package com.rrhh.nomina.repository;

import com.rrhh.nomina.model.SolicitudVacaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolicitudVacacionesRepository extends JpaRepository<SolicitudVacaciones, Long> {
    List<SolicitudVacaciones> findByEmpleadoId(Long empleadoId);
}
