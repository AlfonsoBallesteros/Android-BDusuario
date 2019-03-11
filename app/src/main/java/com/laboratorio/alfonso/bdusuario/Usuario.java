package com.laboratorio.alfonso.bdusuario;

public class Usuario {

    private int Id;
    private String Nombre;
    private String Apellido;
    private String Cedula;
    private String Edad;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String cedula, String edad) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
        Cedula = cedula;
        Edad = edad;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }
}
