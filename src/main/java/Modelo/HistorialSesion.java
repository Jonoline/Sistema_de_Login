package Modelo;

import java.time.LocalDateTime;

public class HistorialSesion {
    private final LocalDateTime inicio;
    private int tareasAgregadas;



    public HistorialSesion(LocalDateTime inicio, int tareasAgregadas) {
        this.inicio = inicio;
        this.tareasAgregadas = 0;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public int getTareasAgregadas() {
        return tareasAgregadas;
    }

    public void registrarNuevaTarea() {
        tareasAgregadas++;
    }


}
