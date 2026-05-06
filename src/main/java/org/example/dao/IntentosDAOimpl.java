package org.example.dao;

import org.example.Model.Intentos;
import org.example.Model.Usuarios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IntentosDAOimpl implements IntentosDAO{
    private  final Connection connection;

    public IntentosDAOimpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void crear(Intentos intentos) {
        String sql = "INSERT INTO Intentos" +
                "(usuario_id, nota) " +
                "VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, intentos.getUsuario_id());
            statement.setDouble(2, intentos.getNota());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Intentos leer(int usuario_id) {
        String sql = "SELECT * FROM Intentos WHERE usuario_id= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, usuario_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                resultSet.getInt("id_intento");
                resultSet.getInt("usuario_id");
                resultSet.getDouble("nota");

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Intentos> intentosList() {
        List<Intentos> intentosList = new ArrayList<>();
        String sql = "SELECT * FROM Intentos";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                intentosList.add(new Intentos(
                   resultSet.getInt("id_intento"),
                   resultSet.getInt("usuario_id"),
                   resultSet.getDouble("nota")
                ));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return intentosList;
    }
}
