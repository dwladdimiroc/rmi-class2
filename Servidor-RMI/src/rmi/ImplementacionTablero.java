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
    
    public int tamanoTablero = 100;
    
    static Logger logger;

    public ImplementacionTablero() throws RemoteException {
        logger = Logger.getLogger(getClass().getName());
        logger.log(Level.INFO, "Se ha instanciado la clase de Implementacion del Servidor");
    }

    /*
     * Debo escribir todos los métodos que se encuentran en la interface
     */
    // Por cada metodo se escribe Override que se utiliza para que utilize este metodo en vez del metodo del padre
    @Override
    public int tamanoTablero() throws RemoteException {
        logger.log(Level.INFO, "Se desea ver el tama~no del tablero al servidor");
        return tamanoTablero;
    }

}
