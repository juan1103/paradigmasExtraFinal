package aero.clases;


import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ascensor extends Thread {
    private int num;
    private Edificio e;
    private Paso p;
    private int nPlanta;
    private int nPlantaDir;
    private ArrayList<Planta> plantas;
    private ArrayList<Pasajero> personas;
    private JTextField tf;
    private LinkedList<Peticion> peticiones;
    private Lock c = new ReentrantLock(true);
    private Condition esperaLleno = c.newCondition();
    private Condition esperaVacio = c.newCondition();


    public Ascensor(int num, Edificio a, Paso paso, ArrayList<Planta> plantas, JTextField tf, ArrayList<Pasajero> personas;) {
        this.num = num;
        this.e = a;
        this.p = paso;
        this.plantas = plantas;
        this.tf = tf;
        this.nPlanta=0;
        this.nPlantaDir=0;
        this.personas=personas;


    }

    public void run() {


        while (true) {
//IR a planta


            p.mirar(num); // Solo 2 ascensores a la vez
            LlamadaBoton llamada = this.comprobarPeticionesDePlanta();//Comprobamos si hay alguna petición de llamada de botón


            
            System.out.println("Ascensor va a planta "+llamada.getPlanta();


//Cogemos personas
            System.out.println("Está en planta "+llamada.getPlanta();
            LinkedList<Pasajero> pasajerosParaMeterAscensor = new LinkedList<>();





            for (int i = 0; i < this.peticiones.size(); ++i) {



            }




            try {
                Thread.sleep(Math.abs(5*(peticion.getDirPlanta()-peticion.getOriPlanta())));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            System.out.println("Llega a planta "+peticion.getDirPlanta());

        //Dejamos personas
            for (int i = 0; i < 7; i++) {
                Pasajero pasajero = this.personas.remove(0);
                plantas.get(peticion.getDirPlanta()).getContenidoPlanta().getLista().add(pasajero);


            }
        System.out.println("Acaba Ascensor " + this.num);
        tf.setText("Acaba ascensor " + this.num);
        System.out.println("Contenido avión: " +  this.plantas.get(this.nPlanta).getContenidoPlanta().getLista().toString() + " || Cantidad maletas: " + this.plantas.get(this.nPlanta).getContenidoPlanta().getLista().size();
        }



}
    public Peticion  comprobarPeticionesDeDir() {
        c.lock();
        try {




            if (this.peticiones.get(0).equals(null)) {
                try {
                    esperaVacio.await();
                } catch (InterruptedException ex) {
                }
            }

            /*
            String juntos = juntar();
            threadServidor.setDatos(juntos);
             */

            return peticiones.removeFirst();



        } finally {
            c.unlock();
        }
    }

    public LlamadaBoton comprobarPeticionesDePlanta () {
        c.lock();
        try {


            if (this.e.getLlamadasBoton().isEmpty()) {
                try {
                    esperaVacio.await();
                } catch (InterruptedException ex) {
                }


            }
            return e.getLlamadasBoton().remove(0);


        } finally {
            c.unlock();
        }
    }





}

