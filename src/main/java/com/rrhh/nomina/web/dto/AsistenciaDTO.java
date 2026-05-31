package com.rrhh.nomina.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsistenciaDTO {
    private Long id;
    private Long empleadoId;
    private String fecha;
    private Integer horasTrabajadas;
    private Integer horasExtra;
}
