package model;

public class Membresia {
    private int id;
    private String tipo;
    private String fechaInicio;
    private String fechaFin;
    private double costo;
    private String estado;

    private Membresia(MembresiaBuilder membresiaBuilder) {
        this.id = membresiaBuilder.id;
        this.tipo = membresiaBuilder.tipo;
        this.fechaInicio = membresiaBuilder.fechaInicio;
        this.fechaFin = membresiaBuilder.fechaFin;
        this.costo = membresiaBuilder.costo;
        this.estado = membresiaBuilder.estado;
    }



    public static class MembresiaBuilder {
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

        public Membresia build() {
            return new Membresia(this);
        }
    }
}
