package model;

import java.sql.Date;

public class Membresia {
    private int id;
    private int idSocio;
    private int idTipoMembresia;
    private int idGimnasio;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public int getIdTipoMembresia() {
        return idTipoMembresia;
    }

    public void setIdTipoMembresia(int idTipoMembresia) {
        this.idTipoMembresia = idTipoMembresia;
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
}
