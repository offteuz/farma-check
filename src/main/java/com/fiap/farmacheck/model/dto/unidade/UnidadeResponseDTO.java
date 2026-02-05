package com.fiap.farmacheck.model.dto.unidade;

import com.fiap.farmacheck.model.entity.Unidade;
import com.fiap.farmacheck.model.enums.TipoUnidade;

public record UnidadeResponseDTO(

        int id,

        String nome,

        String cep,

        String telefone,

        String email,

        TipoUnidade tipoUnidade
) {

    public UnidadeResponseDTO(Unidade unidade){
        this(
                unidade.getId(),
                unidade.getNome(),
                unidade.getCep(),
                unidade.getTelefone(),
                unidade.getEmail(),
                unidade.getTipoUnidade()
        );
    }
}
