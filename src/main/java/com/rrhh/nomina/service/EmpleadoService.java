package com.rrhh.nomina.service;

import com.rrhh.nomina.model.Empleado;
import com.rrhh.nomina.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> obtenerTodos() { return empleadoRepository.findAll(); }
    public Optional<Empleado> obtenerPorId(Long id) { return empleadoRepository.findById(id); }
    public void eliminar(Long id) { empleadoRepository.deleteById(id); }

    public Empleado guardar(Empleado empleado) {
        // REGLA DE NEGOCIO: No se permiten salarios vacíos, en $0 o negativos
        if (empleado.getSalarioBase() == null || empleado.getSalarioBase() <= 0) {
            throw new IllegalArgumentException("El salario base debe ser un monto mayor a $0.00");
        }
        return empleadoRepository.save(empleado);
    }
    public Empleado actualizar(Long id, Empleado empleadoDatosNuevos) {
        Empleado empleadoExistente = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con el ID: " + id));

        if (empleadoDatosNuevos.getSalarioBase() == null || empleadoDatosNuevos.getSalarioBase() <= 0) {
            throw new IllegalArgumentException("El salario base debe ser un monto mayor a $0.00");
        }

        empleadoExistente.setNombre(empleadoDatosNuevos.getNombre());
        empleadoExistente.setCargo(empleadoDatosNuevos.getCargo());
        empleadoExistente.setDepartamento(empleadoDatosNuevos.getDepartamento());
        empleadoExistente.setSalarioBase(empleadoDatosNuevos.getSalarioBase());
        empleadoExistente.setFechaIngreso(empleadoDatosNuevos.getFechaIngreso());

        return empleadoRepository.save(empleadoExistente);
    }
}
