package com.pixelcode.grupo_service.controller;

import com.pixelcode.grupo_service.dto.GrupoDTO;
import com.pixelcode.grupo_service.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
@CrossOrigin(origins = "*")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    /**
     * Create a new grupo
     * POST /api/grupos
     */
    @PostMapping
    public ResponseEntity<GrupoDTO> crearGrupo(@RequestBody GrupoDTO dto) {
        try {
            GrupoDTO nuevoGrupo = grupoService.crearGrupo(dto);
            return new ResponseEntity<>(nuevoGrupo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all grupos
     * GET /api/grupos
     */
    @GetMapping
    public ResponseEntity<List<GrupoDTO>> obtenerTodosGrupos() {
        try {
            List<GrupoDTO> grupos = grupoService.obtenerTodosGrupos();
            if (grupos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(grupos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get only active grupos
     * GET /api/grupos/activos
     */
    @GetMapping("/activos")
    public ResponseEntity<List<GrupoDTO>> obtenerGruposActivos() {
        try {
            List<GrupoDTO> grupos = grupoService.obtenerGruposActivos();
            if (grupos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(grupos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get grupo by ID
     * GET /api/grupos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<GrupoDTO> obtenerGrupoPorId(@PathVariable Long id) {
        try {
            GrupoDTO grupo = grupoService.obtenerGrupoPorId(id);
            return new ResponseEntity<>(grupo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get grupo by codigo
     * GET /api/grupos/codigo/{codigo}
     */
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<GrupoDTO> obtenerGrupoPorCodigo(@PathVariable String codigo) {
        try {
            GrupoDTO grupo = grupoService.obtenerGrupoPorCodigo(codigo);
            return new ResponseEntity<>(grupo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get grupos by division
     * GET /api/grupos/division/{divisionId}
     */
    @GetMapping("/division/{divisionId}")
    public ResponseEntity<List<GrupoDTO>> obtenerGruposPorDivision(@PathVariable Long divisionId) {
        try {
            List<GrupoDTO> grupos = grupoService.obtenerGruposPorDivision(divisionId);
            if (grupos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(grupos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get active grupos by division
     * GET /api/grupos/division/{divisionId}/activos
     */
    @GetMapping("/division/{divisionId}/activos")
    public ResponseEntity<List<GrupoDTO>> obtenerGruposActivosPorDivision(@PathVariable Long divisionId) {
        try {
            List<GrupoDTO> grupos = grupoService.obtenerGruposActivosPorDivision(divisionId);
            if (grupos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(grupos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update grupo
     * PUT /api/grupos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<GrupoDTO> actualizarGrupo(
            @PathVariable Long id, 
            @RequestBody GrupoDTO dto) {
        try {
            GrupoDTO grupoActualizado = grupoService.actualizarGrupo(id, dto);
            return new ResponseEntity<>(grupoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Soft delete (deactivate)
     * DELETE /api/grupos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<GrupoDTO> eliminarGrupo(@PathVariable Long id) {
        try {
            GrupoDTO grupoEliminado = grupoService.eliminarGrupo(id);
            return new ResponseEntity<>(grupoEliminado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Reactivate grupo
     * PATCH /api/grupos/{id}/reactivar
     */
    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<GrupoDTO> reactivarGrupo(@PathVariable Long id) {
        try {
            GrupoDTO grupoReactivado = grupoService.reactivarGrupo(id);
            return new ResponseEntity<>(grupoReactivado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Hard delete
     * DELETE /api/grupos/{id}/permanente
     */
    @DeleteMapping("/{id}/permanente")
    public ResponseEntity<HttpStatus> eliminarGrupoPermanente(@PathVariable Long id) {
        try {
            grupoService.eliminarGrupoPermanente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
