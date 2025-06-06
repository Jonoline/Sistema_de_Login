package Modelo;

import java.io.*;

/**
 * Registra nuevos usuarios en login.txt.
 */
public class GestorUsuarios {
    private final File archivo;

    public GestorUsuarios() {
        // TODO: Crear archivo si no existe.
        archivo = new File("src/main/resources/login.txt");
        VerificarArchivo();
    }

    private Boolean VerificarArchivo() {
        try{
            return archivo.createNewFile();
        } catch (IOException e){
            System.out.println("No se pudo crear el archivo" + e.getMessage());
            return false;
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
        // TODO: Verificar si el usuario ya existe
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