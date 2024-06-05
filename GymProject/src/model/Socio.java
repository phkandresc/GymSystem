package model;

import java.util.Date;

public class Socio {
    private int id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private String email;
    private String numeroTelefono;
    private Date fechaNacimiento;


    public Socio(int id, String nombre, String apellido, String cedula, String direccion, String email, String numeroTelefono, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
        this.fechaNacimiento = fechaNacimiento;
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public static class SocioBuild implements IBuilder{
        private int id;
        private String nombre;
        private String apellido;
        private String cedula;
        private String direccion;
        private String email;
        private String numeroTelefono;
        private Date fechaNacimiento;

        public SocioBuild id(int id) {
            this.id = id;
            return this;
        }

        public SocioBuild nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public SocioBuild apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public SocioBuild cedula(String cedula) {
            this.cedula = cedula;
            return this;
        }

        public SocioBuild direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public SocioBuild email(String email) {
            this.email = email;
            return this;
        }

        public SocioBuild numeroTelefono(String numeroTelefono) {
            this.numeroTelefono = numeroTelefono;
            return this;
        }

        public SocioBuild fechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        @Override
        public Socio build() {
            return new Socio(id, nombre, apellido, cedula, direccion, email, numeroTelefono, fechaNacimiento);
        }
    }
}
