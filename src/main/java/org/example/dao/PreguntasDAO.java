package org.example.dao;

import org.example.Model.Preguntas;

import java.util.List;

public interface PreguntasDAO {
    List<Preguntas> listar();
    void actualizar(Preguntas pregunta);
    Preguntas leer(int id_preguntas);
}
