package org.example;

import org.example.Model.*;
import org.example.Util.ConexionBD;
import org.example.dao.*;
import org.example.dao.PreguntasDAOimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //menu
        try (Connection connection=ConexionBD.obtenerConexion()){
            UsuariosDAO usuariosDAO= new UsuariosDAOimpl(connection);
            PreguntasDAO preguntasDAO = new PreguntasDAOimpl(connection);
            IntentosDAO intentosDAO= new IntentosDAOimpl(connection);
            Respuesta_usuarioDAO respuestaUsuarioDAO= new Respuesta_usuarioDAOimpl(connection);

            List<Preguntas> preguntas = preguntasDAO.listar();
            List<UsuarioConNota>usuarioConNotaList=usuariosDAO.listarNota();

            Scanner scanner=new Scanner(System.in);
            int op;

            System.out.println("Bienvenidos al cuestionario de fisica...ʕ•́ᴥ•̀ʔっ...");
            do {
                System.out.println("seleccione una de las siguientes opciones:");
                System.out.println("1.Crear usuario y responder cuestionario");
                System.out.println("2.Listar usuarios");
                System.out.println("3.Buscar usuarios");
                System.out.println("4.Salir");
                op= scanner.nextInt();
                scanner.nextLine();


                switch (op){
                    case 1://crear

                        System.out.println("Nombre/s:");
                        String nombre= scanner.nextLine();
                        System.out.println("Apellido/s");
                        String apellido=scanner.nextLine();
                        System.out.println("Email:");
                        String email=scanner.nextLine();

                        Usuarios usuarios=new Usuarios(nombre, apellido, email);
                        usuariosDAO.crear(usuarios);
                        System.out.println("Usuario creado...");

                        //intentos
                        Intentos intento = new Intentos(usuarios.getId_usuario(), 0);
                        intentosDAO.crear(intento);

                        //nota
                        int correctas=0;
                        System.out.println("\n");
                        System.out.println("A continuación las preguntas del cuestionario");
                        System.out.println("Recuerda que solo tienes un intento");
                        System.out.println("Te deseamos exitos :)");

                        for (Preguntas p : preguntas) {

                            System.out.println("\n" + p.getEnunciado());
                            System.out.println("A) " + p.getOpcion_a());
                            System.out.println("B) " + p.getOpcion_b());
                            System.out.println("C) " + p.getOpcion_c());
                            System.out.println("D) " + p.getOpcion_d());

                            //preguntas
                            System.out.print("Tu respuesta (A/B/C/D): ");
                            String resp = scanner.nextLine().trim().toUpperCase();
                            //correctas
                            boolean correcta= resp.equalsIgnoreCase(p.getRespuesta_correcta());
                            if (resp.equalsIgnoreCase(p.getRespuesta_correcta())) {
                                correctas ++;
                                System.out.println(" Correcto");
                            } else {
                                System.out.println(" Incorrecto");
                                System.out.println(p.getExplicacion());
                            }

                            Respuesta_usuario respuesta_usuario= new Respuesta_usuario(
                              intento.getId_intento(),
                              p.getId_preguntas(),
                              resp,
                              correcta
                            );
                            respuestaUsuarioDAO.insertar(respuesta_usuario);
                        }
                        //calculo nota
                        double nota= (correctas*5.0)/ preguntas.size();
                        System.out.println("...Cuestionario terminado...");
                        System.out.println("Respuestas correctas: "+correctas);
                        System.out.println("Tu nota final es de: "+nota);
                        intentosDAO.actualizarNota(intento.getId_intento(), nota);

                        break;

                    case 2://lista usuarios

                        if (usuarioConNotaList.isEmpty()) {
                            System.out.println("No hay usuarios registrados con nota.");
                        } else {

                            for (UsuarioConNota u : usuarioConNotaList) {

                                System.out.println("----------------------");
                                System.out.println("ID: " + u.id);
                                System.out.println("Nombre: " + u.nombre);
                                System.out.println("Apellido: " + u.apellido);
                                System.out.println("Correo: " + u.correo);
                                System.out.println("Nota: " + u.nota);
                            }
                            System.out.println("----------------------");
                        }

                        break;
                    case 3://buscar usuarios
                        System.out.println("Ingrese el ID del usuario:");
                        int idleer=scanner.nextInt();
                        Usuarios usuarios1= usuariosDAO.leer(idleer);
                        if(usuarios1!=null){
                            System.out.println(usuarios1);
                        }else{
                            System.out.println("Usuario no encontrado...");
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