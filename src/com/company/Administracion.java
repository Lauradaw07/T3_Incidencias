package com.company;

public class Administracion {

    //Atributos
    private String nombre;
    private String usuarioRegistrado;
    private String correo;
    private String passwordRegistrada;

    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuarioRegistrado() {
        return usuarioRegistrado;
    }

    public void setUsuario(String usuario) {
        this.usuarioRegistrado = usuario;
    }

    public String getPasswordRegistrada() {
        return passwordRegistrada;
    }

    public void setPassword(String password) {
        this.passwordRegistrada = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    //Constructor datos del administrador


    public Administracion(String nombre, String usuarioRegistrado, String correo, String passwordRegistrada) {
        this.nombre = nombre;
        this.usuarioRegistrado = usuarioRegistrado;
        this.correo = correo;
        this.passwordRegistrada = passwordRegistrada;
    }


    //Métodos
    public boolean compruebaUsuario (String usuario){
        if (usuario.equalsIgnoreCase(getUsuarioRegistrado())){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean compruebaPassword (String password){
        if (password.equalsIgnoreCase(getPasswordRegistrada())){
            return true;
        }
        else {
            return false;
        }
    }

}
