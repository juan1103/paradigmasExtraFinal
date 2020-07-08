package aero.clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread {
    private Socket conexion;
    private Paso p;
    private String datos = "";
    private ServerSocket servidor;
    private boolean condicion;

    public Servidor() {

    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public void run() {
        try {
            System.out.println("Creamos servidor con hilo!");
            servidor = new ServerSocket(5000);
            DataOutputStream salida;
            DataInputStream entrada;
            while (true) {
                condicion = true;
                conexion = servidor.accept();

                salida = new DataOutputStream(conexion.getOutputStream());
                entrada = new DataInputStream(conexion.getInputStream());

                System.out.println("Cliente se conecta");

                while (condicion == true) {
                    String mensaje = entrada.readUTF();
                    if (mensaje.equals("seguir")) {// continuarmeos enviando  datos a ese cliente
                        salida.writeUTF(datos);  // Respondemos
                    } else {
                        //cuando el mensaje cambie, el servidor volvera a aceptar otra conexion
                        condicion = false;
                        salida.writeUTF(datos);
                        conexion.close();
                    }
                }


            }


        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



