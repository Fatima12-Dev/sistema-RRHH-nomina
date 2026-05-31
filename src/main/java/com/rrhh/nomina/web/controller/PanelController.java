package com.rrhh.nomina.web.controller;

import com.rrhh.nomina.web.client.ApiClient;
import com.rrhh.nomina.web.dto.AsistenciaDTO;
import com.rrhh.nomina.web.dto.EmpleadoDTO;
import com.rrhh.nomina.web.dto.VacacionDTO;
import com.rrhh.nomina.web.session.UsuarioSesion;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/panel")
public class PanelController {

    private final ApiClient api;

    public PanelController(ApiClient api) {
        this.api = api;
    }

    @ModelAttribute("usuario")
    public UsuarioSesion usuario(HttpSession session) {
        return (UsuarioSesion) session.getAttribute(UsuarioSesion.SESSION_KEY);
    }

    @GetMapping
    public String inicio(@ModelAttribute("usuario") UsuarioSesion usuario, Model model) {
        model.addAttribute("activo", "inicio");

        List<EmpleadoDTO> empleados = api.listarEmpleados();
        List<AsistenciaDTO> asistencias = api.listarAsistencias();
        List<VacacionDTO> vacaciones = api.listarVacaciones();

        String hoy = LocalDate.now().toString();

        List<AsistenciaDTO> asistHoy = asistencias.stream()
                .filter(a -> hoy.equals(a.getFecha()))
                .toList();
        int presentesHoy = asistHoy.size();
        int horasExtraHoy = asistHoy.stream()
                .mapToInt(a -> a.getHorasExtra() == null ? 0 : a.getHorasExtra())
                .sum();

        List<VacacionDTO> pendientes = vacaciones.stream()
                .filter(v -> "Pendiente".equals(v.getEstado()))
                .toList();

        model.addAttribute("totalEmpleados", empleados.size());
        model.addAttribute("presentesHoy", presentesHoy);
        model.addAttribute("horasExtraHoy", horasExtraHoy);
        model.addAttribute("pendientes", pendientes);

        if (!usuario.esRrhh()) {
            Map<String, Object> recibo = api.obtenerNomina(usuario.getEmpleadoId());
            model.addAttribute("recibo", recibo);
        }

        return "panel/inicio";
    }
}
