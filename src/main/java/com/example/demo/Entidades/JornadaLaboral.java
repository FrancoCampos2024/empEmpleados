
package com.example.demo.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="JornadaLaboral")
public class JornadaLaboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJornada;
    private String JorTipo;

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public String getJorTipo() {
        return JorTipo;
    }

    public void setJorTipo(String jorTipo) {
        JorTipo = jorTipo;
    }
}
