package org.gimnasio.model;

import java.sql.Date;

public class Membresia {
    private int id;
    private Socio socio;
    private TipoMembresia tipoMembresia;
    private int idGimnasio;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;

    public Membresia (Socio socio, TipoMembresia tipoMembresia, int idGimnasio, Date fechaInicio, Date fechaFin) {
        this.socio = socio;
        this.tipoMembresia = tipoMembresia;
        this.idGimnasio = idGimnasio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGimnasio() {
        return idGimnasio;
    }

    public void setIdGimnasio(int idGimnasio) {
        this.idGimnasio = idGimnasio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public TipoMembresia getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(TipoMembresia tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }
}
