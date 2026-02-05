package com.fiap.farmacheck.mapper;

import com.fiap.farmacheck.model.dto.usuario.UsuarioRequestDTO;
import com.fiap.farmacheck.model.dto.usuario.UsuarioResponseDTO;
import com.fiap.farmacheck.model.entity.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UnidadeMapperHelper.class})
public interface UsuarioMapper {

    // 1. CONVERSÃO (DTO -> Entity)
    @Mapping(source = "unidadeId", target = "unidade")
    Usuario toEntity(UsuarioRequestDTO dto);

    // 2. CONVERSÃO (Entity -> DTO)
    UsuarioResponseDTO toResponseDTO(Usuario entity);

    // 3. ATUALIZAÇÃO (DTO -> Entity Existente)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "unidadeId", target = "unidade")
    void updateEntityFromDto(UsuarioRequestDTO dto, @MappingTarget Usuario usuario);
}