package Modelo;

import java.io.*;

/**
 * Registra nuevos usuarios en login.txt.
 */
public class GestorUsuarios {
    private final String archivo = "login.txt";

    public GestorUsuarios() {
        // TODO: Crear archivo si no existe.
        try {
            // Crear el archivo
            File archivo = new File("login.txt");
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean registrar(String usuario, String clave) {
        // TODO: Agregar usuario al archivo login.txt.
        // Verificar si el usuario ya existe
        if (verificarUsuario(usuario, clave)) {
            // Agregar nuevo usuario
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
                bw.newLine();
                bw.write(usuario + ";" + clave);
                return true;
            } catch (IOException e) {
                System.out.println("No se pudo escribir en login.txt: " + e.getMessage());
                return false;
            }
        }
        return false;
    }


    public boolean verificarUsuario(String usuario, String clave) {
        // TODO: Agregar usuario al archivo login.txt.
        // Verificar si el usuario ya existe
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(usuario + ";")) {
                    System.out.println("El usuario ya existe.");
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer login.txt: " + e.getMessage());
        }
        return true;
    }
}