import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase responsable de cargar las credenciales desde un archivo.
 */
public class DatosLogin {
    public ArrayList<String> credenciales = new ArrayList<>();

    /**
     * Constructor que inicializa las credenciales desde el archivo.
     */
    public DatosLogin() {
        cargarUsuarios();
    }

    /**
     * Lee el archivo login.txt y agrega las líneas válidas a la lista de credenciales.
     */
    private void cargarUsuarios() {
    // TODO: Abrir archivo login.txt
    // TODO: Leer línea por línea y agregar solo las que contienen ";"
    // TODO: Ignorar líneas vacías o mal formateadas
        try (BufferedReader lector = new BufferedReader(new FileReader("src/login.txt"))) {
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
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

    }

}