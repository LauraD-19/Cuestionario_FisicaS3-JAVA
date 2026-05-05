package org.example;

import org.example.Util.ConexionBD;
import org.example.dao.PreguntasDAO;
import org.example.dao.PreguntasDAOimpl;
import org.example.Model.Preguntas;
import org.example.dao.PreguntasDAOimpl;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            Connection conn = ConexionBD.obtenerConexion();

            PreguntasDAO dao = new PreguntasDAOimpl(conn);
            List<Preguntas> preguntas = dao.listar();

            Scanner sc = new Scanner(System.in);

            for (Preguntas p : preguntas) {

                System.out.println("\n" + p.getEnunciado());
                System.out.println("A) " + p.getOpcionA());
                System.out.println("B) " + p.getOpcionB());
                System.out.println("C) " + p.getOpcionC());
                System.out.println("D) " + p.getOpcionD());

                System.out.print("Tu respuesta (A/B/C/D): ");
                String resp = sc.nextLine().trim();

                if (resp.equalsIgnoreCase(p.getRespuestaCorrecta())) {
                    System.out.println(" Correcto");
                } else {
                    System.out.println(" Incorrecto");
                    System.out.println( p.getExplicacion());
                }
            }

            System.out.println("\n🎉 Terminaste el cuestionario");

        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }