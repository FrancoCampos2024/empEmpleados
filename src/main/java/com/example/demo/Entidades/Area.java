
package com.example.demo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArea;
    
    @Column
    private String ANombre;
    @Column
    private float ASalario;



    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getANombre() {
        return ANombre;
    }

    public void setANombre(String ANombre) {
        this.ANombre = ANombre;
    }

    public float getASalario() {
        return ASalario;
    }

    public void setASalario(float ASalario) {
        this.ASalario = ASalario;
    }
}
