package com.company;

import java.util.Calendar;

public class Tecnico {

    //Atributos
    private int id;
    private String nombre;
    private String usuarioRegistrado;
    private String correo;
    private String passwordRegistrada;
    private String confirmacionPassword;
    private Incidencia incidencia1;
    private Incidencia incidencia2;
    private Incidencia incidenciaResuelta1;
    private Incidencia IncidenciaResuelta2;

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

    public void setUsuarioRegistrado(String usuario) {
        this.usuarioRegistrado = usuario;
    }

    public String getPasswordRegistrada() {
        return passwordRegistrada;
    }

    public void setPasswordRegistrada(String password) {
        this.passwordRegistrada = password;
    }

    public Incidencia getIncidencia1() {
        return incidencia1;
    }

    public void setIncidencia1(Incidencia incidencia1) {
        this.incidencia1 = incidencia1;
    }

    public Incidencia getIncidencia2() {
        return incidencia2;
    }

    public void setIncidencia2(Incidencia incidencia2) {
        this.incidencia2 = incidencia2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getConfirmacionPassword() {
        return confirmacionPassword;
    }

    public void setConfirmacionPassword(String confirmacionPassword) {
        this.confirmacionPassword = confirmacionPassword;
    }

    public Incidencia getIncidenciaResuelta1() {
        return incidenciaResuelta1;
    }

    public void setIncidenciaResuelta1(Incidencia incidenciaResuelta1) {
        this.incidenciaResuelta1 = incidenciaResuelta1;
    }

    public Incidencia getIncidenciaResuelta2() {
        return IncidenciaResuelta2;
    }

    public void setIncidenciaResuelta2(Incidencia incidenciaResuelta2) {
        IncidenciaResuelta2 = incidenciaResuelta2;
    }

    //Constructores
    public Tecnico(String nombre, String usuarioRegistrado, String passwordRegistrada, String confirmacionPassword,String correo) {
        Calendar calendar = Calendar.getInstance();
        int idTecnico = ((int) (Math.random() * 3000000) + 2000000);
        this.id = idTecnico;
        this.nombre = nombre;
        this.usuarioRegistrado = usuarioRegistrado;
        this.passwordRegistrada = passwordRegistrada;
        this.confirmacionPassword = confirmacionPassword;
        this.correo = correo;
    }

    public Tecnico(){
        Calendar calendar = Calendar.getInstance();
        this.id = (int) calendar.getTimeInMillis();
    }

    @Override
    public String toString() {
        return "◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•" + "\n" +
                "\s\s" + "ID: " + id + "\n" +
                "\s\s" + "Nombre: " + nombre + "\n" +
                "\s\s" + "Nombre de usuario: " + usuarioRegistrado + "\n" +
                "\s\s" + "Correo electrónico: " + correo + "\n" +
                "◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•◦•" + "\n";
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



    public boolean confirmaPassword (String passwordTecnico, String confirmacionPassword){
        if (passwordTecnico.equalsIgnoreCase(confirmacionPassword)){
            return true;
        }
        else {
            return false;
        }
    }


}
