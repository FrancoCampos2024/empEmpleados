
package com.example.demo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="EstadoCivil")
public class EstadoCivil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstadoCivil;
    @Column
    private String EstTipo;

    public int getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(int idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public String getEstTipo() {
        return EstTipo;
    }

    public void setEstTipo(String estTipo) {
        EstTipo = estTipo;
    }
}
