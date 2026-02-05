package com.fiap.farmacheck.mapper;

import com.fiap.farmacheck.model.entity.Medicamento;
import com.fiap.farmacheck.model.dto.medicamento.MedicamentoRequestDTO;
import com.fiap.farmacheck.model.dto.medicamento.MedicamentoResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MedicamentoMapper {

    // 1. CONVERSÃO (DTO -> Entity)
    Medicamento toEntity(MedicamentoRequestDTO dto);

    // 2. CONVERSÃO (Entity -> DTO)
    MedicamentoResponseDTO toResponse(Medicamento entity);

    // 3. ATUALIZAÇÃO (DTO -> Entity Existente)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(MedicamentoRequestDTO dto, @MappingTarget Medicamento medicamento);
}