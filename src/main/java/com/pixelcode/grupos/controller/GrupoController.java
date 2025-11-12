package com.pixelcode.grupos.controller;

import com.pixelcode.grupos.dto.GrupoDto;
import com.pixelcode.grupos.service.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
@RequiredArgsConstructor
public class GrupoController {
    
    private final GrupoService grupoService;
    
    // HU08.1 - Listar grupos
    @GetMapping
    public ResponseEntity<List<GrupoDto>> listarTodosLosGrupos() {
        List<GrupoDto> grupos = grupoService.listarTodosLosGrupos();
        return ResponseEntity.ok(grupos);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<GrupoDto>> listarPorStatus(@PathVariable boolean status) {
        List<GrupoDto> grupos = grupoService.listarGruposPorStatus(status);
        return ResponseEntity.ok(grupos);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<GrupoDto>> buscarPorNombre(@RequestParam String nombre) {
        List<GrupoDto> grupos = grupoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(grupos);
    }
    
    @GetMapping("/cuatrimestre/{cuatrimestre}")
    public ResponseEntity<List<GrupoDto>> listarPorCuatrimestre(@PathVariable Integer cuatrimestre) {
        List<GrupoDto> grupos = grupoService.listarPorCuatrimestre(cuatrimestre);
        return ResponseEntity.ok(grupos);
    }
    
    @GetMapping("/division/{divisionId}")
    public ResponseEntity<List<GrupoDto>> listarPorDivision(@PathVariable Integer divisionId) {
        List<GrupoDto> grupos = grupoService.listarPorDivision(divisionId);
        return ResponseEntity.ok(grupos);
    }
    
    // HU08.2 - Agregar nuevo grupo
    @PostMapping
    public ResponseEntity<GrupoDto> agregarGrupo(@RequestBody GrupoDto grupoDto) {
        GrupoDto nuevoGrupo = grupoService.agregarGrupo(grupoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGrupo);
    }
    
    // HU08.3 - Editar grupo existente
    @PutMapping("/{id}")
    public ResponseEntity<GrupoDto> editarGrupo(
            @PathVariable Integer id, 
            @RequestBody GrupoDto grupoDto) {
        GrupoDto grupoActualizado = grupoService.editarGrupo(id, grupoDto);
        return ResponseEntity.ok(grupoActualizado);
    }
    
    // HU08.4 - Dar de baja lógica a un grupo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> darDeBajaGrupo(@PathVariable Integer id) {
        grupoService.darDeBajaGrupo(id);
        return ResponseEntity.noContent().build();
    }
    
    // Endpoint adicional para reactivar un grupo (opcional)
    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<GrupoDto> reactivarGrupo(@PathVariable Integer id) {
        GrupoDto grupoReactivado = grupoService.reactivarGrupo(id);
        return ResponseEntity.ok(grupoReactivado);
    }
}