package com.fiap.farmacheck.kafka;

import com.fiap.farmacheck.model.dto.disponibilidade.MedicamentoIndisponivelEvent;
import com.fiap.farmacheck.model.entity.Estoque;
import com.fiap.farmacheck.repository.EstoqueRepository;
import com.fiap.farmacheck.service.EmailService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
public class MedicamentoReprocessamentoScheduler {

    private static final Logger logger = LoggerFactory.getLogger(MedicamentoReprocessamentoScheduler.class);

    private final EstoqueRepository estoqueRepository;
    private final EmailService emailService;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${farmacheck.kafka.topic.medicamento-indisponivel}")
    private String topic;

    public MedicamentoReprocessamentoScheduler(EstoqueRepository estoqueRepository, EmailService emailService) {
        this.estoqueRepository = estoqueRepository;
        this.emailService = emailService;
    }

    @Scheduled(fixedDelayString = "${farmacheck.kafka.reprocessamento.intervalo-ms}")
    public void reprocessarMedicamentosIndisponiveis() {
        logger.info("Iniciando reprocessamento periodico de medicamentos indisponiveis...");

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "farmacheck-reprocessamento-group");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", JsonDeserializer.class.getName());
        props.put("spring.json.trusted.packages", "com.fiap.farmacheck.model.dto.disponibilidade");
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "false");

        try (KafkaConsumer<String, MedicamentoIndisponivelEvent> consumer =
                     new KafkaConsumer<>(props)) {

            consumer.subscribe(Collections.singletonList(topic));

            ConsumerRecords<String, MedicamentoIndisponivelEvent> records =
                    consumer.poll(Duration.ofSeconds(5));

            if (records.isEmpty()) {
                logger.info("Nenhum medicamento na fila para reprocessamento.");
                return;
            }

            for (ConsumerRecord<String, MedicamentoIndisponivelEvent> record : records) {
                MedicamentoIndisponivelEvent event = record.value();
                logger.info("Reprocessando medicamento: {}", event.getNomeMedicamento());

                try {
                    List<Estoque> estoques = estoqueRepository
                            .findByMedicamentoNomeIgnoreCaseAndQuantidadeGreaterThan(
                                    event.getNomeMedicamento(), 0);

                    if (!estoques.isEmpty()) {
                        logger.info("Medicamento '{}' agora DISPONIVEL! Enviando email para: {}",
                                event.getNomeMedicamento(), event.getEmailUsuario());

                        // Tenta enviar o email
                        emailService.enviarNotificacaoDisponibilidade(
                                event.getEmailUsuario(),
                                event.getNomeUsuario(),
                                event.getNomeMedicamento()
                        );

                        // SOMENTE se o email foi enviado com sucesso, faz commit
                        consumer.commitSync(Collections.singletonMap(
                                new TopicPartition(record.topic(), record.partition()),
                                new OffsetAndMetadata(record.offset() + 1)
                        ));

                        logger.info("Email enviado com sucesso. Medicamento removido da fila.");
                    } else {
                        // Medicamento continua indisponível - NÃO faz commit
                        logger.info("Medicamento '{}' continua INDISPONIVEL. Permanecerá na fila.",
                                event.getNomeMedicamento());
                    }
                } catch (Exception e) {
                    // Qualquer erro - NÃO faz commit, mantém na fila
                    logger.error("Erro ao processar medicamento '{}'. Permanecerá na fila para reprocessamento. Erro: {}",
                            event.getNomeMedicamento(), e.getMessage(), e);
                }
            }

            logger.info("Reprocessamento concluido. {} mensagens processadas.", records.count());
        } catch (Exception e) {
            logger.error("Erro durante reprocessamento de medicamentos: {}", e.getMessage(), e);
        }
    }
}
