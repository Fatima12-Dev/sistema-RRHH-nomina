package com.rrhh.nomina.controller;

import com.rrhh.nomina.model.Empleado;
import com.rrhh.nomina.service.EmpleadoService;
import com.rrhh.nomina.service.NominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*")

public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private NominaService nominaService;

    @GetMapping
    public ResponseEntity<List<Empleado>> listar() {
        return new ResponseEntity<>(empleadoService.obtenerTodos(), HttpStatus.OK); // 200 OK
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<Empleado>> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String departamento) {
        Page<Empleado> resultado = empleadoService.obtenerPaginado(page, size, q, departamento);
        return new ResponseEntity<>(resultado, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscar(@PathVariable Long id) {
        return empleadoService.obtenerPorId(id)
                .map(empleado -> new ResponseEntity<>(empleado, HttpStatus.OK)) // 200 OK
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found si no existe
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Empleado empleado) {
        try {
            Empleado nuevo = empleadoService.guardar(empleado);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            empleadoService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    //  Muestra la nómina calculada automáticamente
    @GetMapping("/{id}/nomina")
    public ResponseEntity<Map<String, Object>> verNomina(@PathVariable Long id) {
        try {
            Map<String, Object> recibo = nominaService.calcularNominaMensual(id);
            return new ResponseEntity<>(recibo, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        try {
            Empleado empleadoActualizado = empleadoService.actualizar(id, empleado);
            return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK); // 200 OK si todo sale bien
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404 si el ID no existe
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 si el salario es inválido
        }
    }

}
