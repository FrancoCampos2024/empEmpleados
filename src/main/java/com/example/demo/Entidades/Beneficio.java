
package com.example.demo.Entidades;

import java.time.Month;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Beneficio")
public class Beneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBeneficio;

    @Column
    private String BenDescripcion;
    @Column
    private double BenMonto;

    private Month MesBeneficio;

    public int getIdBeneficio() {
        return idBeneficio;
    }

    public void setIdBeneficio(int idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public String getBenDescripcion() {
        return BenDescripcion;
    }

    public void setBenDescripcion(String benDescripcion) {
        BenDescripcion = benDescripcion;
    }

    public double getBenMonto() {
        return BenMonto;
    }

    public void setBenMonto(double benMonto) {
        BenMonto = benMonto;
    }

    public Month getMesBeneficio() {
        return MesBeneficio;
    }

    public void setMesBeneficio(Month mesBeneficio) {
        MesBeneficio = mesBeneficio;
    }

    public static boolean esBeneficioAplicable(Beneficio beneficio, Month mes) {
        return beneficio.getMesBeneficio().equals(mes);
    }

}
