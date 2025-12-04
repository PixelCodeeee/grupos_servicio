package com.pixelcode.grupo_service.service;

import com.pixelcode.grupo_service.dto.GrupoDTO;
import com.pixelcode.grupo_service.entity.Grupo;
import com.pixelcode.grupo_service.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    /**
     * Create a new grupo
     */
    @Transactional
    public GrupoDTO crearGrupo(GrupoDTO dto) {
        // Check if codigo already exists
        if (grupoRepository.existsByCodigo(dto.getCodigo())) {
            throw new RuntimeException("Ya existe un grupo con el código: " + dto.getCodigo());
        }
        
        Grupo grupo = new Grupo();
        grupo.setNombre(dto.getNombre());
        grupo.setCodigo(dto.getCodigo());
        grupo.setDivisionId(dto.getDivisionId());
        grupo.setNombreDivision(dto.getNombreDivision());
        grupo.setActivo(dto.getActivo() != null ? dto.getActivo() : true);
        
        Grupo saved = grupoRepository.save(grupo);
        
        return convertToDTO(saved);
    }

    /**
     * Get all grupos
     */
    public List<GrupoDTO> obtenerTodosGrupos() {
        return grupoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get only active grupos
     */
    public List<GrupoDTO> obtenerGruposActivos() {
        return grupoRepository.findByActivoTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get grupo by ID
     */
    public GrupoDTO obtenerGrupoPorId(Long id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con id: " + id));
        return convertToDTO(grupo);
    }

    /**
     * Get grupo by codigo
     */
    public GrupoDTO obtenerGrupoPorCodigo(String codigo) {
        Grupo grupo = grupoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con código: " + codigo));
        return convertToDTO(grupo);
    }

    /**
     * Get grupos by division
     */
    public List<GrupoDTO> obtenerGruposPorDivision(Long divisionId) {
        return grupoRepository.findByDivisionId(divisionId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get active grupos by division
     */
    public List<GrupoDTO> obtenerGruposActivosPorDivision(Long divisionId) {
        return grupoRepository.findByDivisionIdAndActivoTrue(divisionId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Update grupo
     */
    @Transactional
    public GrupoDTO actualizarGrupo(Long id, GrupoDTO dto) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con id: " + id));
        
        // Check if new codigo conflicts with existing grupo
        if (!grupo.getCodigo().equals(dto.getCodigo()) && 
            grupoRepository.existsByCodigo(dto.getCodigo())) {
            throw new RuntimeException("Ya existe un grupo con el código: " + dto.getCodigo());
        }
        
        grupo.setNombre(dto.getNombre());
        grupo.setCodigo(dto.getCodigo());
        grupo.setDivisionId(dto.getDivisionId());
        grupo.setNombreDivision(dto.getNombreDivision());
        grupo.setActivo(dto.getActivo() != null ? dto.getActivo() : grupo.getActivo());
        
        Grupo updated = grupoRepository.save(grupo);
        
        return convertToDTO(updated);
    }

    /**
     * Soft delete (deactivate)
     */
    @Transactional
    public GrupoDTO eliminarGrupo(Long id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con id: " + id));
        
        grupo.setActivo(false);
        Grupo updated = grupoRepository.save(grupo);
        
        return convertToDTO(updated);
    }

    /**
     * Reactivate grupo
     */
    @Transactional
    public GrupoDTO reactivarGrupo(Long id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con id: " + id));
        
        grupo.setActivo(true);
        Grupo updated = grupoRepository.save(grupo);
        
        return convertToDTO(updated);
    }

    /**
     * Hard delete
     */
    @Transactional
    public void eliminarGrupoPermanente(Long id) {
        if (!grupoRepository.existsById(id)) {
            throw new RuntimeException("Grupo no encontrado con id: " + id);
        }
        grupoRepository.deleteById(id);
    }

    /**
     * Update division name in all grupos (call when division name changes)
     */
    @Transactional
    public void actualizarNombreDivision(Long divisionId, String nuevoNombre) {
        List<Grupo> grupos = grupoRepository.findByDivisionId(divisionId);
        grupos.forEach(g -> {
            g.setNombreDivision(nuevoNombre);
            grupoRepository.save(g);
        });
    }

    /**
     * Convert entity to DTO
     */
    private GrupoDTO convertToDTO(Grupo grupo) {
        GrupoDTO dto = new GrupoDTO();
        dto.setId(grupo.getId());
        dto.setNombre(grupo.getNombre());
        dto.setCodigo(grupo.getCodigo());
        dto.setDivisionId(grupo.getDivisionId());
        dto.setNombreDivision(grupo.getNombreDivision());
        dto.setActivo(grupo.getActivo());
        return dto;
    }
}
