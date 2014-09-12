package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interface.Tablero;

/**
 *
 * @author Daniel Wladdimiro Cottet
 * @title Taller de sistemas distribuidos - Clase 1
 */

public class ImplementacionTablero extends UnicastRemoteObject implements Tablero {
    
    static int tamanoTablero;
    
    static Logger logger;

    public ImplementacionTablero() throws RemoteException {
        logger = Logger.getLogger(getClass().getName());
        logger.log(Level.INFO, "Se ha instanciado la clase de Implementacion del Servidor");
        tamanoTablero = 100;
    }

    /*
     * Debo escribir todos los métodos que se encuentran en la interface
     */
    // Por cada metodo se escribe Override que se utiliza para que utilize este metodo en vez del metodo del padre
    @Override
    public void editarTablero(int tamanoTablero) throws RemoteException {
        //Se cambia el tamano del tablero,
        //el cual se debe tratar con "ImplementacionTablero"
        //y no con "this". Esto se debe a que es una variable
        //estática, de tal manera que al inicializar la clase
        //ImplementacionTablero siempre se utilice este valor.
        ImplementacionTablero.tamanoTablero = tamanoTablero;
    }

    @Override
    public String actTablero() throws RemoteException {
        return Integer.toString(tamanoTablero);
    }

}
