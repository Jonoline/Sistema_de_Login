package Modelo;

/**
 * Representa una tarea individual del usuario.
 */
public class Tarea {
    private boolean finalizada;
    private String descripcion;
    private final Prioridad prioridad;



    /**
     * Constructor que inicializa la descripción de la tarea.
     *
     * @param descripcion contenido de la tarea
     */
    public Tarea(String descripcion, Prioridad prioridad) {
        // TODO: Inicializar descripción
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.finalizada = false;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean estaFinalizada() {
        return finalizada;
    }

    public void marcarFinalizada() {
        finalizada = true;
    }
}
