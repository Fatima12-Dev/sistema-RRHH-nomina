package com.rrhh.nomina.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacacionDTO {
    private Long id;
    private Long empleadoId;
    private String fechaInicio;
    private String fechaFin;
    private String estado;
}
