package com.rrhh.nomina.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@Entity
@Table(name = "empleados")
@JsonPropertyOrder({ "id", "nombre", "cargo", "departamento", "salarioBase", "fechaIngreso" })

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String cargo;
    private String departamento;
    private Double salarioBase;
    private LocalDate fechaIngreso;


}
