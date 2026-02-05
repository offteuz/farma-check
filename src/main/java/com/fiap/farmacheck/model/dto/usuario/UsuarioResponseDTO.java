package com.fiap.farmacheck.model.dto.usuario;

import com.fiap.farmacheck.model.dto.unidade.UnidadeResponseDTO;
import com.fiap.farmacheck.model.entity.Usuario;
import com.fiap.farmacheck.model.enums.TipoUsuario;

public record UsuarioResponseDTO(

        int id,

        String nome,

        String email,

        TipoUsuario tipoUsuario,

        UnidadeResponseDTO unidade
) {

    public UsuarioResponseDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                new UnidadeResponseDTO(usuario.getUnidade())
        );
    }
}