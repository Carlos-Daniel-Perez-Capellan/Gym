package proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivadorEntrenador extends Archivador {
    File entrenador;
    //Creador de archivo de entrenadores.
    @Override
    public void CrearArchivo() {
        entrenador = new File("Entrenadores.txt");
        try {
            if (entrenador.createNewFile()) {
                System.out.println("[Archivo de entrenadores creado]");
            } else {
                System.out.println("[Archivo de entrenadores existente]");
            }
        } catch (IOException exepcion) {
            exepcion.printStackTrace(System.out);
        }
    }
    //Guardar dato.
    public void GuardarDato(String id, String nombre, String apellido, String correo, String telefono) {
        try {
            ArchivadorUsuarios seg = new ArchivadorUsuarios();
            FileWriter principal = new FileWriter("Entrenador.txt");
            FileWriter segundario = new FileWriter("EntrenadorAuxiliar.txt");

            segundario.write("Dato:\n"+seg.LeerArchivoEspecifico());
            principal.write(id + ";" + nombre + ";" + apellido + ";" + correo + ";" + telefono + ";" + "\n");

            segundario.close();
            principal.close();
            System.out.println("[Cambios guardados]");
        } catch (IOException exepcion) {
            exepcion.printStackTrace();
        }
    }
    //Metodo para leer archivo txt.
    public String LeerArchivoEspecifico() {
        String especificasion[] = new String[5];
        String linea;
        String contenido = "";
        try {
            FileReader lector = new FileReader("Entrenadores.txt");//Indicamos archivo a leer.
            BufferedReader lectura = new BufferedReader(lector);//Le pasamos el archivo a leer.

            while ((linea = lectura.readLine()) != null) {
                String[] bloques = linea.split(";");

                int id = Integer.parseInt(bloques[0]);
                String nombre = bloques[1];
                String apellido = bloques[2];
                String correo = bloques[3];
                String telefono = bloques[4];

                //Para pasar una linea de datos a otro archivo.
                for (int i = 0; i < 4; i++) {
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
    //
    @Override
    public String LeerDatoEspecifico(int dato) {
        String especificasion[] = new String[5];
        String linea;
        String contenido = "";
        int cont = 0;

        try {
            FileReader lector = new FileReader("Entrenadores.txt");//Indicamos archivo a leer.
            BufferedReader lectura = new BufferedReader(lector);//Le pasamos el archivo a leer.

            while ((linea = lectura.readLine()) != null) {
                String[] bloques = linea.split(";");

                String id = bloques[0];
                
                String nombre = bloques[1];
                String apellido = bloques[2];
                String correo = bloques[3];
                String telefono = bloques[4];
                //Para pasar una linea de datos a otro archivo.
                for (int i = 0; i < 5; i++) {
                    //System.out.print(bloques[i] + ";");
                    contenido = contenido + bloques[i] + ";";
                    especificasion[i] = bloques[i];
                    //System.out.println(especificasion[i]);
                }
            }
            //System.out.println("Conten:" + contenido+"\n");
            //-----------------------------------------------------
            System.out.println("Salida:" + especificasion[dato]);
            return especificasion[dato];
            //-----------------------------------------------------
            //return contenido;

        } catch (IOException exepcion) {
            exepcion.printStackTrace(System.out);
        }
        return "[NO HAY DATOS]";
    }
    //
    @Override
    public String BuscarDato(String dato) {
        String linea;
        String valor = dato;
        try (BufferedReader buscador = new BufferedReader(new FileReader("Entrenadores.txt"))) {

            boolean encontrado = false;

            while ((linea = buscador.readLine()) != null) {
                if (linea.contains(dato)) { // Verificar si la línea contiene el dato.
                    System.out.println("Dato encontrado:" + linea);
                    if (valor == dato) {
                        return valor;//Devolver linea si se encuentra el dato.
                    }
                    encontrado = true;
                    //break; // Salir del bucle si se encuentra el dato.
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
    //
    @Override
    public void EliminarArchivo(File archivo) {
        if (archivo.delete()) {
            System.out.println("[Archivo eliminado.]");
        } else {
            System.out.println("[Error al eliminar, Archivo no encontrado.]");
        }
    }
    //Inactivo, metodo no utilizado.
    @Override
    public void GuardarDato(String login, int level, String nombre, String apellido, String correo, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
