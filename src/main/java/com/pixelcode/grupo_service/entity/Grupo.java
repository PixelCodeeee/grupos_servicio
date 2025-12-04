package com.pixelcode.grupo_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad Grupo
 * Representa un grupo/clase académica
 * 
 * MICROSERVICES PATTERN:
 * - References Division by ID only (no JPA relationship)
 * - Division entity lives in division-service (port 8085)
 * - To get Division details, call division-service REST API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grupo", 
       uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(nullable = false, unique = true, length = 50)
    private String codigo;
    
    /**
     * Foreign key to Division (in division-service)
     * We store the ID but don't use JPA @ManyToOne relationship
     */
    @Column(name = "division_id", nullable = false)
    private Long divisionId;
    
    /**
     * Cached division name for quick display
     * Should be synced when division name changes
     */
    @Column(name = "nombre_division", length = 200)
    private String nombreDivision;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (activo == null) {
            activo = true;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}