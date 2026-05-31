package com.rrhh.nomina.controller;

import com.rrhh.nomina.model.Asistencia;
import com.rrhh.nomina.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
@CrossOrigin(origins = "*")
public class AsistenciaController {
    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<List<Asistencia>> listarTodas() {
        return new ResponseEntity<>(asistenciaService.obtenerTodas(), HttpStatus.OK);
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<Asistencia>> porEmpleado(@PathVariable Long empleadoId) {
        return new ResponseEntity<>(asistenciaService.obtenerPorEmpleado(empleadoId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Asistencia asistencia) {
        try {
            Asistencia nueva = asistenciaService.registrar(asistencia);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }
}
