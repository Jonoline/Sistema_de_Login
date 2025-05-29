package Modelo;

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase responsable de cargar las credenciales desde un archivo.
 */
public class DatosLogin {
    private final String archivo = "login.txt";

    public ArrayList<String> getCredenciales() {
        return credenciales;
    }

    private final ArrayList<String> credenciales = new ArrayList<>();

    public DatosLogin() {
        try {
            crearArchivoSiNoExiste();
            cargarUsuarios("src/main/login.txt"); // valor por defecto
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void crearArchivoSiNoExiste() {
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


    /**
     * Lee el archivo login.txt y agrega las líneas válidas a la lista de credenciales.
     */
    private void cargarUsuarios(String rutaArchivo)throws IOException {
    // TODO: Abrir archivo login.txt
    // TODO: Leer línea por línea y agregar solo las que contienen ";"
    // TODO: Ignorar líneas vacías o mal formateadas
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim(); // eliminar espacios al inicio y al final
                // Verificar que la línea tenga un ";"
                if (linea.contains(";")) {
                    String[] partes = linea.split(";");
                    if (partes.length == 2 && !partes[0].isEmpty() && !partes[1].isEmpty()) {
                        credenciales.add(linea); // guardar las líneas válidas
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException("No se ha encontrado el archivo" );
        }
    }

}