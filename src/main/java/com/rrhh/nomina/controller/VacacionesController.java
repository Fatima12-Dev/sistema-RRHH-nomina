package com.rrhh.nomina.controller;

import com.rrhh.nomina.model.SolicitudVacaciones;
import com.rrhh.nomina.service.VacacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vacaciones")
@CrossOrigin(origins = "*")
public class VacacionesController {
    @Autowired
    private VacacionesService vacacionesService;

    @GetMapping
    public ResponseEntity<List<SolicitudVacaciones>> listarTodas() {
        return new ResponseEntity<>(vacacionesService.obtenerTodas(), HttpStatus.OK);
    }

    // Cuando el empleado pide vacaciones (entra automáticamente como 'Pendiente')
    @PostMapping
    public ResponseEntity<SolicitudVacaciones> crear(@RequestBody SolicitudVacaciones solicitud) {
        return new ResponseEntity<>(vacacionesService.solicitar(solicitud), HttpStatus.CREATED); // 201 Created
    }

    // Para que aprueba o rechaza la solicitud
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> resolver(@PathVariable Long id, @RequestParam String estado) {
        try {
            SolicitudVacaciones resuelta = vacacionesService.resolverSolicitud(id, estado);
            return new ResponseEntity<>(resuelta, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            vacacionesService.eliminarSolicitud(id);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK si se eliminó con éxito
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found si el ID no existe
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
