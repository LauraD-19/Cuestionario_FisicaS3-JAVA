package org.example.dao;

import org.example.Model.UsuarioConNota;
import org.example.Model.Usuarios;

import java.util.List;

public interface UsuariosDAO {
    void crear(Usuarios usuarios);
    Usuarios leer(int id_usuario);
    List<Usuarios> listar();
    List<UsuarioConNota>listarNota();

}
