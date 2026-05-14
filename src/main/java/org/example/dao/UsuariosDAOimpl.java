package org.example.dao;

import org.example.Model.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAOimpl implements UsuariosDAO{
    //conecction
    private final Connection connection;
    public UsuariosDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Usuarios usuarios) {
        String sql = "INSERT INTO Usuarios "+"(nombre, apellido, correo)"+"VALUES (?,?,?)";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setString(1, usuarios.getNombre());
            statement.setString(2, usuarios.getApellido());
            statement.setString(3, usuarios.getCorreo());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Usuarios leer(int id_usuario) {
        String sql = "SELECT *FROM Usuarios WHERE id_usuario =?";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1, id_usuario);

            ResultSet resultSet =statement.executeQuery();

            if(resultSet.next()){
                Usuarios usuarios = new Usuarios(
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("correo")

                );
                usuarios.setId_usuario(resultSet.getInt("id_usuario"));
                return usuarios;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Usuarios> listar() {
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT *FROM Usuaraios";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Usuarios usuarios1 = new Usuarios(//para que se pueda mostrar el id
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("correo")
                );

                usuarios1.setId_usuario(resultSet.getInt("id_usuario"));
                usuarios.add(usuarios1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuarios;
    }
}
