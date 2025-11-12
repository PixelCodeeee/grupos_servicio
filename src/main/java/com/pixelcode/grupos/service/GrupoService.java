package com.pixelcode.grupos.service;

import com.pixelcode.grupos.dto.GrupoDto;
import com.pixelcode.grupos.entity.Grupo;
import com.pixelcode.grupos.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GrupoService {
    
    private final GrupoRepository grupoRepository;
    
    // Listar grupos
    public List<GrupoDto> listarTodosLosGrupos() {
        List<Grupo> grupos = grupoRepository.findAll();
        return grupos.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    public List<GrupoDto> listarGruposPorStatus(boolean status) {
        return grupoRepository.findByStatus(status)
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    public List<GrupoDto> buscarPorNombre(String nombre) {
        List<Grupo> grupos = grupoRepository.findByNombreGrupoContainingIgnoreCase(nombre);
        return grupos.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    public List<GrupoDto> listarPorCuatrimestre(Integer cuatrimestre) {
        List<Grupo> grupos = grupoRepository.findByCuatrimestre(cuatrimestre);
        return grupos.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    public List<GrupoDto> listarPorDivision(Integer divisionId) {
        List<Grupo> grupos = grupoRepository.findByDivisionId(divisionId);
        return grupos.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    // Agregar nuevo grupo
    @Transactional
    public GrupoDto agregarGrupo(GrupoDto grupoDto) {
        Grupo grupo = new Grupo();
        grupo.setNombreGrupo(grupoDto.getNombreGrupo());
        grupo.setCuatrimestre(grupoDto.getCuatrimestre());
        grupo.setStatus(grupoDto.getStatus() != null ? grupoDto.getStatus() : true); // Por defecto activo
        grupo.setDivisionId(grupoDto.getDivisionId());
        
        Grupo grupoGuardado = grupoRepository.save(grupo);
        return convertirADto(grupoGuardado);
    }
    
    // Editar grupo existente (permite edición parcial)
@Transactional
public GrupoDto editarGrupo(Integer id, GrupoDto grupoDto) {
    Grupo grupo = grupoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Grupo no encontrado con ID: " + id));
    
    // Solo actualiza los campos que vienen en el DTO (no son null)
    if (grupoDto.getNombreGrupo() != null) {
        grupo.setNombreGrupo(grupoDto.getNombreGrupo());
    }
    
    if (grupoDto.getCuatrimestre() != null) {
        grupo.setCuatrimestre(grupoDto.getCuatrimestre());
    }
    
    if (grupoDto.getStatus() != null) {
        grupo.setStatus(grupoDto.getStatus());
    }
    
    if (grupoDto.getDivisionId() != null) {
        grupo.setDivisionId(grupoDto.getDivisionId());
    }
    
    Grupo grupoActualizado = grupoRepository.save(grupo);
    return convertirADto(grupoActualizado);
}
    // Dar de baja lógica
    @Transactional
    public void darDeBajaGrupo(Integer id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con ID: " + id));
        
        grupo.setStatus(false); // Baja lógica
        grupoRepository.save(grupo);
    }
    
    // Método adicional para reactivar un grupo
    @Transactional
    public GrupoDto reactivarGrupo(Integer id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con ID: " + id));
        
        grupo.setStatus(true);
        Grupo grupoReactivado = grupoRepository.save(grupo);
        return convertirADto(grupoReactivado);
    }
    
    private GrupoDto convertirADto(Grupo grupo) {
        return new GrupoDto(
            grupo.getIdGrupo(),
            grupo.getNombreGrupo(),
            grupo.getCuatrimestre(),
            grupo.getStatus(),
            grupo.getDivisionId()
        );
    }
}