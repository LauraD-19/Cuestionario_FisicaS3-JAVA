package org.example.dao;

import org.example.Model.Preguntas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreguntasDAOimpl implements PreguntasDAO {

    private Connection connection;

    public PreguntasDAOimpl(Connection conn) {
        this.connection = connection;
    }

    @Override
    public List<Preguntas> listar() {
        List<Preguntas> lista = new ArrayList<>();

        String sql = "SELECT * FROM Preguntas";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Preguntas p = new Preguntas();

                p.setId_preguntas(rs.getInt("id_preguntas"));
                p.setEnunciado(rs.getString("enunciado"));
                p.setOpcionA(rs.getString("opcion_a"));
                p.setOpcionB(rs.getString("opcion_b"));
                p.setOpcionC(rs.getString("opcion_c"));
                p.setOpcionD(rs.getString("opcion_d"));
                p.setRespuestaCorrecta(rs.getString("respuesta_correcta"));

                p.setExplicacion(rs.getString("explicacion"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}