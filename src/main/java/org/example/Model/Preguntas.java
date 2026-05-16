package org.example.Model;

public class Preguntas {
    private int id_preguntas;
    private String enunciado;
    private String opcion_a;
    private String opcion_b;
    private String opcion_c;
    private String opcion_d;
    private String respuesta_correcta;
    private String explicacion;

    public Preguntas() {
    }

    public Preguntas(int id_preguntas, String enunciado, String opcion_a, String opcion_b, String opcion_c, String opcion_d, String respuesta_correcta, String explicacion) {
        this.id_preguntas=id_preguntas;
        this.enunciado = enunciado;
        this.opcion_a = opcion_a;
        this.opcion_b = opcion_b;
        this.opcion_c = opcion_c;
        this.opcion_d = opcion_d;
        this.respuesta_correcta = respuesta_correcta;
        this.explicacion = explicacion;
    }

    public int getId_preguntas() {
        return id_preguntas;
    }

    public void setId_preguntas(int id_preguntas) {
        this.id_preguntas = id_preguntas;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getOpcion_a() {
        return opcion_a;
    }

    public void setOpcion_a(String opcion_a) {
        this.opcion_a = opcion_a;
    }

    public String getOpcion_b() {
        return opcion_b;
    }

    public void setOpcion_b(String opcion_b) {
        this.opcion_b = opcion_b;
    }

    public String getOpcion_c() {
        return opcion_c;
    }

    public void setOpcion_c(String opcion_c) {
        this.opcion_c = opcion_c;
    }

    public String getOpcion_d() {
        return opcion_d;
    }

    public void setOpcion_d(String opcion_d) {
        this.opcion_d = opcion_d;
    }

    public String getRespuesta_correcta() {
        return respuesta_correcta;
    }

    public void setRespuesta_correcta(String respuesta_correcta) {
        this.respuesta_correcta = respuesta_correcta;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    @Override
    public String toString() {
        return "Preguntas{" +
                "id_preguntas=" + id_preguntas +
                ", enunciado='" + enunciado + '\'' +
                ", opcion_a='" + opcion_a + '\'' +
                ", opcion_b='" + opcion_b + '\'' +
                ", opcion_c='" + opcion_c + '\'' +
                ", opcion_d='" + opcion_d + '\'' +
                ", respuesta_correcta='" + respuesta_correcta + '\'' +
                ", explicacion='" + explicacion + '\'' +
                '}';
    }
}