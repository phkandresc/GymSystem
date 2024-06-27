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
}
