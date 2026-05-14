package org.example.dao;

import org.example.Model.Respuesta_usuario;

import java.util.List;

public interface Respuesta_usuarioDAO {


    List<Respuesta_usuario> listar();

    void insertar(Respuesta_usuario respuesta_usuario);

    void actualizar(Respuesta_usuario respuesta_usuario);

    void eliminar(int id);

    Respuesta_usuario buscarPorId(int id);
}