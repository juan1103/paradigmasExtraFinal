package aero.clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author mok4_
 */
public class Log {
    private FileWriter archivo; //nuestro archivo log

    public void agregarOperacion(String op) throws IOException {
        this.crearLog(op);
    }

    public void crearLog(String operacion) throws IOException {

        //Pregunta el archivo existe, caso contrario crea uno con el nombre log.txt
        if (new File("log.txt").exists() == false) {
            archivo = new FileWriter(new File("log.txt"), true);
        }
        archivo = new FileWriter(new File("log.txt"), true);
        Calendar fechaActual = Calendar.getInstance(); //Para poder utilizar el paquete calendar
        //Empieza a escribir en el archivo
        archivo.write((String.valueOf(fechaActual.get(Calendar.HOUR_OF_DAY))
                + ":" + String.valueOf(fechaActual.get(Calendar.MINUTE))
                + ":" + String.valueOf(fechaActual.get(Calendar.SECOND))) + ";  " + operacion + "\r\n");

    }//Fin del metodo crearLog

    public void closeStream() throws IOException {
        archivo.close();
    }
}
