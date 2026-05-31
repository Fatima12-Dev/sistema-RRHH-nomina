package com.rrhh.nomina.repository;


import com.rrhh.nomina.model.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    @Query("""
            SELECT e FROM Empleado e
            WHERE (:departamento IS NULL OR e.departamento = :departamento)
              AND (:texto IS NULL
                   OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
                   OR LOWER(e.cargo)  LIKE LOWER(CONCAT('%', :texto, '%')))
            """)
    Page<Empleado> buscar(@Param("texto") String texto,
                          @Param("departamento") String departamento,
                          Pageable pageable);
}
