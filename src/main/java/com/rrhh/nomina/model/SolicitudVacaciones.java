package com.rrhh.nomina.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@Entity
@Table(name = "solicitudes_vacaciones")
@JsonPropertyOrder({ "id", "empleadoId", "fechaInicio", "fechaFin", "estado" })

public class SolicitudVacaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long empleadoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado; // Pendiente, Aprobado o Rechazado
}
