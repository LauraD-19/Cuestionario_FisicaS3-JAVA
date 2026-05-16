package org.example.dao;

import org.example.Model.Respuesta_usuario;

import java.util.List;

public interface Respuesta_usuarioDAO {
    void insertar(Respuesta_usuario respuesta_usuario);
    List<Respuesta_usuario> listarporIntento(int intento_id);

}