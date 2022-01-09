package com.company;

public class Usuario {

    //Atributos
    //Datos del usuario
    private String nombre;
    private String dni;
    private String usuarioRegistrado;
    private String correo;
    private String passwordRegistrada;
    private String confirmacionPassword;
    private String telefono;
    private Incidencia incidencia1;
    private Incidencia incidencia2;
    private Incidencia incidencia3;

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Incidencia getIncidencia3() {
        return incidencia3;
    }

    public void setIncidencia3(Incidencia incidencia3) {
        this.incidencia3 = incidencia3;
    }

    public String getConfirmacionPassword() {
        return confirmacionPassword;
    }

    public void setConfirmacionPassword(String confirmacionPassword) {
        this.confirmacionPassword = confirmacionPassword;
    }

    //Constructores

    public Usuario(String nombre, String dni, String usuarioRegistrado, String correo, String passwordRegistrada, String confirmacionPassword,String telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.usuarioRegistrado = usuarioRegistrado;
        this.correo = correo;
        this.passwordRegistrada = passwordRegistrada;
        this.confirmacionPassword = confirmacionPassword;
        this.telefono = telefono;
    }

    public Usuario (){}

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", usuarioRegistrado='" + usuarioRegistrado + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono=" + telefono +
                '}';
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

    public boolean confirmaPassword (String passwordUsuario, String confirmacionPassword){
        if (passwordUsuario.equalsIgnoreCase(confirmacionPassword)){
            return true;
        }
        else {
            return false;
        }
    }

}
