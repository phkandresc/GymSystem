package org.gimnasio.model;

import java.sql.Date;

public class Socio {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String email;
    private String numeroTelefono;
    private String direccion;
    private Date fechaNacimiento;

    // Constructor privado para el Builder
    private Socio(SocioBuilder builder) {
        this.id = builder.id;
        this.cedula = builder.cedula;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.email = builder.email;
        this.numeroTelefono = builder.numeroTelefono;
        this.direccion = builder.direccion;
        this.fechaNacimiento = builder.fechaNacimiento;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Builder class
    public static class SocioBuilder {
        private int id;
        private String cedula;
        private String nombre;
        private String apellido;
        private String email;
        private String numeroTelefono;
        private String direccion;
        private Date fechaNacimiento;

        public SocioBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public SocioBuilder setCedula(String cedula) {
            this.cedula = cedula;
            return this;
        }

        public SocioBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public SocioBuilder setApellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public SocioBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public SocioBuilder setNumeroTelefono(String numeroTelefono) {
            this.numeroTelefono = numeroTelefono;
            return this;
        }

        public SocioBuilder setDireccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public SocioBuilder setFechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Socio build() {
            return new Socio(this);
        }
    }

    @Override
    public String toString() {
        return "Socio{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
