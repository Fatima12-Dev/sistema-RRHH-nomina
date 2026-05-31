package com.rrhh.nomina.web.controller;

import com.rrhh.nomina.web.client.ApiClient;
import com.rrhh.nomina.web.dto.EmpleadoDTO;
import com.rrhh.nomina.web.dto.AsistenciaDTO;
import com.rrhh.nomina.web.dto.PaginaDTO;
import com.rrhh.nomina.web.session.UsuarioSesion;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/panel/admin")
public class AdminViewController {

    private final ApiClient api;

    public AdminViewController(ApiClient api) {
        this.api = api;
    }

    @ModelAttribute("usuario")
    public UsuarioSesion usuario(HttpSession session) {
        return (UsuarioSesion) session.getAttribute(UsuarioSesion.SESSION_KEY);
    }

    private static final int TAM_PAGINA = 10;

    @GetMapping("/empleados")
    public String empleados(@RequestParam(required = false) String q,
                            @RequestParam(required = false) String departamento,
                            @RequestParam(defaultValue = "0") int page,
                            Model model) {
        model.addAttribute("activo", "empleados");

        PaginaDTO<EmpleadoDTO> paginaEmpleados =
                api.listarEmpleadosPaginado(page, TAM_PAGINA, q, departamento);

        List<String> departamentos = api.listarEmpleados().stream()
                .map(EmpleadoDTO::getDepartamento)
                .filter(d -> d != null && !d.isBlank())
                .distinct().sorted().toList();

        model.addAttribute("empleados", paginaEmpleados.getContent());
        model.addAttribute("departamentos", departamentos);
        model.addAttribute("q", q);
        model.addAttribute("departamentoSel", departamento);
        model.addAttribute("paginaActual", paginaEmpleados.getNumber());
        model.addAttribute("totalPaginas", Math.max(1, paginaEmpleados.getTotalPages()));
        model.addAttribute("totalRegistros", paginaEmpleados.getTotalElements());
        return "panel/admin/empleados";
    }

    @PostMapping("/empleados")
    public String crearEmpleado(@RequestParam String nombre,
                                @RequestParam String cargo,
                                @RequestParam String departamento,
                                @RequestParam Double salarioBase,
                                @RequestParam String fechaIngreso,
                                Model model) {
        EmpleadoDTO e = new EmpleadoDTO();
        e.setNombre(nombre);
        e.setCargo(cargo);
        e.setDepartamento(departamento);
        e.setSalarioBase(salarioBase);
        e.setFechaIngreso(fechaIngreso);
        api.crearEmpleado(e);
        return "redirect:/panel/admin/empleados";
    }

    @PostMapping("/empleados/{id}/eliminar")
    public String eliminarEmpleado(@PathVariable Long id) {
        api.eliminarEmpleado(id);
        return "redirect:/panel/admin/empleados";
    }

    @GetMapping("/asistencias")
    public String asistencias(@RequestParam(required = false) String fecha, Model model) {
        model.addAttribute("activo", "asistencias");

        List<AsistenciaDTO> asistencias = api.listarAsistencias();

        if (fecha != null && !fecha.isBlank()) {
            asistencias = asistencias.stream()
                    .filter(a -> fecha.equals(a.getFecha()))
                    .toList();
        }
        asistencias = asistencias.stream()
                .sorted(Comparator.comparing(AsistenciaDTO::getFecha,
                        Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();

        model.addAttribute("asistencias", asistencias);
        model.addAttribute("fechaSel", fecha);
        return "panel/admin/asistencias";
    }

    @GetMapping("/vacaciones")
    public String vacaciones(Model model) {
        model.addAttribute("activo", "vacaciones");
        model.addAttribute("vacaciones", api.listarVacaciones());
        return "panel/admin/vacaciones";
    }

    @PostMapping("/vacaciones/{id}/resolver")
    public String resolver(@PathVariable Long id, @RequestParam String estado) {
        api.resolverVacacion(id, estado);
        return "redirect:/panel/admin/vacaciones";
    }

    @PostMapping("/vacaciones/{id}/eliminar")
    public String eliminarVacacion(@PathVariable Long id) {
        api.eliminarVacacion(id);
        return "redirect:/panel/admin/vacaciones";
    }
}
