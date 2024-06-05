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
    private Membresia membresia;

    private Socio(SocioBuilder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.cedula = builder.cedula;
        this.direccion = builder.direccion;
        this.email = builder.email;
        this.numeroTelefono = builder.numeroTelefono;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.membresia = builder.membresia;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public static class SocioBuilder{
        private int id;
        private String nombre;
        private String apellido;
        private String cedula;
        private String direccion;
        private String email;
        private String numeroTelefono;
        private Date fechaNacimiento;
        private Membresia membresia;

        public SocioBuilder setId(int id) {
            this.id = id;
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

        public SocioBuilder setCedula(String cedula) {
            this.cedula = cedula;
            return this;
        }

        public SocioBuilder setDireccion(String direccion) {
            this.direccion = direccion;
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

        public SocioBuilder setFechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public SocioBuilder setMembresia(Membresia membresia) {
            this.membresia = membresia;
            return this;
        }

        public Socio build() {
            return new Socio(this);
        }
    }

}
