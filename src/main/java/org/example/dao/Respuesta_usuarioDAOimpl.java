package org.example.dao;

import org.example.Model.Respuesta_usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Respuesta_usuarioDAOimpl implements Respuesta_usuarioDAO {

    private Connection conn;

    public Respuesta_usuarioDAOimpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Respuesta_usuario> listar() {

        List<Respuesta_usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM respuesta_usuario";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Respuesta_usuario respuesta = new Respuesta_usuario();

                respuesta.setId_respuesta(rs.getInt("id_respuesta"));
                respuesta.setIntento_id(rs.getInt("intento_id"));
                respuesta.setPreguntas_id(rs.getInt("preguntas_id"));
                respuesta.setRespuesta_dada(rs.getString("respuesta_dada"));
                respuesta.setEs_correcta(rs.getBoolean("es_correcta"));

                lista.add(respuesta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void insertar(Respuesta_usuario respuesta_usuario) {

        String sql = "INSERT INTO respuesta_usuario " +
                "(intento_id, preguntas_id, respuesta_dada, es_correcta) " +
                "VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, respuesta_usuario.getIntento_id());
            ps.setInt(2, respuesta_usuario.getPreguntas_id());
            ps.setString(3, respuesta_usuario.getRespuesta_dada());
            ps.setBoolean(4, respuesta_usuario.isEs_correcta());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Respuesta_usuario respuesta_usuario) {

        String sql = "UPDATE respuesta_usuario SET " +
                "intento_id=?, preguntas_id=?, respuesta_dada=?, es_correcta=? " +
                "WHERE id_respuesta=?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, respuesta_usuario.getIntento_id());
            ps.setInt(2, respuesta_usuario.getPreguntas_id());
            ps.setString(3, respuesta_usuario.getRespuesta_dada());
            ps.setBoolean(4, respuesta_usuario.isEs_correcta());
            ps.setInt(5, respuesta_usuario.getId_respuesta());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {

        String sql = "DELETE FROM respuesta_usuario WHERE id_respuesta=?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Respuesta_usuario buscarPorId(int id) {

        String sql = "SELECT * FROM respuesta_usuario WHERE id_respuesta=?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Respuesta_usuario respuesta = new Respuesta_usuario();

                respuesta.setId_respuesta(rs.getInt("id_respuesta"));
                respuesta.setIntento_id(rs.getInt("intento_id"));
                respuesta.setPreguntas_id(rs.getInt("preguntas_id"));
                respuesta.setRespuesta_dada(rs.getString("respuesta_dada"));
                respuesta.setEs_correcta(rs.getBoolean("es_correcta"));

                return respuesta;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
