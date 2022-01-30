package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Incidencia {

    //Atributos
    private int id;
    private Usuario usuario;
    private Tecnico tecnico;
    private String comentarioUsuario;
    private String comentarioTecnico;
    private String prioridad;
    private Calendar fechaRegistro;
    private Calendar fechaActual;
    private String fechaImprimir;
    private String estado;
    private boolean asignada;
    private boolean resuelto;

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public String getComentarioUsuario() {
        return comentarioUsuario;
    }

    public void setComentarioUsuario(String comentarioUsuario) {
        this.comentarioUsuario = comentarioUsuario;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public Calendar getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Calendar fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getFechaImprimir() {
        return fechaImprimir;
    }

    public void setFechaImprimir(String fechaImprimir) {
        this.fechaImprimir = fechaImprimir;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentarioTecnico() {
        return comentarioTecnico;
    }

    public void setComentarioTecnico(String comentarioTecnico) {
        this.comentarioTecnico = comentarioTecnico;
    }

    public boolean isAsignada() {
        return asignada;
    }

    public void setAsignada(boolean asignado) {
        this.asignada = asignado;
    }

    //Constructores //TODO ARREGLAR FECHA
    public Incidencia(Usuario usuario, Tecnico tecnico, String comentarioUsuario, String prioridad, boolean asignado, boolean resuelto, String comentarioTecnico) {
        Calendar calendar = Calendar.getInstance();
        this.fechaRegistro = Calendar.getInstance();
        this.fechaImprimir = new SimpleDateFormat("dd-MM-yyyy").format(this.fechaRegistro.getTime());
        this.fechaActual = Calendar.getInstance();
        this.fechaActual.set(2022, Calendar.JANUARY, 20);
        this.usuario = usuario;
        this.tecnico = tecnico;
        this.comentarioUsuario = comentarioUsuario;
        this.prioridad = prioridad;
        this.resuelto = resuelto;
        this.comentarioTecnico = comentarioTecnico;
    }

    public Incidencia () {
        Calendar calendar = Calendar.getInstance();
        this.id = (int) calendar.getTimeInMillis();
        this.fechaRegistro = calendar;
        this.fechaImprimir = new SimpleDateFormat("dd-MM-yyyy").format(this.fechaRegistro.getTime());
        this.fechaActual = calendar;
        this.fechaActual.set(Calendar.YEAR, Calendar.JANUARY, 20);
        this.usuario = usuario;
    }

    //Métodos
    public int betweenDays() {
        return (int)( (fechaActual.getTime().getTime() - fechaRegistro.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }



    @Override
    public String toString() {
        return "～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～" + "\n" +
                "\s Incidencia con ID: " + id + "\n" +
                "\s Abierta por: " + usuario.getUsuarioRegistrado() + "\n" +
                "\s Han pasado " + betweenDays() + " días desde que se abrió" + "\n" +
                "\s Comentarios: " + comentarioUsuario + "\n" +
                "\s Prioridad: " + prioridad + "\n" +
                "\s Fecha de creación: " + fechaImprimir + "\n" +
                "\s Estado: " + (resuelto ? "RESUELTO" : "SIN RESOLVER") + "\n" +
                "～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～";
    }

    public String muestraIncidencia (){
        if (resuelto == true){
            estado = "RESUELTA";
        } else {
            estado = "SIN RESOLVER";
        }

        return "～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～" + "\n" +
                "\s Incidencia con ID: " + id + "\n" +
                "\s Abierta por: " + usuario.getUsuarioRegistrado() + "\n" +
                "\s Han pasado " + betweenDays() + " días desde que se abrió" + "\n" +
                "\s Comentarios: " + comentarioUsuario + "\n" +
                "\s Prioridad: " + prioridad + "\n" +
                "\s Fecha de creación: " + fechaImprimir + "\n" +
                "\s Estado: " + estado + "\n" +
                "\s Comentario del técnico: " + comentarioTecnico + "\n" +
                "～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～";
    }


}
