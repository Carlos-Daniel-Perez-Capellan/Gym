package proyecto;

public class ZonaDePruebas {

    public static void main(String[] args) {
        ArchivadorUsuarios usu = new ArchivadorUsuarios();
        ArchivadorEntrenador entre = new ArchivadorEntrenador();
        MenuDeInteraccion menu = new MenuDeInteraccion();

        //usu.GuardarDato("Dinael", 0, "Carlos", "Perez", "Carlos@gmail.com", "1222792");
        //for(int i=0;i<5;i++)
        //entre.LeerArchivoEspecifico();
        //usu.LeerArchivoEspecifico();
        menu.comprobante(1);
    }
}
