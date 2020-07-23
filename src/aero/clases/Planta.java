package aero.clases;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Planta {

    private ContenidoPlanta contenidoPlanta;
    private Lock c = new ReentrantLock();
    private Integer empleadosEnElAvion;
    private Log log;
    private Paso paso;
    private boolean peticionDeLlamada;


    public Planta(Log log, Paso paso, JTextField tf) {
        this.contenidoPlanta = new ContenidoPlanta(tf);
        this.empleadosEnElAvion = 0;
        this.log = log;
        this.paso = paso;
        this.peticionDeLlamada = false;
    }


    public void meterPasajero(Maleta maleta, Integer numEmpleado, JTextField tf) throws IOException {
        c.lock();

        paso.mirar(numEmpleado);
        paso.mirarTodos();
        System.out.println("Empleado numero: " + numEmpleado + " entrando al avión");
        log.agregarOperacion("Empleado numero: " + numEmpleado + " entrando al avión");
        tf.setText("Empleado numero: " + numEmpleado + " entrando al avión");


        System.out.println("Empleado numero: " + numEmpleado + " entrando al avión con maleta: " + maleta.toString());
        log.agregarOperacion("Empleado numero: " + numEmpleado + " entrando al avión con maleta: " + maleta.toString());
        tf.setText("Empleado numero: " + numEmpleado + " entrando al avión con maleta: " + maleta.toString());

        this.aumentarNumAscensores();
        this.contenidoPlanta.meter(maleta);
        System.out.println("Maleta " + maleta.getIdMaleta() + " introducida por empleado número: " + numEmpleado);
        log.agregarOperacion("Maleta " + maleta.getIdMaleta() + " introducida por empleado número: " + numEmpleado);
        tf.setText("Maleta " + maleta.getIdMaleta() + " introducida por empleado número: " + numEmpleado);
        System.out.println("Numero de empleados en el avión: " + empleadosEnElAvion);

        this.disminuirNumAscensores();

        long tAleatorio2 = 400 + (int) ((300) * Math.random());
        try {
            Thread.sleep(tAleatorio2);
            ; //Simulamos el tiempo que tarda en volver del avion
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Empleado numero: " + numEmpleado + " saliendo del avión");
        log.agregarOperacion("Empleado numero: " + numEmpleado + " saliendo del avión");
        tf.setText("Empleado numero: " + numEmpleado + " saliendo del avión");


        c.unlock();

    }

    public void aumentarNumAscensores() {
        this.empleadosEnElAvion++;
    }

    public void disminuirNumAscensores() {
        this.empleadosEnElAvion--;
    }

    public ContenidoPlanta getContenidoPlanta() {
        return contenidoPlanta;
    }

    public void setContenidoPlanta(ContenidoPlanta contenidoPlanta) {
        this.contenidoPlanta = contenidoPlanta;
    }
}

