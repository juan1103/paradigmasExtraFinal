package aero.clases;

public class Peticion {
    private int dirPlanta;
    private int oriPlanta;
    private Ascensor ascensorEnCurso;
    private boolean peticionFinal;
    private boolean realizada;

    public Peticion(int dirPlanta, int oriPlanta, Ascensor ascensorEnCurso, boolean peticionFinal) {
        this.dirPlanta = dirPlanta;
        this.oriPlanta = oriPlanta;
        this.ascensorEnCurso = ascensorEnCurso;
        this.peticionFinal = peticionFinal;
    }

    public int getDirPlanta() {
        return dirPlanta;
    }

    public void setDirPlanta(int dirPlanta) {
        this.dirPlanta = dirPlanta;
    }

    public Ascensor getAscensorEnCurso() {
        return ascensorEnCurso;
    }

    public void setAscensorEnCurso(Ascensor ascensorEnCurso) {
        this.ascensorEnCurso = ascensorEnCurso;
    }

    public int getOriPlanta() {
        return oriPlanta;
    }

    public void setOriPlanta(int oriPlanta) {
        this.oriPlanta = oriPlanta;
    }

    public boolean isPeticionFinal() {
        return peticionFinal;
    }

    public void setPeticionFinal(boolean peticionFinal) {
        this.peticionFinal = peticionFinal;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }
}
