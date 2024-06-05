package model;

public class Membresia {
    private int id;
    private String tipo;
    private String fechaInicio;
    private String fechaFin;
    private double costo;
    private String estado;

    public Membresia(int id, String tipo, String fechaInicio, String fechaFin, double costo, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public static class MembresiaBuilder{
        private int id;
        private String tipo;
        private String fechaInicio;
        private String fechaFin;
        private double costo;
        private String estado;

        public MembresiaBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public MembresiaBuilder setTipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public MembresiaBuilder setFechaInicio(String fechaInicio) {
            this.fechaInicio = fechaInicio;
            return this;
        }

        public MembresiaBuilder setFechaFin(String fechaFin) {
            this.fechaFin = fechaFin;
            return this;
        }

        public MembresiaBuilder setCosto(double costo) {
            this.costo = costo;
            return this;
        }

        public MembresiaBuilder setEstado(String estado) {
            this.estado = estado;
            return this;
        }

        public Membresia build(){
            return new Membresia(id, tipo, fechaInicio, fechaFin, costo, estado);
        }

    }
}
