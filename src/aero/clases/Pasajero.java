package aero.clases;

import java.util.concurrent.CyclicBarrier;

public class Pasajero extends Thread {

    private int num;
    private Edificio a;
    private CyclicBarrier barreraFin;
    private Paso p;
    private int contadorPasoMaletas;
    private int dirPlanta;

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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Edificio getA() {
        return a;
    }

    public void setA(Edificio a) {
        this.a = a;
    }

    public CyclicBarrier getBarreraFin() {
        return barreraFin;
    }

    public void setBarreraFin(CyclicBarrier barreraFin) {
        this.barreraFin = barreraFin;
    }

    public Paso getP() {
        return p;
    }

    public void setP(Paso p) {
        this.p = p;
    }

    public int getContadorPasoMaletas() {
        return contadorPasoMaletas;
    }

    public void setContadorPasoMaletas(int contadorPasoMaletas) {
        this.contadorPasoMaletas = contadorPasoMaletas;
    }

    public int getDirPlanta() {
        return dirPlanta;
    }

    public void setDirPlanta(int dirPlanta) {
        this.dirPlanta = dirPlanta;
    }
}
