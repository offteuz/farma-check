package com.fiap.farmacheck.mapper;

import com.fiap.farmacheck.model.dto.usuario.UsuarioRequestDTO;
import com.fiap.farmacheck.model.dto.usuario.UsuarioResponseDTO;
import com.fiap.farmacheck.model.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    UsuarioResponseDTO toResponseDTO(Usuario entity);
}