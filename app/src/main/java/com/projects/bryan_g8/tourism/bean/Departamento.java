package com.projects.bryan_g8.tourism.bean;

/**
 * Created by bryan_g8 on 29/05/16.
 */
public class Departamento {
    private Integer idDepartamento;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Departamento() {
    }

    public Departamento(Integer idDepartamento, String nombre) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
    }
}
