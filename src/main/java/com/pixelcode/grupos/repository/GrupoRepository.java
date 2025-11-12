package com.pixelcode.grupos.repository;
import com.pixelcode.grupos.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    
    // Métodos personalizados
    List<Grupo> findByStatus(boolean status);
    
    List<Grupo> findByNombreGrupoContainingIgnoreCase(String nombreGrupo);
    
    List<Grupo> findByCuatrimestre(Integer cuatrimestre);
    
    List<Grupo> findByDivisionId(Integer divisionId);
}