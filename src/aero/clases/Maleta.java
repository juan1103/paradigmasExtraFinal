package aero.clases;


public class Maleta {

    private Integer numPasajero;
    private String idMaleta;


    public Maleta(String idMaleta, Integer numPasajero) {

        this.idMaleta = idMaleta;
        this.numPasajero = numPasajero;
    }

    public String getIdMaleta() {
        return idMaleta;
    }

    public void setIdMaleta(String idMaleta) {
        this.idMaleta = idMaleta;
    }


    public Integer getNumPasajero() {
        return numPasajero;
    }

    public void setNumPasajero(Integer numPasajero) {
        this.numPasajero = numPasajero;
    }

    @Override
    public String toString() {
        return
                idMaleta + " ";
    }
}

