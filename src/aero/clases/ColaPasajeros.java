package aero.clases;

import java.io.IOException;
import java.util.ArrayList;

/* La clase ColaPasajeros permite gestionar las listas de threads en los monitores,
con métodos para meter y sacar threads en ella. Cada vez que una lista se modifica,
se imprime su nuevo contenido en el JTextField que toma como parámetro el constructor. */
public class ColaPasajeros {
    private ArrayList<Integer> lista;

    public ColaPasajeros() {
        lista = new ArrayList<Integer>();

    }

    public synchronized void meter(int p, Log log) {
        lista.add(p);


        try {
            log.agregarOperacion("Pasajero " + p + " llega a la cola");
            log.agregarOperacion(lista.toString());
        } catch (IOException ex) {
        }

        System.out.println(lista.toString());
    }

    public synchronized void sacar(int p, Log log) {
        lista.remove(new Integer(p));


        try {
            log.agregarOperacion("Pasajero " + p + " sale de la cola");
            log.agregarOperacion(lista.toString());
        } catch (IOException ex) {
        }

        System.out.println(lista.toString());

    }

    public ArrayList getCola() {
        return lista;
    }

}
