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

    public int getId() {
        return id;
    }

    public Socio getSocio() {
        return socio;
    }

    public TipoMembresia getTipoMembresia() {
        return tipoMembresia;
    }

    public int getIdGimnasio() {
        return idGimnasio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public static class Builder {
        private int id;
        private Socio socio;
        private TipoMembresia tipoMembresia;
        private int idGimnasio;
        private Date fechaInicio;
        private Date fechaFin;
        private String estado;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder socio(Socio socio) {
            this.socio = socio;
            return this;
        }

        public Builder tipoMembresia(TipoMembresia tipoMembresia) {
            this.tipoMembresia = tipoMembresia;
            return this;
        }

        public Builder idGimnasio(int idGimnasio) {
            this.idGimnasio = idGimnasio;
            return this;
        }

        public Builder fechaInicio(Date fechaInicio) {
            this.fechaInicio = fechaInicio;
            return this;
        }

        public Builder fechaFin(Date fechaFin) {
            this.fechaFin = fechaFin;
            return this;
        }

        public Builder estado(String estado) {
            this.estado = estado;
            return this;
        }

        public Membresia build() {
            Membresia membresia = new Membresia();
            membresia.id = this.id;
            membresia.socio = this.socio;
            membresia.tipoMembresia = this.tipoMembresia;
            membresia.idGimnasio = this.idGimnasio;
            membresia.fechaInicio = this.fechaInicio;
            membresia.fechaFin = this.fechaFin;
            membresia.estado = this.estado;
            return membresia;
        }
    }
}


