package com.rrhh.nomina.service;

import com.rrhh.nomina.model.Asistencia;
import com.rrhh.nomina.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {
    @Autowired
    private AsistenciaRepository LaboresRepository;

    public List<Asistencia> obtenerTodas() { return LaboresRepository.findAll(); }
    public List<Asistencia> obtenerPorEmpleado(Long empleadoId) { return LaboresRepository.findByEmpleadoId(empleadoId); }

    public Asistencia registrar(Asistencia asistencia) {
        // REGLA DE NEGOCIO: No se pueden registrar horas negativas
        if (asistencia.getHorasTrabajadas() < 0 || asistencia.getHorasExtra() < 0) {
            throw new IllegalArgumentException("Las horas laboradas no pueden ser valores negativos");
        }

        // LÓGICA INTELIGENTE: Buscar si el empleado ya marcó asistencia HOY
        List<Asistencia> registrosDelEmpleado = LaboresRepository.findByEmpleadoId(asistencia.getEmpleadoId());

        Optional<Asistencia> registroExistente = registrosDelEmpleado.stream()
                .filter(a -> a.getFecha().equals(asistencia.getFecha()))
                .findFirst();

        if (registroExistente.isPresent()) {
            Asistencia registroAModificar = registroExistente.get();

            registroAModificar.setHorasTrabajadas(asistencia.getHorasTrabajadas());
            registroAModificar.setHorasExtra(asistencia.getHorasExtra());

            return LaboresRepository.save(registroAModificar);
        }

        return LaboresRepository.save(asistencia);
    }

}
