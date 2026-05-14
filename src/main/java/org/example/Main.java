package org.example;

import org.example.Model.Usuarios;
import org.example.Util.ConexionBD;
import org.example.dao.*;
import org.example.Model.Preguntas;
import org.example.dao.PreguntasDAOimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection connection=ConexionBD.obtenerConexion()){
            PreguntasDAO dao = new PreguntasDAOimpl(connection);
            List<Preguntas> preguntas = dao.listar();

            Scanner scanner=new Scanner(System.in);
            int op;

            System.out.println("Bienvenidos al cuestionario de fisica...ʕ•́ᴥ•̀ʔっ...");
            do {
                UsuariosDAO usuariosDAO = new UsuariosDAOimpl(connection);
                System.out.println("seleccione una de las siguientes opciones:");
                System.out.println("1.crear");
                System.out.println("2.listar usuarios");
                System.out.println("3.buscar usuarios");
                System.out.println("4.salir");
                op= scanner.nextInt();


                switch (op){
                    case 1://crear


                        scanner.nextLine();
                        System.out.println("Nombre/s:");
                        String nombre= scanner.nextLine();
                        System.out.println("Apellido/s");
                        String apellido=scanner.nextLine();
                        System.out.println("Email:");
                        String email=scanner.nextLine();

                        Usuarios usuarios=new Usuarios(nombre, apellido, email);
                        usuariosDAO.crear(usuarios);
                        System.out.println("Usuario creado...");


                        System.out.println("\n");
                        System.out.println("A continuación las preguntas del quiz");
                        System.out.println("Te deseamos exitos :)");

                        for (Preguntas p : preguntas) {

                            System.out.println("\n" + p.getEnunciado());
                            System.out.println("A) " + p.getOpcionA());
                            System.out.println("B) " + p.getOpcionB());
                            System.out.println("C) " + p.getOpcionC());
                            System.out.println("D) " + p.getOpcionD());

                            System.out.print("Tu respuesta (A/B/C/D): ");
                            String resp = scanner.nextLine().trim();
                            if (resp.equalsIgnoreCase(p.getRespuestaCorrecta())) {
                                System.out.println(" Correcto");
                            } else {
                                System.out.println(" Incorrecto");
                                System.out.println(p.getExplicacion());
                            }
                        }
                        break;

                    case 2://lista

                        List<Usuarios> usuariosList = usuariosDAO.listar();
                        for (Usuarios c : usuariosList){
                            System.out.println(c);
                        }
                        break;

                    case 3://buscar
                        System.out.println("Ingrese el ID del usuario:");
                        int idleer=scanner.nextInt();
                        Usuarios usuarios1= usuariosDAO.leer(idleer);
                        if(usuarios1!=null){
                            System.out.println(usuarios1);
                        }else{
                            System.out.println("usuario no encontrado");
                        }
                        break;

                    case 4://salida
                        System.out.println("Saliendo...");
                        System.out.println("Que tenga buen día ʕ•́ᴥ•̀ʔっ ...");
                        break;

                    default:
                        System.out.println("---> Ingrese una opcion correcta >:");

                }


            }while (op!=4);


        }catch (SQLException e){
            e.printStackTrace();
        }


    }

}