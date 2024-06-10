package model;

import java.sql.Time;
import java.util.Date;

public class Gimnasio {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private Time horario_apertura;
    private Time horario_cierre;
    private Date fecha_apertura;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Time getHorario_apertura() {
        return horario_apertura;
    }

    public void setHorario_apertura(Time horario_apertura) {
        this.horario_apertura = horario_apertura;
    }

    public Time getHorario_cierre() {
        return horario_cierre;
    }

    public void setHorario_cierre(Time horario_cierre) {
        this.horario_cierre = horario_cierre;
    }

    public Date getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(Date fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
