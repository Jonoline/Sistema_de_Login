package Vista;


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
        datos = new DatosLogin();
    }

    private final Login login = new Login();
    private Usuario usuario;


    public ConsolaLogin() {
        menu();
    }

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
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = sc.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = sc.nextLine();

        Usuario usuarioAutenticado = login.autenticar(nombreUsuario, contrasena, datos);

        if (usuarioAutenticado != null) {
            usuario = usuarioAutenticado;
            System.out.println("\nLogin exitoso!");
            System.out.println("Usuario: " + usuario.getNombre());
            System.out.println("Tipo: " + (usuario.esAdmin() ? "Administrador" : "Usuario normal"));
        } else {
            System.out.println("\nError: Usuario o contraseña incorrectos");
        }
    }
}