package org.gimnasio.model;

public class Clase {
    private int id;
    private int idTipoClase;
    private String nombre;
    private String descripcion;
    private Double costo;
    private int idInstrutor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipoClase() {
        return idTipoClase;
    }

    public void setIdTipoClase(int idTipoClase) {
        this.idTipoClase = idTipoClase;
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

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public int getIdInstrutor() {
        return idInstrutor;
    }

    public void setIdInstrutor(int idInstrutor) {
        this.idInstrutor = idInstrutor;
    }
}
