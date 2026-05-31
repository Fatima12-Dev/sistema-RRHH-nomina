package com.rrhh.nomina.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@Entity
@Table(name = "asistencias", uniqueConstraints = {@UniqueConstraint(columnNames = {"empleadoId", "fecha"})})
@JsonPropertyOrder({ "id", "empleadoId", "fecha", "horasTrabajadas", "horasExtra" })

public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long empleadoId;
    private LocalDate fecha;
    private Integer horasTrabajadas;
    private Integer horasExtra;
}
