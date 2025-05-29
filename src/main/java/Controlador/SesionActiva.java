package Controlador;

import Modelo.DatosSesion;
import Modelo.GestorUsuarios;

import java.util.Scanner;

/**
 * Representa la sesión de un usuario logueado.
 */
public class SesionActiva {
    Scanner sc = new Scanner(System.in);
    private final String usuario;
    private final Scanner scanner = new Scanner(System.in);
    private final DatosSesion datosSesion;
    private final GestorUsuarios usuarios = new GestorUsuarios();


    public SesionActiva(String usuario) {
        this.usuario = usuario;
        this.datosSesion = new DatosSesion(usuario);
    }

    /**
     * Ciclo de operaciones disponibles en sesión.
     */

    public void menuSesion() {
        // TODO: Implementar ciclo del menú principal
        int opcion;
        do {
            mostrarOpciones();
            opcion = obtenerOpcion(0);
            if (opcion != -1) {
                ejecutarOpcion(opcion);
            }
        } while (opcion != 2);
    }
    public void mostrarOpciones() {
        // TODO: Mostrar opciones según si el usuario es admin o no.
        // TODO: Escribir tareas.
        // TODO: Registrar usuarios (solo admin).
        // TODO: Salir de sesión.
        System.out.println("\n=============================");
        System.out.println("            Opciones Admin        ");
        System.out.println("      Opciones      ");
        System.out.println("============================= ");
        System.out.println("    [1] Escribir tareas      ");
        System.out.println("    [2] Crear Usuario      ");
        System.out.println("    [3] Salir              ");
        System.out.println("============================= ");
        System.out.print("      Opción: ");
    }
    private int obtenerOpcion(int opcion) {
        // TODO: Si es "1" llamar a manejarLogin, si es "2" salir
        try {
            opcion = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido.");
            return -1; // Devolver un valor inválido para que no ejecute ninguna opción
        }
        return opcion;
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> escribirTarea();
            case 2 -> registrarUsuario();
            case 3 -> System.out.println("Hasta luego...");
            default -> System.out.println("Opción inválida...");
        }
    }

    private void escribirTarea() {
        // TODO: Pedir tarea al usuario y delegar a datosSesion.
    }

    private void registrarUsuario() {
        // TODO: Usar GestorUsuarios para registrar un nuevo usuario.
    }
}