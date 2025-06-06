package Modelo;

import java.io.*;
import java.util.ArrayList;

/**
 * Clase encargada de manejar las tareas de un usuario autenticado.
 */
public class DatosSesion {
    private final File archivo;
    private final ArrayList<Tarea> tareas = new ArrayList<>();

    /**
     * Constructor que carga las tareas desde archivo.
     *
     * @param usuario nombre del usuario
     */
    public DatosSesion(String usuario) {
        // TODO: Cargar tareas desde archivo <usuario>_todo.txt
        this.archivo = new File("src/main/resources/"+ usuario + "_todo.txt");
        VerificarArchivo();
        CargarTarea();
    }

    private Boolean VerificarArchivo() {
        try{
            if(!archivo.exists()){
            return archivo.createNewFile();
        }
            return true;
        } catch (IOException e){
            System.out.println("No se pudo crear el archivo" + e.getMessage());
            return false;
        }
    }

    private void GuardarTarea(){
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))){
            for( Tarea t : tareas){
                escritor.write(t.getDescripcion());
                escritor.newLine();
            }
        } catch (IOException e){
            System.out.println("error al ingresar tarea" + e.getMessage());

        }
    }

    public void EscribirTarea(String tarea){
        tareas.add(new Tarea(tarea));
        GuardarTarea();
    }

    public void CargarTarea() {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                tareas.add(new Tarea(linea));
            }
        } catch (IOException e) {
            System.out.println("error al leer el archivo" + e.getMessage());
        }
    }
        /**
         * Devuelve la lista de tareas.
         *
         * @return lista de tareas
         */

        public ArrayList<Tarea> getTareas() {
        return tareas;
    }
}