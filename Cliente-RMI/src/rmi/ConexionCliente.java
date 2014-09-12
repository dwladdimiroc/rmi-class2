package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi_interface.Tablero;
import rmi_interface.Usuario;

/**
 *
 * @author Daniel Wladdimiro Cottet
 * @title Taller de sistemas distribuidos - Clase 1
 */
public class ConexionCliente {

    private Registry registry;  //Registro de la conexión del usuario con el servidor
    private boolean conectado;  //Estado de conexión del usuario con el servidor
    private Usuario usuario; //Interface necesaria para la comunición con el objecto Usuario del servidor
    private Tablero tablero; //Interface necesaria para la comunición con el objecto Tablero del servidor

    public ConexionCliente() {
        this.conectado = false;
        this.registry = null;
        this.usuario = null;
        this.tablero = null;
    }

    public boolean iniciarRegistro(String IP, int Puerto, String nombreObjetoRemoto) throws RemoteException {
        try {

            //Se le otorga el permiso necesario para poder ejecutar las funciones correspondientes
            java.security.AllPermission allPermision = new java.security.AllPermission();
            System.setProperty("java.security.policy", "rmi.policy");

            //Se inicia RMI-Registry para el registro del objeto
            try {
                //Obtenemos el Registry del servidor RMI
                registry = LocateRegistry.getRegistry(IP, Puerto);

                //De existir algún error con el registro que se desea obtener del servidor, se mostrará un mensaje
            } catch (RemoteException e) {
                System.out.println(IP + ":" + Puerto);
                System.out.println(e.getMessage());
                System.out.println(e.toString());
            }

            //Vamos al Registry y miramos los Objeto que hay en "nombreObjRemoto" para poder usarlo.
            if (nombreObjetoRemoto.equals("UsuarioRemoto")) {
                usuario = (Usuario) registry.lookup(nombreObjetoRemoto);
            } else if (nombreObjetoRemoto.equals("TableroRemoto")) {
                tablero = (Tablero) registry.lookup(nombreObjetoRemoto);
            }

            this.conectado = true;
            return true;

            //De existir algún error con la conexión al servidor, se mostrará un mensaje
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error: No se posee conexión");

            this.conectado = false;
            return false;
        }

    }

    //Getting y Setting de los atributos de ConexionCliente
    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public boolean getConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public Usuario getUsuarioServidor() {
        return usuario;
    }

    public void setUsuarioServidor(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tablero getTableroServidor() {
        return tablero;
    }

    public void setUsuarioServidor(Tablero tablero) {
        this.tablero = tablero;
    }
}
