package Modelo;

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase responsable de cargar las credenciales desde un archivo.
 */
public class DatosLogin {
    private final ArrayList<String> credenciales = new ArrayList<>();

    File archivo = new File("src/main/resources/login.txt"); // Archivo con credenciales

    public DatosLogin() throws IOException {
        validacionArchivo(); // Crea el archivo si no existe
        cargarUsuarios();    // Luego carga el contenido
    }

    public ArrayList<String> getCredenciales() {
        return credenciales;
    }

    /**
     * Verifica si el archivo de usuarios existe, y si no, lo crea con un usuario por defecto.
     */
    public void validacionArchivo() throws IOException {
        if (!archivo.exists()) {
            System.out.println("Archivo de usuarios no encontrado. Creando uno nuevo...");
            boolean creado = archivo.createNewFile();
            if (!creado) {
                throw new IOException("No se pudo crear el archivo de usuarios.");
            }

            // Agrega un usuario por defecto "admin,admin123"
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println("admin,admin123");
            }
        }
    }

    /**
     * Lee el archivo usuarios.txt y agrega las líneas válidas a la lista de credenciales.
     */
    // TODO: Abrir archivo usuarios.txt
    // TODO: Leer línea por línea y agregar solo las que contienen ","
    // TODO: Ignorar líneas vacías o mal formateadas

    private void cargarUsuarios() throws IOException {
        if (!archivo.exists()) {
            throw new IOException("No se ha encontrado el archivo");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty() && linea.contains(";")) {
                    String[] partes = linea.split(";");
                    if (partes.length == 2) {
                        credenciales.add(linea); // Ejemplo: "admin,admin123"
                    }
                }
            }
        }
    }
}
