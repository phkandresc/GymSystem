package org.gimnasio.model;

import java.sql.Date;

public class Factura {
    private int id;
    private Pago pago;
    private String numeroFactura;
    private Date fechaEmision;
    private String detalle;
    private double subtotal;
    private double iva;
    private double total;


    private Factura(Builder builder) {
        this.id = builder.id;
        this.pago = builder.pago;
        this.numeroFactura = builder.numeroFactura;
        this.fechaEmision = builder.fechaEmision;
        this.detalle = builder.detalle;
        this.subtotal = builder.subtotal;
        this.iva = builder.iva;
        this.total = builder.total;
    }

    public static class Builder {
        private int id;
        private Pago pago;
        private String numeroFactura;
        private Date fechaEmision;
        private String detalle;
        private double subtotal;
        private double iva;
        private double total;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder pago(Pago pago) {
            this.pago = pago;
            return this;
        }

        public Builder numeroFactura(String numeroFactura) {
            this.numeroFactura = numeroFactura;
            return this;
        }

        public Builder fechaEmision(Date fechaEmision) {
            this.fechaEmision = fechaEmision;
            return this;
        }

        public Builder detalle(String detalle) {
            this.detalle = detalle;
            return this;
        }

        public Builder subtotal(double subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public Builder iva(double iva) {
            this.iva = iva;
            return this;
        }

        public Builder total(double total) {
            this.total = total;
            return this;
        }

        public Factura build() {
            return new Factura(this);
        }
    }

    public int getId() {
        return id;
    }

    public Pago getPago() {
        return pago;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public String getDetalle() {
        return detalle;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

