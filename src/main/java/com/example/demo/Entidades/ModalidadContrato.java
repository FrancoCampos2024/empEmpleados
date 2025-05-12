
package com.example.demo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ModalidadContrato")
public class ModalidadContrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModalidad;
    @Column
    private String ModNombre;

    public int getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(int idModalidad) {
        this.idModalidad = idModalidad;
    }

    public String getModNombre() {
        return ModNombre;
    }

    public void setModNombre(String modNombre) {
        ModNombre = modNombre;
    }
}
