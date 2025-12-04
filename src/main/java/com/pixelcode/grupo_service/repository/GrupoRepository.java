package com.pixelcode.grupo_service.repository;

import com.pixelcode.grupo_service.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    
    /**
     * Find grupo by codigo (unique)
     */
    Optional<Grupo> findByCodigo(String codigo);
    
    /**
     * Check if codigo exists
     */
    boolean existsByCodigo(String codigo);
    
    /**
     * Find all grupos by division
     */
    List<Grupo> findByDivisionId(Long divisionId);
    
    /**
     * Find only active grupos
     */
    List<Grupo> findByActivoTrue();
    
    /**
     * Find active grupos by division
     */
    List<Grupo> findByDivisionIdAndActivoTrue(Long divisionId);
}
