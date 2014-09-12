package cliente;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import rmi.ConexionCliente;
import rmi_interface.Tablero;
import rmi_interface.Usuario;

/**
 *
 * @author Daniel Wladdimiro Cottet
 * @title Taller de sistemas distribuidos - Clase 1
 */
public class Cliente extends JFrame {

    public static int Puerto = 2014;                                 //Número del puerto que está alojado el servidor
    public static String IPServer = "localhost";                      //Dirección IP del servidor, la cual podría utilizarse por defecto el localhost
    public static String usuarioRefRemoto = "UsuarioRemoto";    // Nombre del objeto subido
    public static String tableroRefRemota = "TableroRemoto";    // Nombre del objeto subido
    
    public static JButton botonTamTablero;
    
    public Cliente() {
        setLayout(null);
        botonTamTablero = new JButton("# Piezas");
        botonTamTablero.setBounds(20, 20, 180, 60);
        add(botonTamTablero);
        
        
        this.setTitle("Servidor BD1");
    }

    public static void main(String[] args) {
        Cliente panel = new Cliente();
        panel.setBounds(650, 350, 220, 100);
        panel.setVisible(true);
        
        Usuario usuarioRemoto; //Se crea un nuevo objeto llamado objetoRemoto
        Tablero tableroRemoto;  //Se crea un nuevo objeto llamado tableroRemoto

        //Se instancia el objeto que conecta con el servidor
        ConexionCliente conexion = new ConexionCliente();
        try {
            //Se conecta con el servidor
            if (conexion.iniciarRegistro(IPServer, Puerto, usuarioRefRemoto) && conexion.iniciarRegistro(IPServer, Puerto, tableroRefRemota)) {

                //Se obtiene la referencia al objeto remoto
                usuarioRemoto = conexion.getUsuarioServidor();
                tableroRemoto = conexion.getTableroServidor();

                int opcion = 0;
                while (opcion != 5) {

                    //Escoger alguna opción del menú
                    System.out.println("Menú RMI\n1. Ingresar un usuario al servidor\n2. Ver usuarios del servidor\n3. Editar piezas del tablero\n4. Actualizar piezas tablero\n5. Salir");
                    Scanner sc = new Scanner(System.in);
                    opcion = Integer.parseInt(sc.next());

                    if (opcion == 1) {

                        System.out.println("Ingrese el nombre del usuario: ");
                        sc = new Scanner(System.in);
                        String usuario = sc.next();

                        //Llama a un método del objeto remoto, y se le ingresa un parámetro a éste método
                        boolean ingreso = usuarioRemoto.ingresarUsuario(usuario);
                        if (ingreso) {
                            System.out.println("¡Felicitaciones, ha sido agregado el usuario!");
                        } else {
                            System.out.println("Lamentablemente no ha sido ingresado el usuario, pruebe con otro nombre...");
                        }

                    } else if (opcion == 2) {
                        //Llama a un método del objeto remoto
                        ArrayList<String> usuarios = usuarioRemoto.verUsuarios();

                        for (String usuario : usuarios) {
                            System.out.println("Usuario: " + usuario);
                        }

                    } else if (opcion == 3) {
                        System.out.println("Ingrese cuantas piezas quedan: ");
                        sc = new Scanner(System.in);
                        String piezas = sc.next();
                        
                        tableroRemoto.editarTablero(Integer.parseInt(piezas));
                        System.out.println("Se ha editado las piezas del tablero");
                    } else if (opcion == 4) {
                        botonTamTablero.setText(tableroRemoto.actTablero());
                        System.out.println("Se ha actualizado las piezas del tablero");
                    } else if (opcion != 5) {
                        System.out.println("Ingrese un número válido por favor...");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error... " + e);
        }

        System.exit(0);
    }
}
