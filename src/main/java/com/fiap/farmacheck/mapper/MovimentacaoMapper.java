package com.fiap.farmacheck.mapper;

import com.fiap.farmacheck.model.entity.Movimentacao;
import com.fiap.farmacheck.model.dto.movimentacao.MovimentacaoRequestDTO;
import com.fiap.farmacheck.model.dto.movimentacao.MovimentacaoResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {UsuarioMapperHelper.class, EstoqueMapperHelper.class})
public interface MovimentacaoMapper {

    // 1. CONVERSÃO (DTO -> Entity)
    @Mapping(source = "usuarioId", target = "usuario")
    @Mapping(source = "estoqueId", target = "estoque")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataHora", ignore = true)
    Movimentacao toEntity(MovimentacaoRequestDTO dto);

    // 2. CONVERSÃO (Entity -> DTO)
    MovimentacaoResponseDTO toResponse(Movimentacao movimentacao);
}