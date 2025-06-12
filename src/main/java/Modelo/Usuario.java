package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un usuario del sistema.
 */
public class Usuario {
    private String nombre;
    private String clave;
    private final Perfil perfil;
    private List<Tarea> tareas;

    /**
     * Constructor que inicializa los atributos del usuario.
     *
     * @param nombre nombre del usuario
     * @param clave clave del usuario
     */
    public Usuario(String nombre, String clave, Perfil perfil) {
        // TODO: Inicializar atributos nombre y clave
        this.nombre = nombre;
        this.clave = clave;
        this.perfil = perfil;
        this.tareas= new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

}