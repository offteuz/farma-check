package com.fiap.farmacheck.mapper;

import com.fiap.farmacheck.model.entity.Unidade;
import com.fiap.farmacheck.model.dto.unidade.UnidadeRequestDTO;
import com.fiap.farmacheck.model.dto.unidade.UnidadeResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UnidadeMapper {

    // 1. CONVERSÃO (DTO -> Entity)
    Unidade toEntity(UnidadeRequestDTO dto);

    // 2. CONVERSÃO (Entity -> DTO)
    UnidadeResponseDTO toResponse(Unidade entity);

    // 3. ATUALIZAÇÃO (DTO -> Entity Existente)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UnidadeRequestDTO dto, @MappingTarget Unidade unidade);
}