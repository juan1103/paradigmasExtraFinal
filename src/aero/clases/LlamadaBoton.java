package aero.clases;

import java.sql.Timestamp;

public class LlamadaBoton {

    int planta;
    Pasajero pasajero;
    Timestamp hora;

    public LlamadaBoton(int planta, Pasajero pasajero, Timestamp hora) {
        this.planta = planta;
        this.pasajero = pasajero;
        this.hora = hora;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }
}
