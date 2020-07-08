package aero.clases;

import java.util.concurrent.CyclicBarrier;

public class Pasajero extends Thread {

    private int num;
    private Edificio a;
    private CyclicBarrier barreraFin;
    private Paso p;
    private int contadorPasoMaletas;

    public Pasajero(int num, Edificio a, Paso paso, CyclicBarrier barreraFin) {
        this.num = num;
        this.a = a;
        this.p = paso;
        this.barreraFin = barreraFin;
        this.contadorPasoMaletas = 1;
    }

    public void run() {

        while (contadorPasoMaletas < 3) {
            p.mirarTodos();
            a.llegarCinta(num);
            p.mirarTodos();
            Maleta maleta = new Maleta("Pasajero" + this.num + "M" + this.contadorPasoMaletas, this.num);
            a.pasarCinta(maleta);
            //System.out.println("Pasajero: " + num + " deja Maleta: " + contadorPasoMaletas);
            contadorPasoMaletas++;
            long tAleatorio1 = 500 + (int) ((500) * Math.random());
            try {
                sleep(tAleatorio1); //Simulamos el tiempo que tarda en dejar la segunda maleta
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        try {
            barreraFin.await();  // Se queda bloqueado hasta que 41 hilos hagan esta llamada.
        } catch (Exception e) {
        }


    }
}
