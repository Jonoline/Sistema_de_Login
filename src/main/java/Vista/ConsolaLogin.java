package Vista;


import Controlador.SesionActiva;
import Modelo.DatosLogin;
import Controlador.Login;
import Modelo.Perfil;
import Modelo.Usuario;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Clase responsable de interactuar con el usuario por consola.
 * Controla el menú principal y el flujo de login.
 */
public class ConsolaLogin {
    Scanner sc = new Scanner(System.in);
    private final DatosLogin datos;
    {
        try {
            datos = new DatosLogin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final Login login = new Login();
    private Usuario usuario;


    /**
     * Controla el ciclo principal del menú del sistema.
     */
    public void menu() {
        // TODO: Mostrar mensaje de bienvenida y manejar login
        System.out.println("\n  ===========================================");
        System.out.println("     Sistema de Login con Tareas Personales        ");
        System.out.println("              Inicio de Sesión     ");
        System.out.println("  ============================================= ");
        do {
            manejarLogin();
        } while (true);
    }

    /**
     * Solicita usuario y contraseña, y muestra el resultado.
     */
    private void manejarLogin() {
        // TODO: Pedir usuario y contrasena por consola
        // TODO: Llamar a login.autenticar() y mostrar mensaje según resultado
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = sc.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = sc.nextLine();
        System.out.print("Ingrese correo electrónico: ");
        String correo = sc.nextLine();


        if (login.autenticar(nombreUsuario, contrasena, datos)) {
            System.out.println("\nInicio de sesión correcto");
            this.usuario = new Usuario(nombreUsuario, contrasena, new Perfil(correo, LocalDateTime.now())); // Crear usuario
            SesionActiva sesion = new SesionActiva(usuario);       // Pasar usuario
            sesion.menuSesion();
        } else {
            System.out.println("\nDatos incorrectos");
        }
    }
}