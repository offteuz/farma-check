package com.fiap.farmacheck.model.dto.estoque;

import com.fiap.farmacheck.model.dto.medicamento.MedicamentoResponseDTO;
import com.fiap.farmacheck.model.dto.unidade.UnidadeResponseDTO;
import com.fiap.farmacheck.model.entity.Estoque;

public record EstoqueResponseDTO(

        int id,

        Integer quantidade,

        UnidadeResponseDTO unidade,

        MedicamentoResponseDTO medicamento
) {

    public EstoqueResponseDTO(Estoque estoque) {
        this(
                estoque.getId(),
                estoque.getQuantidade(),
                new UnidadeResponseDTO(estoque.getUnidade()),
                new MedicamentoResponseDTO(estoque.getMedicamento())
        );
    }
}
