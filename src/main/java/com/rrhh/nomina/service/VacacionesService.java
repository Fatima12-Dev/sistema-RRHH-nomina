package com.rrhh.nomina.service;

import com.rrhh.nomina.model.SolicitudVacaciones;
import com.rrhh.nomina.repository.SolicitudVacacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VacacionesService {
    @Autowired
    private SolicitudVacacionesRepository vacacionesRepository;

    public List<SolicitudVacaciones> obtenerTodas() { return vacacionesRepository.findAll(); }
    public List<SolicitudVacaciones> obtenerPorEmpleado(Long empleadoId) { return vacacionesRepository.findByEmpleadoId(empleadoId); }

    public SolicitudVacaciones solicitar(SolicitudVacaciones solicitud) {
        // REGLA DE NEGOCIO: Toda solicitud nueva debe registrarse automáticamente como "Pendiente"
        solicitud.setEstado("Pendiente");
        return vacacionesRepository.save(solicitud);
    }

    public SolicitudVacaciones resolverSolicitud(Long id, String nuevoEstado) {
        SolicitudVacaciones solicitud = vacacionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La solicitud de vacaciones no existe"));

        // REGLA DE NEGOCIO: Solo se permite cambiar el estado a valores válidos
        if (!nuevoEstado.equals("Aprobado") && !nuevoEstado.equals("Rechazado")) {
            throw new IllegalArgumentException("El estado enviado no es válido (Debe ser Aprobado o Rechazado)");
        }
        solicitud.setEstado(nuevoEstado);
        return vacacionesRepository.save(solicitud);
    }

    public void eliminarSolicitud(Long id) {
        SolicitudVacaciones solicitud = vacacionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La solicitud de vacaciones no existe"));

        // REGLA DE NEGOCIO: Solo se puede eliminar si está Pendiente
        if (!solicitud.getEstado().equals("Pendiente")) {
            throw new IllegalStateException("No se puede eliminar una solicitud que ya fue " + solicitud.getEstado());
        }

        vacacionesRepository.deleteById(id);
    }

}
