package aero.clases;


import javax.swing.*;
import java.io.IOException;

public class Ascensor extends Thread {
    private int num;
    private Edificio a;
    private Paso p;
    private boolean pasajerosAsistidos = true;
    private Planta avion;
    private JTextField tf;

    public Ascensor(int num, Edificio a, Paso paso, Planta avion, JTextField tf) {
        this.num = num;
        this.a = a;
        this.p = paso;
        this.avion = avion;
        this.tf = tf;

    }

    public void run() {


        while (pasajerosAsistidos) { //El empleado atiende maletas hasta que se acaban  y devuelve false


            p.mirar(num);
            p.mirarTodos();
            int maletasAtendidas = this.a.getMaletasAtendidas();
            if (maletasAtendidas == 80) {
                System.out.println("Todas las maletas atendidas");

            }
            boolean noParar = a.comprobarNoVacio(this.num);//Comprobamos si hay alguna maleta al que atender

            if (!noParar) {
                return;
            }
            Maleta maletaParaAvion = new Maleta(null, null);
            pasajerosAsistidos = a.salirCinta(this.num, maletaParaAvion); //terminamos de atender


            System.out.println("Empleado numero: " + this.num + " yendo al avión con maleta: " + maletaParaAvion.toString());
            tf.setText("Empleado numero: " + this.num + " yendo al avión con maleta: " + maletaParaAvion.toString());

            long tAleatorio1 = 400 + (int) ((300) * Math.random());


            try {
                Thread.sleep(tAleatorio1);
                p.mirar(num);
                p.mirarTodos();
                this.avion.meterPasajero(maletaParaAvion, this.num, this.tf);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


        }

        System.out.println("Acaba Empleado " + this.num);
        tf.setText("Acaba Empleado " + this.num);
        System.out.println("Contenido avión: " + this.avion.getContenidoPlanta().getLista().toString() + " || Cantidad maletas: " + this.avion.getContenidoPlanta().getLista().size());

    }
}

