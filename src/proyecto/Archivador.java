package proyecto;

import java.io.*;

public class Archivador {
    File archivo;
    
    //Creador de archivo.
    public void CrearArchivo(){
        archivo=new File("Usuarios.txt");
        try{
            if(archivo.createNewFile()){
                System.out.println("[Archivo creado]");
            }else{
                System.out.println("[ERROR, archivo existente]");
            }
        }catch(IOException exepcion){
            exepcion.printStackTrace(System.out);
        }
    }
    //Editor de archivo.
    public void EditarArchivo(String nombre,String apellido,String email, String id){
        try{
            FileWriter editor=new FileWriter("Usuario.txt");
            editor.write(nombre);
            editor.write(apellido);
            editor.write(email);
                        
        }catch(IOException exepcion){
            exepcion.printStackTrace();
        }
    }
}
