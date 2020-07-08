package aero.clases;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

/* La clase ColaPasajeros permite gestionar las listas de threads en los monitores,
con métodos para meter y sacar threads en ella. Cada vez que una lista se modifica,
se imprime su nuevo contenido en el JTextField que toma como parámetro el constructor. */
public class ContenidoPlanta {
    private LinkedList<Pasajero> lista;
    private JTextField tf;

    public ContenidoPlanta(JTextField tf) {
        lista = new LinkedList<Pasajero>();
        this.tf = tf;
    }

    public synchronized void meter(Pasajero pasajero) {
        lista.add(pasajero);
        tf.setText(lista.toString());

    }


    public LinkedList<Pasajero> getLista() {
        return lista;
    }

}
