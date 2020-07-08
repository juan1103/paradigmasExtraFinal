/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aero.clases;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Javier
 */
public class Paso {
    private boolean[] cerrado = new boolean[]{false, false};
    private boolean todosCerrados = false;
    private Lock cerrojo = new ReentrantLock();
    private Condition parar = cerrojo.newCondition();

    public void mirar(int empleado) {
        try {
            cerrojo.lock();
            while (cerrado[empleado - 1]) {
                try {
                    parar.await();
                } catch (InterruptedException ie) {
                }
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public void abrir(int empleado) {
        try {
            cerrojo.lock();
            cerrado[empleado] = false;
            parar.signal();
        } finally {
            cerrojo.unlock();
        }
    }

    public void cerrar(int empleado) {
        try {
            cerrojo.lock();
            cerrado[empleado] = true;
            System.out.println("Empleado " + empleado + 1 + " parado");
        } finally {
            cerrojo.unlock();
        }
    }


    // Pausar/Reanudar todos
    public void mirarTodos() {
        try {
            cerrojo.lock();
            while (todosCerrados) {
                try {
                    parar.await();
                } catch (InterruptedException ie) {
                }
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public void abrirTodos() {
        cerrojo.lock();
        try {

            todosCerrados = false;
            parar.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }

    public void cerrarTodos() {
        cerrojo.lock();
        try {

            todosCerrados = true;
            System.out.println("Todos parados");
        } finally {
            cerrojo.unlock();
        }
    }
}