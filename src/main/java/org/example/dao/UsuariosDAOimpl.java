package org.example.dao;

import org.example.Model.UsuarioConNota;
import org.example.Model.Usuarios;
import java.sql.Statement;
import java.sql.*;
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

        String sql = """
            INSERT INTO Usuarios
            (nombre, apellido, correo)
            VALUES (?, ?, ?)
            """;

        try(
                PreparedStatement statement =
                        connection.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ){

            statement.setString(
                    1,
                    usuarios.getNombre()
            );

            statement.setString(
                    2,
                    usuarios.getApellido()
            );

            statement.setString(
                    3,
                    usuarios.getCorreo()
            );

            int filas =
                    statement.executeUpdate();

            System.out.println(
                    "Filas insertadas: " +
                            filas
            );

            ResultSet keys =
                    statement.getGeneratedKeys();

            if(keys.next()){

                int id =
                        keys.getInt(1);

                System.out.println(
                        "ID generado MYSQL: " +
                                id
                );

                usuarios.setId_usuario(id);

            } else {

                System.out.println(
                        "NO se generaron keys"
                );
            }

        } catch(SQLException e){
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
        String sql = "SELECT *FROM Usuarios";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
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

    @Override
    public List<UsuarioConNota> listarNota() {
        List<UsuarioConNota> usuarioConNotaList = new ArrayList<>();

        String sql = """
        SELECT u.id_usuario, u.nombre, u.apellido, u.correo, i.nota
        FROM usuarios u
        INNER JOIN intentos i ON u.id_usuario = i.usuario_id
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                usuarioConNotaList.add(new UsuarioConNota(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getDouble("nota")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioConNotaList;
    }
}
