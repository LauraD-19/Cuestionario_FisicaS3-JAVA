package org.example.dao;

import java.sql.Connection;
import org.example.Model.Respuesta_usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Respuesta_usuarioDAOimpl implements Respuesta_usuarioDAO{

    private Connection connection;
    public Respuesta_usuarioDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertar(Respuesta_usuario respuestaUsuario) {
        String sql="INSERT INTO Respuesta_usuario (intento_id, preguntas_id, respuesta_dada, es_correcta) VALUES (?,?,?,?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, respuestaUsuario.getIntento_id());
            statement.setInt(2, respuestaUsuario.getPreguntas_id());
            statement.setString(3, respuestaUsuario.getRespuesta_dada());
            statement.setBoolean(4, respuestaUsuario.isEs_correcta());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();

        }
    }

    @Override
    public List<Respuesta_usuario> listarporIntento(int intento_id) {
        List<Respuesta_usuario> respuesta_usuarioList = new ArrayList<>();

        String sql = "SELECT * FROM respuesta_usuario WHERE intento_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, intento_id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Respuesta_usuario respuesta = new Respuesta_usuario(
                        resultSet.getInt("intento_id"),
                        resultSet.getInt("preguntas_id"),
                        resultSet.getString("respuesta_dada"),
                        resultSet.getBoolean("es_correcta")
                );

                respuesta.setId_respuesta(resultSet.getInt("id_respuesta"));
                respuesta_usuarioList.add(respuesta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return respuesta_usuarioList;
    }
}
