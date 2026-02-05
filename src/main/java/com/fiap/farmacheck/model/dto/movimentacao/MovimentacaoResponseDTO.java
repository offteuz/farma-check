package com.fiap.farmacheck.model.dto.movimentacao;

import com.fiap.farmacheck.model.dto.estoque.EstoqueResponseDTO;
import com.fiap.farmacheck.model.dto.usuario.UsuarioResponseDTO;
import com.fiap.farmacheck.model.entity.Movimentacao;
import com.fiap.farmacheck.model.enums.TipoMovimentacao;

public record MovimentacaoResponseDTO(

        int id,

        Integer quantidade,

        TipoMovimentacao tipoMovimentacao,

        UsuarioResponseDTO usuario,

        EstoqueResponseDTO estoque
) {

    public MovimentacaoResponseDTO(Movimentacao movimentacao) {
        this (
                movimentacao.getId(),
                movimentacao.getQuantidade(),
                movimentacao.getTipoMovimentacao(),
                movimentacao.getUsuario(),
                new EstoqueResponseDTO(movimentacao.getEstoque())
        );
    }
}
