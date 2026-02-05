package com.fiap.farmacheck.mapper;

import com.fiap.farmacheck.model.entity.Estoque;
import com.fiap.farmacheck.model.dto.estoque.EstoqueRequestDTO;
import com.fiap.farmacheck.model.dto.estoque.EstoqueResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {UnidadeMapperHelper.class, MedicamentoMapperHelper.class})
public interface EstoqueMapper {

    // 1. CONVERSÃO (DTO -> Entity)
    @Mapping(source = "unidadeId", target = "unidade")
    @Mapping(source = "medicamentoId", target = "medicamento")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataUltimaAtualizacao", ignore = true)
    Estoque toEntity(EstoqueRequestDTO dto);

    // 2. CONVERSÃO (Entity -> DTO)
    EstoqueResponseDTO toResponse(Estoque entity);

    // 3. ATUALIZAÇÃO (DTO -> Entity Existente)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EstoqueRequestDTO dto, @MappingTarget Estoque entity);
}