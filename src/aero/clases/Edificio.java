package aero.clases;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Edificio {


    private ArrayList<Integer> empleadosPos = new ArrayList(Arrays.asList(null, null, null, null, null, null, null, null));
    private LinkedList<Peticion> peticiones = new LinkedList<>();

    public int getPersonasAtendidas() {
        return personasAtendidas;
    }

    public void setPersonasAtendidas(int personasAtendidas) {
        this.personasAtendidas = personasAtendidas;
    }

    private int personasAtendidas;


    private ArrayList<JTextField> mls;
    private Servidor threadServidor;

    private int esperandoCinta, pasandoCinta = 0, maletasAtendidas = 0;
    private Semaphore cintaSemaphore = new Semaphore(8);
    private Lock c = new ReentrantLock(true);
    private Condition esperaLleno = c.newCondition();
    private Condition esperaVacio = c.newCondition();
    private Log log;
    private ColaPasajeros colaPasajeros;
    private ArrayList<Planta> plantas;

    public Edificio(Log log, ArrayList<JTextField> mls, Servidor threadServidor, ArrayList<Planta> plantas) {
        this.log = log;
        this.colaPasajeros = new ColaPasajeros();
        this.mls = mls;
        this.threadServidor = threadServidor;
        this.plantas = plantas;
    }

    //Funcion que simula la llegada de un pasajero a la cola para dejar la maleta
    public void llegarCinta(int num) {
        c.lock();
        try {
            colaPasajeros.meter(num, log);
            esperandoCinta++;
            String juntos = juntar();
            //Cada vez que entra un nuevo pasajero actualizamos los datos que compartira el servidor
            threadServidor.setDatos(juntos);
            System.out.println("Pasajero: " + num + " entra en la cola para dejar la maleta");
            System.out.println("-------------------------------------------");
            System.out.println("Pasajeros esperando: " + esperandoCinta);

            //Espera hasta que haya un hueco libre
            while (huecoLibre() == -1) {
                try {
                    esperaLleno.await();
                } catch (InterruptedException ex) {
                }
            }


            System.out.println("Pasajero: " + num + " sale de la cinta");
            System.out.println("-------------------------------------------");
            System.out.println("Pasajeros esperando: " + esperandoCinta);


        } finally {
            c.unlock();
        }
    }

    //Funcion que simula la entrada de un pasajero a la cinta de equipaje
    public void pasarCinta(Maleta maleta) {
        c.lock();
        try {
            colaPasajeros.sacar(maleta.getNumPasajero(), log);

            esperandoCinta--;
            try {
                cintaSemaphore.acquire(); //Ocupo un hueco en la cinta
            } catch (InterruptedException e) {
            }


            pasandoCinta++;
            int posMaleta = huecoLibre();
            this.maletas.set(posMaleta, maleta); //Mete en primer hueco al pasajero


            mls.get(posMaleta).setText(String.valueOf(maleta.getIdMaleta()));
            String juntos = juntar();
            //Cada vez que entra un pasajero a un hueco de cinta actualizamos los datos del servidor
            threadServidor.setDatos(juntos);
            esperaVacio.signalAll();
            System.out.println("-------------------------------------------");

            log.agregarOperacion("Maleta de pasajero: " + maleta.getNumPasajero() + " entra a posicion de la cinta " + posMaleta + "\n");

            System.out.println("Maleta de pasajero: " + maleta.getNumPasajero() + " entra a posicion de la cinta: " + posMaleta);
            System.out.println("Maletas en cinta: ");


            for (int i = 0; i < maletas.size(); ++i) {
                if (maletas.get(i) != null)

                    System.out.print(maletas.get(i).getIdMaleta() + " ");
                else {
                    System.out.println("La posicion: " + i + " esta vacia ");
                }

            }
            System.out.print("\n");


        } catch (IOException ex) {
        } finally {
            c.unlock();
        }

    }

    //Comprueba si hay peticiones de plantas de boton
    public Peticion comprobarPeticiones() {
        c.lock();
        try {




            if (peticiones.get(0).equals(null)) {
                try {
                    esperaVacio.await();
                } catch (InterruptedException ex) {
                }
            }


            String juntos = juntar();
            threadServidor.setDatos(juntos);
            return peticiones.removeFirst();

        } finally {
            c.unlock();
        }
    }


    //Funcion que simula la salida de la cinta  y el fin de la atencion a un pasjero por parte de un empleado
    public boolean salirCinta(int num, Maleta maletaParaAvion) {
        c.lock();
        try {

            if (maletasAtendidas >= 80) { //Cuando acaben de atender las 80 maletas, en el caso de que haya algún empleado dormido, se despierta
                esperaVacio.signalAll();
                return false;   //se retorna false para que el hilo empleado dueño salga del while y acabe su ejecución
            }

            int posMaleta = empleadosPos.indexOf(num);

            int m = maletas.get(posMaleta).getNumPasajero();
            System.out.println(num);
            Maleta maleta = this.maletas.get(posMaleta);
            this.maletas.set(posMaleta, null);
            maletas.set(posMaleta, null); //Mete en primer hueco ocupado el null para quitar la maleta

            System.out.println("Sacando maleta " + maleta.getIdMaleta());


            mls.get(posMaleta).setText("");

            empleadosPos.set(posMaleta, null);

            String juntos = juntar();
            //Cada vez que sale una maleta y un empleado termina de atender se actualizan los datos del servidor
            threadServidor.setDatos(juntos);

            cintaSemaphore.release(); //Libero un hueco de la cinta

            pasandoCinta--;

            System.out.println("-------------------------------------------");

            log.agregarOperacion("Empleado " + num + " ha recogido la maleta en posicion " + posMaleta + "atendiendo al pasajero " + m + " ms\n");


            System.out.println("Se atiende la Maleta en posicion: " + posMaleta);
            System.out.println("Número de maletas en cinta: " + pasandoCinta);

            System.out.print("\n" + "--------------------------------------");


            System.out.println("Maletas en cinta: ");


            for (int i = 0; i < maletas.size() - 1; ++i) {
                if (maletas.get(i) != null)

                    System.out.print(maletas.get(i).getIdMaleta() + " ");

            }
            System.out.print("\n" + "--------------------------------------" + "\n");


            System.out.println("Empleados en cinta: " + empleadosPos.toString());


            System.out.println("----------------------------------------------------------- Contenido del avion: ");


            for (int i = 0; i < avion.getContenidoPlanta().getLista().size(); ++i) {
                if (avion.getContenidoPlanta().getLista() != null)

                    System.out.print(avion.getContenidoPlanta().getLista().get(i).getIdMaleta() + " ");


            }

            System.out.println("Cantidad de maletas: " + avion.getContenidoPlanta().getLista().size());


            System.out.print("\n" + "------------------------------------------------------");


            maletasAtendidas++;

            maletaParaAvion.setIdMaleta(maleta.getIdMaleta());
            maletaParaAvion.setNumPasajero(maleta.getNumPasajero());


            esperaLleno.signal();

            if (maletasAtendidas >= 80) {
                esperaVacio.signalAll();
                return false;
            }

        } catch (IOException ex) {
        } finally {
            c.unlock();
        }

        return true;

    }

    public int huecoLibre() { //Funcion que devuelve el primer hueco libre en la cinta de maletas


        int i;
        int hueco = -1;

        for (i = 7; i >= 0; i--) {
            if (maletas.get(i) == null) {
                hueco = i;
            }
        }
        return hueco;

    }



    //Funcion para juntar y mandar al servdidor
    public String juntar() {

        String maletas;
        String maletaEnAvion;

        if (this.maletas.equals(null)) {
            maletas = "Vacio";
        } else {
            maletas = this.maletas.toString();
        }

        if (this.avion.getContenidoPlanta().getLista().equals(null)) {
            maletaEnAvion = "Vacio";
        } else {
            maletaEnAvion = this.avion.getContenidoPlanta().getLista().toString();
        }


        String datosCliente = maletas + ";" + maletaEnAvion;


        return datosCliente;

    }

    public int getMaletasAtendidas() {
        return maletasAtendidas;
    }

    public void setMaletasAtendidas(int maletasAtendidas) {
        this.maletasAtendidas = maletasAtendidas;
    }
}
