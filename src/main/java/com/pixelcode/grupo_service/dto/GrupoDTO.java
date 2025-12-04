package com.pixelcode.grupo_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Grupo entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDTO {
    private Long id;
    private String nombre;
    private String codigo;
    private Long divisionId;
    private String nombreDivision;
    private Boolean activo;
}
