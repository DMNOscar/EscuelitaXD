package com.example.oscar.escuelaapp.Entidades;

/**
 * Created by Oscar on 15/06/2018.
 */

public class Evaluacion {

    private int id;
    private double calificacion;
    private Usuario usuario;
    private Materia materia;


    public Evaluacion() {
    }

    public Evaluacion(int id, double calificacion, Usuario usuario, Materia materia) {
        this.id = id;
        this.calificacion = calificacion;
        this.usuario = usuario;
        this.materia = materia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
