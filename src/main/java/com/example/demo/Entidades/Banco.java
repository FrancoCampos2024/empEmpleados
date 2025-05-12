package com.example.demo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Banco")
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBanco;
    
    @Column
    private String BanNombre;
    @Column
    private byte BanEstado;

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getBanNombre() {
        return BanNombre;
    }

    public void setBanNombre(String banNombre) {
        BanNombre = banNombre;
    }

    public byte getBanEstado() {
        return BanEstado;
    }

    public void setBanEstado(byte banEstado) {
        BanEstado = banEstado;
    }
}
