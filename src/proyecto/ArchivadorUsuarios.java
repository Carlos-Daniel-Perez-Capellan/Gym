package proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivadorUsuarios extends Archivador {

    File entrenador;
    File usuarios;

    //Creador de archivo.
    @Override
    public void CrearArchivo() {
        usuarios = new File("Usuarios.txt");
        entrenador = new File("Entrenadores.txt");
        try {
            //Creador de archivo de usuarios.
            if (usuarios.createNewFile()) {
                System.out.println("[Archivo de usuarios creado]");
            } else {
                System.out.println("[Archivo de usuarios existente]");
            }
            //Creador de archivo de entrenadores.
            if (entrenador.createNewFile()) {
                System.out.println("[Archivo de entrenadores creado]");
            } else {
                System.out.println("[Archivo de entrenadores existente]");
            }
            //Crear dato de admin.
            FileWriter editor = new FileWriter("Usuarios.txt");
            editor.write("110;" + 0 + ";Admin;Capellan;CarlosDaniel@gmail.com;1222792;");
            editor.close();

        } catch (IOException exepcion) {
            exepcion.printStackTrace(System.out);
        }
    }

    //Metodo para leer archivo txt.
    private String LeerArchivoEspecifico() {
        String especificasion[] = new String[5];
        String linea;
        String contenido = "";
        try {
            FileReader lector = new FileReader("Usuarios.txt");//Indicamos archivo a leer.
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
                    System.out.println(contenido);
                    contenido = contenido + bloques[i] + ";";
                }
                
            }
            System.out.println("Linea segundaria:"+contenido);
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
            FileReader lector = new FileReader("Usuarios.txt");//Indicamos archivo a leer.
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

    //Buscar dato en el archivo"Usuarios".
    @Override
    public String BuscarDato(String dato) {
        String linea;
        String valor = dato;
        try (BufferedReader buscador = new BufferedReader(new FileReader("Usuarios.txt"))) {

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

    //Guardar datos.
    @Override
    public void GuardarDato(String login, int level, String nombre, String apellido, String correo, String password) {
        try {
            ArchivadorUsuarios seg = new ArchivadorUsuarios();
            FileWriter principal = new FileWriter("Usuarios.txt");
            FileWriter segundario = new FileWriter("Auxiliar.txt");

            segundario.write(seg.LeerArchivoEspecifico());
            principal.write(login + ";" + level + ";" + nombre + ";" + apellido + ";" + correo + ";" + password + ";" + "\n");

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

}
