package com.projects.bryan_g8.tourism.bean;

/**
 * Created by bryan_g8 on 29/05/16.
 */
public class LugarTuristico {
    private Integer idLugarTuristico;
    private String nombre;
    private String descripcion;
    private Integer idDepartamento;

    public LugarTuristico() {
    }

    public LugarTuristico(Integer idLugarTuristico, String nombre, String descripcion, Integer idDepartamento) {
        this.idLugarTuristico = idLugarTuristico;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdLugarTuristico() {
        return idLugarTuristico;
    }

    public void setIdLugarTuristico(Integer idLugarTuristico) {
        this.idLugarTuristico = idLugarTuristico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
}
