package aero.clases;

import javax.swing.*;
import java.util.ArrayList;

/* La clase ColaPasajeros permite gestionar las listas de threads en los monitores,
con métodos para meter y sacar threads en ella. Cada vez que una lista se modifica,
se imprime su nuevo contenido en el JTextField que toma como parámetro el constructor. */
public class ContenidoPlanta {
    private ArrayList<Maleta> lista;
    private JTextField tf;

    public ContenidoPlanta(JTextField tf) {
        lista = new ArrayList<Maleta>();
        this.tf = tf;
    }

    public synchronized void meter(Maleta maleta) {
        lista.add(maleta);
        tf.setText(lista.toString());

    }


    public ArrayList<Maleta> getLista() {
        return lista;
    }

}
