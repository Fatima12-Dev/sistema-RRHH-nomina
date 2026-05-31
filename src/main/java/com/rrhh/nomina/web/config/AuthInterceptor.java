package com.rrhh.nomina.web.config;

import com.rrhh.nomina.web.session.UsuarioSesion;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        UsuarioSesion usuario = (UsuarioSesion) request.getSession()
                .getAttribute(UsuarioSesion.SESSION_KEY);

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        if (request.getRequestURI().contains("/panel/admin") && !usuario.esRrhh()) {
            response.sendRedirect(request.getContextPath() + "/panel");
            return false;
        }
        return true;
    }
}
