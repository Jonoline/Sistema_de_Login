package ui;


import datos.DatosLogin;
import logica.Login;

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase responsable de interactuar con el usuario por consola.
 * Controla el menú principal y el flujo de login.
 */
public class ConsolaLogin {
    Scanner sc = new Scanner(System.in);
    DatosLogin datos;
    {
        try {
            datos = new DatosLogin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Login login = new Login();


    /**
     * Controla el ciclo principal del menú del sistema.
     */
    public void menu() {
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

    /**
     * Muestra las opciones disponibles para el usuario.
     */
    private void mostrarOpciones() {
        // TODO: Mostrar "1. Iniciar sesión", "2. Salir"
        System.out.println("\n=============================");
        System.out.println("            Inicio de Sesión         ");
        System.out.println("      Opciones      ");
        System.out.println("============================= ");
        System.out.println("    [1] Iniciar Sesión      ");
        System.out.println("    [2] Salir              ");
        System.out.println("============================= ");
        System.out.print("      Opción: ");
    }

    /**
     * Ejecuta la opción seleccionada por el usuario.
     *
     * @param opcion opción ingresada por el usuario
     */
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
            case 1 -> manejarLogin();
            case 2 -> System.out.println("Hasta luego...");
            default -> System.out.println("Opción inválida...");
        }
    }

    /**
     * Solicita usuario y contraseña, y muestra el resultado.
     */
    private void manejarLogin() {
        // TODO: Pedir usuario y contrasena por consola
        // TODO: Llamar a login.autenticar() y mostrar mensaje según resultado

        System.out.print("Ingrese nombre de usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Ingrese contraseña ");
        String contrasena = sc.nextLine();


        if (login.autenticar(usuario, contrasena, datos)) {
            System.out.println("Inicio de sesión correcto ");
        } else {
            System.out.println("Datos incorrectos ");
        }
    }
}