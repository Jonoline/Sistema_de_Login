package Modelo;

import java.time.LocalDateTime;

public class Perfil {
    private final String correo;
    private final LocalDateTime fechaCreacion;


    public Perfil(String correo, LocalDateTime fechaCreacion) {
        this.correo = correo;
        this.fechaCreacion = LocalDateTime.now();
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getCorreo() {
        return correo;
    }
}
