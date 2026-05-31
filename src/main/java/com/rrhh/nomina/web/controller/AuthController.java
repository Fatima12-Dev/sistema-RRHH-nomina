package com.rrhh.nomina.web.controller;

import com.rrhh.nomina.web.client.ApiClient;
import com.rrhh.nomina.web.dto.EmpleadoDTO;
import com.rrhh.nomina.web.session.UsuarioSesion;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final ApiClient api;

    public AuthController(ApiClient api) {
        this.api = api;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("empleados", api.listarEmpleados());
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String rol,
                        @RequestParam(required = false) Long empleadoId,
                        HttpSession session,
                        Model model) {
        if ("RRHH".equals(rol)) {
            session.setAttribute(UsuarioSesion.SESSION_KEY,
                    new UsuarioSesion("RRHH", null, "Recursos Humanos"));
            return "redirect:/panel";
        }

        if (empleadoId == null) {
            model.addAttribute("empleados", api.listarEmpleados());
            model.addAttribute("error", "Debes seleccionar un empleado.");
            return "login";
        }

        try {
            EmpleadoDTO emp = api.obtenerEmpleado(empleadoId);
            session.setAttribute(UsuarioSesion.SESSION_KEY,
                    new UsuarioSesion("EMPLEADO", emp.getId(), emp.getNombre()));
            return "redirect:/panel";
        } catch (Exception e) {
            model.addAttribute("empleados", api.listarEmpleados());
            model.addAttribute("error", "Empleado no valido.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/panel";
    }
}
