package org.example.Model;

public class UsuarioConNota {

    public int id;
    public String nombre;
    public String apellido;
    public String correo;
    public double nota;

    public UsuarioConNota(int id, String nombre, String apellido, String correo, double nota) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.nota = nota;
    }
}
