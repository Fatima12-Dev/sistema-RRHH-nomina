package com.rrhh.nomina.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpleadoDTO {
    private Long id;
    private String nombre;
    private String cargo;
    private String departamento;
    private Double salarioBase;
    private String fechaIngreso;
}
