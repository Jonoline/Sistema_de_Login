package Controlador;

import Modelo.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Representa la sesión de un usuario logueado.
 */
public class SesionActiva {
    private final Usuario usuario;
    private final Scanner sc = new Scanner(System.in);
    private final DatosSesion datosSesion;
    private final GestorUsuarios gestorUsuarios;

    public SesionActiva(Usuario usuario) {
        this.usuario = usuario;
        this.datosSesion = new DatosSesion(usuario.getNombre());
        this.gestorUsuarios = new GestorUsuarios();
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
        } while (opcion !=4);
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
        System.out.println("[3] Mostrar Tareas");


        if (usuario.getNombre().equals("admin")) {
            System.out.println("[2] Crear nuevo usuario");
            System.out.println("[4] Salir");
        } else {
            System.out.println("[4] Salir");
        }
        System.out.println("=============================");
        System.out.print("Opción: ");
    }

    private int obtenerOpcion(int opcion) {
        // TODO: Si es "1" llamar a manejarLogin, si es "2" salir
        try {
            opcion = parseInt(sc.nextLine());
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
                case 3 -> mostrarTareas();
                case 4 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida");
            }
        } else {
            switch (opcion) {
                case 1 -> escribirTarea();
                case 3 -> mostrarTareas();
                case 4 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void escribirTarea() {
        System.out.println("Ingrese una tarea a realizar:");
        String descripcion = sc.nextLine();
        System.out.println("Seleccione prioridad (1-BAJA, 2-MEDIA, 3-ALTA):");
        Prioridad prioridad = obtenerPrioridad();
        datosSesion.EscribirTarea(descripcion, prioridad);
    }

        private Prioridad obtenerPrioridad() {
            while (true) {
                try {
                    int valorPrioridad = Integer.parseInt(sc.nextLine());
                    switch (valorPrioridad) {
                        case 1:
                            return Prioridad.BAJA;
                        case 2:
                            return Prioridad.MEDIA;
                        case 3:
                            return Prioridad.ALTA;
                        default:
                            System.out.println("Seleccione una prioridad válida (1-3):");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido (1-3):");
                }
            }
        }

    private void mostrarTareas() {
        System.out.println("Tareas disponibles:");
        File archivo = new File("src/main/resources/" + usuario.getNombre()+"_todo" + ".txt");

        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(archivo)))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty() && linea.contains(";")) {
                    String[] partes = linea.split(";");
                    if (partes.length == 3) {  // Descripción, Prioridad, Estado
                        String descripcion = partes[0];
                        String prioridad = partes[1];
                        boolean estado = Boolean.parseBoolean(partes[2]);
                        System.out.println("Descripcion "+ descripcion +" Prioridad " + prioridad + " Finalizado=" + estado);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer las tareas: " + e.getMessage());
        }
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