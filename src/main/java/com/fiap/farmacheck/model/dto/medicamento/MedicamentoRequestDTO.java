package com.fiap.farmacheck.model.dto.medicamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicamentoRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "O principio ativo é obrigatório")
        String pricipioAtivo,

        @NotBlank(message = "A dosagem é obrigatória")
        String dosagem,

        @NotBlank(message = "O laboratorio é obrigatório")
        String laboratorio
) {
}
