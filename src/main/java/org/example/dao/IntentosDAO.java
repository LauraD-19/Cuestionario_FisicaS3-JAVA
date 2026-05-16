package org.example.dao;

import org.example.Model.Intentos;

import java.util.List;

public interface IntentosDAO {
    void crear (Intentos intentos);
    void actualizarNota(int id_intento, double nota);
    Intentos leer(int usuario_id);
    List<Intentos> intentosList();
}
