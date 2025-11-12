package com.pixelcode.grupos.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDto {
    private Integer idGrupo;
    private String nombreGrupo;
    private Integer cuatrimestre;
    private Boolean status;
    private Integer divisionId;
}