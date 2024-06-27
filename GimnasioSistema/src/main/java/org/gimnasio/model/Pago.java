package org.gimnasio.model;

import java.sql.Date;

public class Pago {
    private int id;
    private Socio socio;
    private Membresia membresia;
    private Clase clase;
    private double monto;
    private Date fechaPago;
    private String metodoPago;
    private String tipoPago;

    private Pago(Builder builder) {
        this.id = builder.id;
        this.socio = builder.socio;
        this.membresia = builder.membresia;
        this.clase = builder.clase;
        this.monto = builder.monto;
        this.fechaPago = builder.fechaPago;
        this.metodoPago = builder.metodoPago;
        this.tipoPago = builder.tipoPago;
    }

    public static class Builder {
        private int id;
        private Socio socio;
        private Membresia membresia;
        private Clase clase;
        private double monto;
        private Date fechaPago;
        private String metodoPago;
        private String tipoPago;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder socio(Socio socio) {
            this.socio = socio;
            return this;
        }

        public Builder membresia(Membresia membresia) {
            this.membresia = membresia;
            return this;
        }

        public Builder clase(Clase clase) {
            this.clase = clase;
            return this;
        }

        public Builder monto(double monto) {
            this.monto = monto;
            return this;
        }

        public Builder fechaPago(Date fechaPago) {
            this.fechaPago = fechaPago;
            return this;
        }

        public Builder metodoPago(String metodoPago) {
            this.metodoPago = metodoPago;
            return this;
        }

        public Builder tipoPago(String tipoPago) {
            this.tipoPago = tipoPago;
            return this;
        }

        public Pago build() {
            return new Pago(this);
        }
    }

    public int getId() {
        return id;
    }

    public Socio getSocio() {
        return socio;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public Clase getClase() {
        return clase;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }
}
