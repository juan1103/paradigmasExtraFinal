package aero.clases;


import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Ascensor extends Thread {
    private int num;
    private Edificio e;
    private Paso p;
    private int nPlanta;
    private int nPlantaDir;
    private ArrayList<Planta> plantas;
    private ArrayList<Pasajero> personas;
    private JTextField tf;

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


        while (true) { //El ascensor atiende pasajeros hasta que se acaban  y devuelve false


            int personasAtendidas = this.e.getPersonasAtendidas();
            if (personasAtendidas == 60000) {
                System.out.println("Todas las maletas atendidas");
                break;

            }
            p.mirar(num);


            Peticion peticion = e.comprobarPeticiones();//Comprobamos si hay alguna petición de llamada de botón
            peticion.setAscensorEnCurso(this);


            System.out.println("Ascensor va a planta "+peticion.getDirPlanta());
//Cogemos personas

            LinkedList<Pasajero> pasajerosParaMeterAscensor = new LinkedList<>();
            personas.forEach((final Pasajero pasajero)-> System.out.println(persona.getNombre()));
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

        }

        System.out.println("Acaba Ascensor " + this.num);
        tf.setText("Acaba ascensor " + this.num);
        System.out.println("Contenido avión: " +  this.plantas.get(this.nPlanta).getContenidoPlanta().getLista().toString() + " || Cantidad maletas: " + this.plantas.get(this.nPlanta).getContenidoPlanta().getLista().size();

    }




}

