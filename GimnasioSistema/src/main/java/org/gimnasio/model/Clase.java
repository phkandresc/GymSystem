package org.gimnasio.model;

public class Clase {
    private int id;
    private TipoClase tipoClase;
    private String nombre;
    private String descripcion;
    private Double costo;
    private int cupos;
    private Entrenador instructor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoClase getTipoClase() {
        return tipoClase;
    }

    public void setTipoClase(TipoClase tipoClase) {
        this.tipoClase = tipoClase;
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

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public Entrenador getInstructor() {
        return instructor;
    }

    public void setInstructor(Entrenador instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "id=" + id +
                ", tipoClase=" + tipoClase +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", costo=" + costo +
                ", cupos=" + cupos +
                ", instructor=" + instructor +
                '}';
    }
}
