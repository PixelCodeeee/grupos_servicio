package com.pixelcode.grupos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grupos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grupo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Integer idGrupo;
    
    @Column(name = "nombre_grupo", nullable = false, length = 100)
    private String nombreGrupo;
    
    @Column(nullable = false)
    private Integer cuatrimestre;
    
    @Column(nullable = false)
    private Boolean status;
    
    @Column(name = "division_id", nullable = false)
    private Integer divisionId;
}
