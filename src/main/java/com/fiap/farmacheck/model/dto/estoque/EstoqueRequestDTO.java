package com.fiap.farmacheck.model.dto.estoque;

import com.fiap.farmacheck.model.dto.unidade.UnidadeRequestDTO;
import jakarta.validation.constraints.NotBlank;

public record EstoqueRequestDTO(

        @NotBlank(message = "A quantidade é obrigatória")
        Integer quantidade,

        @NotBlank(message = "A unidade é obrigatória")
        UnidadeRequestDTO unidadeRequestDTO,

        @NotBlank(message = "O medicamento é obrigatório")
        int idMedicamento
) {
}
