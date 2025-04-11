package proyecto;

import java.io.*;

public abstract class Archivador {
    
    public abstract void CrearArchivo();
    public abstract void GuardarDato(String login, int level, String nombre, String apellido, String correo, String password);
    public abstract String LeerDatoEspecifico(int dato);
    public abstract String BuscarDato(String dato);
    public abstract void EliminarArchivo(File archivo);
    
}
