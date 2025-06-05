package Modelo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Registra nuevos usuarios en login.txt.
 */
public class GestorUsuarios {
    private final String archivo = "login.txt";

    public GestorUsuarios() {
        // TODO: Crear archivo si no existe.
    }

    public boolean registrar(String usuario, String clave) {
        // TODO: Agregar usuario al archivo login.txt.
        // Verificar si el usuario ya existe
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(usuario + ";")) {
                    return false; // El usuario ya existe
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer login.txt: " + e.getMessage());
            return false;
        }

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
}