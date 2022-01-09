package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Incidencia {

    //Atributos
    private int id;
    private Usuario usuario;
    private Tecnico tecnico;
    private String comentario;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public boolean isAsignada() {
        return asignada;
    }

    public void setAsignada(boolean asignado) {
        this.asignada = asignado;
    }

    //Constructores
    public Incidencia(Usuario usuario, Tecnico tecnico,String comentario, String prioridad, boolean asignado,boolean resuelto) {
        Calendar calendar = Calendar.getInstance();
        this.id = (int) calendar.getTimeInMillis();
        this.fechaRegistro = Calendar.getInstance();
        this.fechaImprimir = new SimpleDateFormat("dd-MM-yyyy").format(this.fechaRegistro.getTime());
        this.fechaActual = Calendar.getInstance();
        this.fechaActual.set(2022, Calendar.JANUARY, 20);
        this.usuario = usuario;
        this.tecnico = tecnico;
        this.comentario = comentario;
        this.prioridad = prioridad;
        this.resuelto = resuelto;
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

    @Override
    public String toString() {
        return "Incidencia{" +
                "id=" + id +
                ", usuario=" + usuario.getUsuarioRegistrado() +
                ", tecnico=" + tecnico.getUsuarioRegistrado() +
                ", comentario='" + comentario + '\'' +
                ", prioridad='" + prioridad + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaActual=" + fechaActual +
                ", fechaImprimir='" + fechaImprimir + '\'' +
                '}';
    }

    //Métodos
    public int betweenDays() {
        return (int)( (fechaActual.getTime().getTime() - fechaRegistro.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    public String muestraIncidencia (){
        if (resuelto == true){
            estado = "RESUELTA";
        } else {
            estado = "SIN RESOLVER";
        }
        return "～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～" + "\n" +
                "\s Incidencia con ID: " + id + "\n" +
                "\s Abierta por: " + usuario.getUsuarioRegistrado() + "\n" +
                "\s Han pasado " + betweenDays() + " días desde que se abrió" + "\n" +
                "\s Comentarios: " + comentario + "\n" +
                "\s Prioridad: " + prioridad + "\n" +
                "\s Fecha de creación: " + fechaImprimir + "\n" +
                "\s Estado: " + estado + "\n" +
                "～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～●～";
    }


}
