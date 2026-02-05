package com.fiap.farmacheck.model.dto.medicamento;

import com.fiap.farmacheck.model.entity.Medicamento;

public record MedicamentoResponseDTO(

        int id,

        String nome,

        String pricipioAtivo,

        String dosagem,

        String laboratorio
) {

    public MedicamentoResponseDTO(Medicamento medicamento) {
        this(
                medicamento.getId(),
                medicamento.getNome(),
                medicamento.getPricipioAtivo(),
                medicamento.getDosagem(),
                medicamento.getLaboratorio()
        );
    }
}
