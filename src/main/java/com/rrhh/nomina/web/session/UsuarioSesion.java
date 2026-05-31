package com.rrhh.nomina.web.session;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioSesion {
    public static final String SESSION_KEY = "usuarioSesion";

    private String rol;
    private Long empleadoId;
    private String nombre;

    public boolean esRrhh() {
        return "RRHH".equals(rol);
    }
}
