package com.rrhh.nomina.web.controller;

import com.rrhh.nomina.web.client.ApiClient;
import com.rrhh.nomina.web.dto.AsistenciaDTO;
import com.rrhh.nomina.web.dto.VacacionDTO;
import com.rrhh.nomina.web.session.UsuarioSesion;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/panel")
public class EmpleadoViewController {

    private final ApiClient api;

    public EmpleadoViewController(ApiClient api) {
        this.api = api;
    }

    @ModelAttribute("usuario")
    public UsuarioSesion usuario(HttpSession session) {
        return (UsuarioSesion) session.getAttribute(UsuarioSesion.SESSION_KEY);
    }

    @GetMapping("/mis-datos")
    public String misDatos(@ModelAttribute("usuario") UsuarioSesion usuario, Model model) {
        if (usuario.esRrhh()) return "redirect:/panel";
        model.addAttribute("activo", "misdatos");
        model.addAttribute("empleado", api.obtenerEmpleado(usuario.getEmpleadoId()));
        model.addAttribute("vacaciones", api.vacacionesPorEmpleado(usuario.getEmpleadoId()));
        return "panel/mis-datos";
    }

    @GetMapping("/mi-recibo")
    public String miRecibo(@ModelAttribute("usuario") UsuarioSesion usuario, Model model) {
        if (usuario.esRrhh()) return "redirect:/panel";
        model.addAttribute("activo", "recibo");
        model.addAttribute("recibo", api.obtenerNomina(usuario.getEmpleadoId()));
        return "panel/mi-recibo";
    }

    @GetMapping("/mi-asistencia")
    public String miAsistencia(@ModelAttribute("usuario") UsuarioSesion usuario, Model model) {
        if (usuario.esRrhh()) return "redirect:/panel";
        model.addAttribute("activo", "miasistencia");
        model.addAttribute("asistencias", api.asistenciasPorEmpleado(usuario.getEmpleadoId()));
        model.addAttribute("hoy", LocalDate.now().toString());
        return "panel/mi-asistencia";
    }

    @PostMapping("/mi-asistencia")
    public String registrarAsistencia(@ModelAttribute("usuario") UsuarioSesion usuario,
                                      @RequestParam String fecha,
                                      @RequestParam Integer horasTrabajadas,
                                      @RequestParam Integer horasExtra) {
        if (usuario.esRrhh()) return "redirect:/panel";
        AsistenciaDTO a = new AsistenciaDTO();
        a.setEmpleadoId(usuario.getEmpleadoId());
        a.setFecha(fecha);
        a.setHorasTrabajadas(horasTrabajadas);
        a.setHorasExtra(horasExtra);
        api.registrarAsistencia(a);
        return "redirect:/panel/mi-asistencia";
    }

    @PostMapping("/mis-vacaciones")
    public String solicitarVacaciones(@ModelAttribute("usuario") UsuarioSesion usuario,
                                      @RequestParam String fechaInicio,
                                      @RequestParam String fechaFin) {
        if (usuario.esRrhh()) return "redirect:/panel";
        VacacionDTO v = new VacacionDTO();
        v.setEmpleadoId(usuario.getEmpleadoId());
        v.setFechaInicio(fechaInicio);
        v.setFechaFin(fechaFin);
        api.crearVacacion(v);
        return "redirect:/panel/mis-datos";
    }
}
