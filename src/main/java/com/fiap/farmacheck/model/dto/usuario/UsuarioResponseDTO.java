package com.fiap.farmacheck.model.dto.usuario;

import com.fiap.farmacheck.model.dto.auditoria.AuditoriaResponseDTO;
import com.fiap.farmacheck.model.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class UsuarioResponseDTO extends AuditoriaResponseDTO {

    private Integer id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario; // PACIENTE ou ADMINISTRADOR
}
