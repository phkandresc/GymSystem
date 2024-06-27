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
}
