package org.gimnasio.model;

import java.sql.Date;

public class Reserva {
    private Clase claseProgramada;
    private Socio socio;
    private Date fechaReserva;

    public Clase getClaseProgramada() {
        return claseProgramada;
    }

    public void setClaseProgramada(Clase claseProgramada) {
        this.claseProgramada = claseProgramada;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
