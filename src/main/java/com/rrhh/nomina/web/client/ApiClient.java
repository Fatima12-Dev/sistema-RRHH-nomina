package com.rrhh.nomina.web.client;

import com.rrhh.nomina.web.dto.AsistenciaDTO;
import com.rrhh.nomina.web.dto.EmpleadoDTO;
import com.rrhh.nomina.web.dto.PaginaDTO;
import com.rrhh.nomina.web.dto.VacacionDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class ApiClient {

    private final RestClient rest;

    public ApiClient(RestClient apiRestClient) {
        this.rest = apiRestClient;
    }

    public List<EmpleadoDTO> listarEmpleados() {
        return rest.get().uri("/api/empleados").retrieve()
                .body(new ParameterizedTypeReference<List<EmpleadoDTO>>() {});
    }

    public PaginaDTO<EmpleadoDTO> listarEmpleadosPaginado(int page, int size, String q, String departamento) {
        return rest.get().uri(uriBuilder -> uriBuilder
                        .path("/api/empleados/paginado")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .queryParamIfPresent("q", java.util.Optional.ofNullable(
                                (q != null && !q.isBlank()) ? q : null))
                        .queryParamIfPresent("departamento", java.util.Optional.ofNullable(
                                (departamento != null && !departamento.isBlank()) ? departamento : null))
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<PaginaDTO<EmpleadoDTO>>() {});
    }

    public EmpleadoDTO obtenerEmpleado(Long id) {
        return rest.get().uri("/api/empleados/{id}", id).retrieve().body(EmpleadoDTO.class);
    }

    public EmpleadoDTO crearEmpleado(EmpleadoDTO emp) {
        return rest.post().uri("/api/empleados").body(emp).retrieve().body(EmpleadoDTO.class);
    }

    public EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO emp) {
        return rest.put().uri("/api/empleados/{id}", id).body(emp).retrieve().body(EmpleadoDTO.class);
    }

    public void eliminarEmpleado(Long id) {
        rest.delete().uri("/api/empleados/{id}", id).retrieve().toBodilessEntity();
    }

    public Map<String, Object> obtenerNomina(Long id) {
        return rest.get().uri("/api/empleados/{id}/nomina", id).retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {});
    }

    public List<AsistenciaDTO> listarAsistencias() {
        return rest.get().uri("/api/asistencias").retrieve()
                .body(new ParameterizedTypeReference<List<AsistenciaDTO>>() {});
    }

    public List<AsistenciaDTO> asistenciasPorEmpleado(Long empleadoId) {
        return rest.get().uri("/api/asistencias/empleado/{id}", empleadoId).retrieve()
                .body(new ParameterizedTypeReference<List<AsistenciaDTO>>() {});
    }

    public AsistenciaDTO registrarAsistencia(AsistenciaDTO a) {
        return rest.post().uri("/api/asistencias").body(a).retrieve().body(AsistenciaDTO.class);
    }

    public List<VacacionDTO> listarVacaciones() {
        return rest.get().uri("/api/vacaciones").retrieve()
                .body(new ParameterizedTypeReference<List<VacacionDTO>>() {});
    }

    public List<VacacionDTO> vacacionesPorEmpleado(Long empleadoId) {
        return rest.get().uri("/api/vacaciones/empleado/{id}", empleadoId).retrieve()
                .body(new ParameterizedTypeReference<List<VacacionDTO>>() {});
    }

    public VacacionDTO crearVacacion(VacacionDTO v) {
        return rest.post().uri("/api/vacaciones").body(v).retrieve().body(VacacionDTO.class);
    }

    public void resolverVacacion(Long id, String estado) {
        rest.put().uri("/api/vacaciones/{id}/estado?estado={e}", id, estado)
                .retrieve().toBodilessEntity();
    }

    public void eliminarVacacion(Long id) {
        rest.delete().uri("/api/vacaciones/{id}", id).retrieve().toBodilessEntity();
    }
}
