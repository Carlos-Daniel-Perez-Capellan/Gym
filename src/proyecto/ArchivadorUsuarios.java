package proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivadorUsuarios extends Archivador {
    File usuarios;

    //Creador de archivo de usuarios.
    @Override
    public void CrearArchivo() {
        usuarios = new File("Usuarios.txt");
        
        try {
            if (usuarios.createNewFile()) {
                System.out.println("[Archivo de usuarios creado]");
            } else {
                System.out.println("[Archivo de usuarios existente]");
            }
            //Crear dato de admin.
            FileWriter editor = new FileWriter("Usuarios.txt");
            editor.write("1222792;" + 0 + ";admin;Carlos;Perez;CarlosDaniel1102004@gmail.com");
            editor.close();
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
            FileReader lector = new FileReader("Entrenador.txt");//Indicamos archivo a leer.
            BufferedReader lectura = new BufferedReader(lector);//Le pasamos el archivo a leer.

            while ((linea = lectura.readLine()) != null) {
                String[] bloques = linea.split(";");
                
                String password = bloques[0];
                int nivel = Integer.parseInt(bloques[1]);
                String login=bloques[2];
                String nombre = bloques[3];
                String apellido = bloques[4];
                String correo = bloques[5];
                
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
            FileWriter segundario = new FileWriter("UsuarioAuxiliar.txt");

            segundario.write("Dato:\n"+seg.LeerArchivoEspecifico());
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
