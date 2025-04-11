/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Daniel
 */
public class Salas extends Archivador{
    int id;
    int localizacion_sala;
    String nombre;
    String descripcion;
    public Salas(){
        this.descripcion="";
        this.id=0;
        this.nombre="";
        this.localizacion_sala=0;
    }
    File Salas;
   //Creador de archivo de usuarios.
    @Override
    public void CrearArchivo() {
        Salas = new File("Salas.txt");
        
        try {
            if (Salas.createNewFile()) {
                System.out.println("[Archivo creado]");
            } else {
                System.out.println("[Archivo existente]");
            }
        } catch (IOException exepcion) {
            exepcion.printStackTrace(System.out);
        }
    }
    //Metodo para leer archivo txt.
    public String LeerArchivoEspecifico() {
        String especificasion[] = new String[5];
        String linea;
        String contenido = "";
        try {
            FileReader lector = new FileReader("Salas.txt");//Indicamos archivo a leer.
            BufferedReader lectura = new BufferedReader(lector);//Le pasamos el archivo a leer.

            while ((linea = lectura.readLine()) != null) {
                String[] bloques = linea.split(";");

                String login = bloques[0];
                int nivel = Integer.parseInt(bloques[1]);
                String nombre = bloques[2];
                String apellido = bloques[3];
                String correo = bloques[4];
                String contrasena = bloques[5];

                //Para pasar una linea de datos a otro archivo.
                for (int i = 0; i < 5; i++) {
                    contenido = contenido + bloques[i] + ";";
                }
            }
            System.out.println("Linea segundaria:" + contenido);
            return contenido;

        } catch (IOException exepcion) {
            exepcion.printStackTrace(System.out);
        }
        return "[NO HAY DATOS]";
    }

    @Override
    public String LeerDatoEspecifico(int dato) {
        String especificasion[] = new String[5];
        String linea;
        String contenido = "";
        int cont = 0;

        try {
            FileReader lector = new FileReader("Salas.txt");//Indicamos archivo a leer.
            BufferedReader lectura = new BufferedReader(lector);//Le pasamos el archivo a leer.

            while ((linea = lectura.readLine()) != null) {
                String[] bloques = linea.split(";");

                int id = Integer.parseInt(bloques[0]);
                String nombre = bloques[1];
                String descripcion = bloques[2];
                String idLocate = bloques[3];
                String contridEntrenador = bloques[4];
                //Para pasar una linea de datos a otro archivo.
                for (int i = 0; i < 5; i++) {
                    contenido = contenido + bloques[i] + ";";
                    especificasion[i] = bloques[i];
                }
            }
            //-----------------------------------------------------
            System.out.println("Salida:" + especificasion[dato]);
            return especificasion[dato];
            //-----------------------------------------------------

        } catch (IOException exepcion) {
            exepcion.printStackTrace(System.out);
        }
        return "[NO HAY DATOS]";
    }

    @Override
    public String BuscarDato(String dato) {
        String linea;
        String valor = dato;
        try (BufferedReader buscador = new BufferedReader(new FileReader("Salas.txt"))) {

            boolean encontrado = false;

            while ((linea = buscador.readLine()) != null) {
                if (linea.contains(dato)) { // Verificar si la línea contiene el dato.
                    System.out.println("Dato encontrado:" + linea);
                    if (valor == dato) {
                        return valor;//Devolver linea si se encuentra el dato.
                    }
                    encontrado = true;
                } else {
                    System.out.println("Dato no encontrado.");
                }
            }

            if (!encontrado) {
                System.out.println("El dato buscado no se encuentra en el archivo.");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
        return null;
    }

    //Guardar datos.
    public void GuardarDato(int id,String nombre, String descripcion, String idLocate, String idEntrenador) {
        try {
            ArchivadorEntrenador datoEnt=new ArchivadorEntrenador();//vincular.
            ArchivadorUsuarios seg = new ArchivadorUsuarios();
            FileWriter principal = new FileWriter("Salas.txt");
            FileWriter segundario = new FileWriter("SalasAuxiliar.txt");

            segundario.write("Dato:\n"+seg.LeerArchivoEspecifico());
            principal.write(id + ";" + nombre + ";" + descripcion + ";" + idLocate +";"+idEntrenador);

            segundario.close();
            principal.close();
            System.out.println("[Cambios guardados]");
        } catch (IOException exepcion) {
            exepcion.printStackTrace();
        }
    }

    //Metodo para eliminar  archivo txt.
    @Override
    public void EliminarArchivo(File archivo) {
        if (archivo.delete()) {
            System.out.println("[Archivo eliminado.]");
        } else {
            System.out.println("[Error al eliminar, Archivo no encontrado.]");
        }
    }

    @Override
    public void GuardarDato(String login, int level, String nombre, String apellido, String correo, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
