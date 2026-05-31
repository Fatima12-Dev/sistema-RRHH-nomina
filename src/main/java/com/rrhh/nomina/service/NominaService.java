package com.rrhh.nomina.service;

import com.rrhh.nomina.model.Asistencia;
import com.rrhh.nomina.model.Empleado;
import com.rrhh.nomina.repository.AsistenciaRepository;
import com.rrhh.nomina.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NominaService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private AsistenciaRepository asistenciaRepository;

    public Map<String, Object> calcularNominaMensual(Long empleadoId) {
        // Buscar si el empleado existe
        Empleado emp = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        // Buscar todas las asistencias registradas de ese empleado
        List<Asistencia> asistencias = asistenciaRepository.findByEmpleadoId(empleadoId);

        // las horas extras acumuladas en el mes
        int totalHorasExtra = asistencias.stream().mapToInt(Asistencia::getHorasExtra).sum();

        double salarioBase = emp.getSalarioBase();
        double valorHoraNormal = salarioBase / 30 / 8; // Cálculo estimado por día y hora
        double pagoHorasExtra = totalHorasExtra * (valorHoraNormal * 2); // Pago al doble por ley

        double bonificaciones = 50.00; // Un bono fijo de transporte asignado por la empresa
        double deducciones = salarioBase * 0.075; // Retenciones de ley estimadas (7.5%)

        // Sueldo Neto
        double salarioNeto = (salarioBase + pagoHorasExtra + bonificaciones) - deducciones;

        // formato de "Boleta de Pago"
        Map<String, Object> recibo = new java.util.LinkedHashMap<>(); //
        recibo.put("empleadoId", emp.getId());
        recibo.put("nombre", emp.getNombre());
        recibo.put("salarioBase", salarioBase);
        recibo.put("horasExtraAcumuladas", totalHorasExtra);
        recibo.put("pagoHorasExtra", pagoHorasExtra);
        recibo.put("bonificaciones", bonificaciones);
        recibo.put("deducciones", deducciones);
        recibo.put("salarioNeto", salarioNeto);

        return recibo;
    }

}
