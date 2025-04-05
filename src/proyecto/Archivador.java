package proyecto;

import java.io.*;

public class Archivador {

    File archivo;

    //Creador de archivo.
    public void CrearArchivo() {
        archivo = new File("Usuarios.txt");

        try {
            if (archivo.createNewFile()) {
                System.out.println("[Archivo creado]");
            } else {
                System.out.println("[ERROR, archivo existente]");
            }
            FileWriter editor = new FileWriter("Usuarios.txt");
            editor.write(1010+":;Admin:;CarlosDaniel@gmail.com:;829-539-3818:;"+0);

            editor.close();
            System.out.println("[Cambios guardados]");

        } catch (IOException exepcion) {
            exepcion.printStackTrace(System.out);
        }
    }

    //Editor de archivo.
    public void EditarArchivo(String nombre, String apellido, String email, String login, String password,int level) {
        try {
            Archivador delete=new Archivador();
            FileWriter editor = new FileWriter("Usuarios.txt");
            FileWriter copia=new FileWriter("Copia.txt");
            PrintWriter edit =new PrintWriter(editor);
            PrintWriter cop =new PrintWriter(copia);
            
            
            cop.println(login + ":;"+level+ ":;" + nombre + ":;" + apellido + ":;" + email + ":;" + password + ":;");
            delete.Eliminararchivo();
            edit.println(login + ":;"+level+ ":;" + nombre + ":;" + apellido + ":;" + email + ":;" + password + ":;");
            
            //editor.write(login + ":;"+level+ ":;" + nombre + ":;" + apellido + ":;" + email + ":;" + password + ":;");

            editor.close();
            System.out.println("[Cambios guardados]");
        } catch (IOException exepcion) {
            exepcion.printStackTrace();
        }
    }
     //Metodo para eliminar  archivo txt.
    public void Eliminararchivo() {
        if (archivo.delete()) {
            System.out.println("[Archivo eliminado.]");
        } else {
            System.out.println("[Error al eliminar, Archivo no encontrado.]");
        }
    }
     
    //Buscar dato en el archivo.
    public String BuscarDato(String dato) {
        String datoBuscado = dato;
        try (BufferedReader buscador = new BufferedReader(new FileReader("Usuarios.txt"))) {
            String linea;

            boolean encontrado = false;

            while ((linea = buscador.readLine()) != null) {
                if (linea.contains(datoBuscado)) { // Verificar si la línea contiene el dato
                    System.out.println("Dato encontrado: " + linea);
                    encontrado = true;
                    //break; // Salir del bucle si se encuentra el dato
                    return linea;
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
}