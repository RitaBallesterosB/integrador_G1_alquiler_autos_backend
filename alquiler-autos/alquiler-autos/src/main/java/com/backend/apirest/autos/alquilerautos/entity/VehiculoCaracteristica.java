package com.backend.apirest.autos.alquilerautos.entity;

import javax.persistence.*;


    @Entity
    @Table(name = "VEHICULOS_HAS_CARACTERISTICAS")
    public class VehiculoCaracteristica {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "VEHICULO_ID")
        private Vehiculo vehiculo;

        @ManyToOne
        @JoinColumn(name = "CARACTERISTICA_ID")
        private Caracteristica caracteristica;

        public VehiculoCaracteristica() {
        }

        public VehiculoCaracteristica(Long id, Vehiculo vehiculo, Caracteristica caracteristica) {
            this.id = id;
            this.vehiculo = vehiculo;
            this.caracteristica = caracteristica;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Vehiculo getVehiculo() {
            return vehiculo;
        }

        public void setVehiculo(Vehiculo vehiculo) {
            this.vehiculo = vehiculo;
        }

        public Caracteristica getCaracteristica() {
            return caracteristica;
        }

        public void setCaracteristica(Caracteristica caracteristica) {
            this.caracteristica = caracteristica;
        }
    }

