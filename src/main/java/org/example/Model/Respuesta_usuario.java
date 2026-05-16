package org.example.Model;

public class Respuesta_usuario {
    private int id_respuesta;
    private int intento_id;
    private int preguntas_id;
    private String respuesta_dada;
    private boolean es_correcta;

    public Respuesta_usuario() {
    }

    public Respuesta_usuario( int intento_id, int preguntas_id, String respuesta_dada, boolean es_correcta) {
        this.intento_id = intento_id;
        this.preguntas_id = preguntas_id;
        this.respuesta_dada = respuesta_dada;
        this.es_correcta = es_correcta;
    }

    public int getId_respuesta() {
        return id_respuesta;
    }


    public void setId_respuesta(int id_respuesta) {
        this.id_respuesta = id_respuesta;
    }

    public int getIntento_id() {
        return intento_id;
    }

    public void setIntento_id(int intento_id) {
        this.intento_id = intento_id;
    }

    public int getPreguntas_id() {
        return preguntas_id;
    }

    public void setPreguntas_id(int preguntas_id) {
        this.preguntas_id = preguntas_id;
    }

    public String getRespuesta_dada() {
        return respuesta_dada;
    }

    public void setRespuesta_dada(String respuesta_dada) {
        this.respuesta_dada = respuesta_dada;
    }

    public boolean isEs_correcta() {
        return es_correcta;
    }

    public void setEs_correcta(boolean es_correcta) {
        this.es_correcta = es_correcta;
    }
}
