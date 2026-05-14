package org.example.Model;

public class Intentos {
    private int id_intento;
    private int usuario_id;
    private double nota;

    public Intentos() {
    }

    public Intentos(double nota, int usuario_id) {
        this.nota = nota;
        this.usuario_id = usuario_id;
    }

    public Intentos(int id_intento, int usuario_id, double nota) {
        this.id_intento = id_intento;
        this.usuario_id = usuario_id;
        this.nota = nota;
    }

    public int getId_intento() {
        return id_intento;
    }

    public void setId_intento(int id_intento) {
        this.id_intento = id_intento;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Intentos ---> " +
                " id_intento=" + id_intento + '\'' +
                ", usuario_id=" + usuario_id + '\'' +
                ", nota=" + nota + '\'';
    }
}
