package Controlador;

import Modelo.DatosSesion;
import Modelo.GestorUsuarios;
import Modelo.Usuario;

import java.util.Scanner;

/**
 * Representa la sesión de un usuario logueado.
 */
public class SesionActiva {
    private final Usuario usuario;
    private final Scanner sc = new Scanner(System.in);
    private final DatosSesion datosSesion;
    private final GestorUsuarios gestorUsuarios = new GestorUsuarios();


    public SesionActiva(Usuario usuario) {
        this.usuario = usuario;
        this.datosSesion = new DatosSesion(usuario.getNombre());
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
        } while (opcion !=3);
    }
    public void mostrarOpciones() {
        // TODO: Mostrar opciones según si el usuario es admin o no.
        // TODO: Escribir tareas.
        // TODO: Registrar usuarios (solo admin).
        // TODO: Salir de sesión.
        System.out.println("\n=============================");
        System.out.println("  Sesión de: " + usuario.getNombre());
        System.out.println("=============================");
        System.out.println("[1] Escribir tarea");

        if (usuario.getNombre().equals("admin")) {
            System.out.println("[2] Crear nuevo usuario");
            System.out.println("[3] Salir");
        } else {
            System.out.println("[3] Salir");
        }
        System.out.println("=============================");
        System.out.print("Opción: ");
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
        if (usuario.getNombre().equals("admin")) {
            switch (opcion) {
                case 1 -> escribirTarea();
                case 2 -> registrarUsuario();
                case 3 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida");
            }
        } else {
            switch (opcion) {
                case 1 -> escribirTarea();
                case 3 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void escribirTarea() {
        // TODO: Pedir tarea al usuario y delegar a datosSesion.
    }

    private void mostrarTareas() {
        // TODO: Mostrar todas las tareas disponibles
    }

    private void registrarUsuario() {
        // TODO: Usar GestorUsuarios para registrar un nuevo usuario.
        System.out.print("Ingrese nuevo nombre de usuario: ");
        String nuevoUsuario = sc.nextLine();
        System.out.print("Ingrese contraseña: ");
        String nuevaClave = sc.nextLine();

        if (gestorUsuarios.registrar(nuevoUsuario, nuevaClave)) {
            System.out.println("Usuario registrado con éxito.");
        } else {
            System.out.println("Error: El usuario ya existe o hubo un problema.");
        }
    }
}