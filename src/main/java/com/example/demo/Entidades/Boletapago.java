
package com.example.demo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "Boletapago")
public class Boletapago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBoleta;
    @Column
    private java.sql.Date BolFecEmisionBole;
    @Column
    private double BolMontoFijo;
    @Column
    private double BolMontoBeneficio;
    @Column
    private double BolMontoNetoBole;

    private String BeneficiosAplicados;

    @ManyToOne
    @JoinColumn(name = "idContrato", foreignKey = @ForeignKey(name = "fk_boletapago_contrato"))
    private Contrato BolContrato;

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Date getBolFecEmisionBole() {
        return BolFecEmisionBole;
    }

    public void setBolFecEmisionBole(Date bolFecEmisionBole) {
        BolFecEmisionBole = bolFecEmisionBole;
    }

    public double getBolMontoFijo() {
        return BolMontoFijo;
    }

    public void setBolMontoFijo(double bolMontoFijo) {
        BolMontoFijo = bolMontoFijo;
    }

    public double getBolMontoBeneficio() {
        return BolMontoBeneficio;
    }

    public void setBolMontoBeneficio(double bolMontoBeneficio) {
        BolMontoBeneficio = bolMontoBeneficio;
    }

    public double getBolMontoNetoBole() {
        return BolMontoNetoBole;
    }

    public void setBolMontoNetoBole(double bolMontoNetoBole) {
        BolMontoNetoBole = bolMontoNetoBole;
    }

    public Contrato getBolContrato() {
        return BolContrato;
    }

    public void setBolContrato(Contrato bolContrato) {
        BolContrato = bolContrato;
    }

    public String getBeneficiosAplicados() {
        return BeneficiosAplicados;
    }

    public void setBeneficiosAplicados(String beneficiosAplicados) {
        BeneficiosAplicados = beneficiosAplicados;
    }
}
