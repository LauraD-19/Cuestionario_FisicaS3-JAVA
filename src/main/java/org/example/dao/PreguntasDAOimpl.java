package org.example.dao;

import org.example.Model.Preguntas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreguntasDAOimpl implements PreguntasDAO {

    private Connection connection;

    public PreguntasDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Preguntas> listar() {
        List<Preguntas> lista = new ArrayList<>();

        String sql = "SELECT *FROM Preguntas";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Preguntas p = new Preguntas();

                p.setId_preguntas(rs.getInt("id_preguntas"));
                p.setEnunciado(rs.getString("enunciado"));
                p.setRespuesta_correcta(rs.getString("respuesta_correcta"));
                p.setOpcion_a(rs.getString("opcion_a"));
                p.setOpcion_b(rs.getString("opcion_b"));
                p.setOpcion_c(rs.getString("opcion_c"));
                p.setOpcion_d(rs.getString("opcion_d"));
                p.setExplicacion(rs.getString("explicacion"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void actualizar(Preguntas pregunta) {
        String sql = """
        UPDATE Preguntas
        SET enunciado = ?,
            respuesta_correcta = ?,
            opcion_a = ?,
            opcion_b = ?,
            opcion_c = ?,
            opcion_d = ?,
            explicacion = ?
        WHERE id_preguntas = ?
        """;

        try(PreparedStatement statement =
                    connection.prepareStatement(sql)) {

            statement.setString(1,
                    pregunta.getEnunciado());

            statement.setString(2,
                    pregunta.getRespuesta_correcta());

            statement.setString(3,
                    pregunta.getOpcion_a());

            statement.setString(4,
                    pregunta.getOpcion_b());

            statement.setString(5,
                    pregunta.getOpcion_c());

            statement.setString(6,
                    pregunta.getOpcion_d());

            statement.setString(7,
                    pregunta.getExplicacion());

            statement.setInt(8,
                    pregunta.getId_preguntas());

            statement.executeUpdate();

            System.out.println("Pregunta actualizada");

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Preguntas leer(int id_preguntas) {
        String sql =
                "SELECT *FROM Preguntas WHERE id_preguntas = ?";

        try(PreparedStatement statement =
                    connection.prepareStatement(sql)) {

            statement.setInt(1, id_preguntas);

            ResultSet resultSet =
                    statement.executeQuery();

            if(resultSet.next()) {

                return new Preguntas(

                        resultSet.getInt("id_preguntas"),
                        resultSet.getString("enunciado"),
                        resultSet.getString("respuesta_correcta"),
                        resultSet.getString("opcion_a"),
                        resultSet.getString("opcion_b"),
                        resultSet.getString("opcion_c"),
                        resultSet.getString("opcion_d"),
                        resultSet.getString("explicacion")
                );
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}