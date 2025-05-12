
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
@Table(name = "HistorialSalario")
public class HistorialSalario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistorial;

    @ManyToOne
    @JoinColumn(name = "idEmpleado", foreignKey = @ForeignKey(name = "fk_historialsalario_empleado"))
    private Empleado HisEmpleado;

    @Column
    private double HisSalario;
    @Column
    private java.sql.Date HisFechaCambio;

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Empleado getHisEmpleado() {
        return HisEmpleado;
    }

    public void setHisEmpleado(Empleado hisEmpleado) {
        HisEmpleado = hisEmpleado;
    }

    public double getHisSalario() {
        return HisSalario;
    }

    public void setHisSalario(double hisSalario) {
        HisSalario = hisSalario;
    }

    public Date getHisFechaCambio() {
        return HisFechaCambio;
    }

    public void setHisFechaCambio(Date hisFechaCambio) {
        HisFechaCambio = hisFechaCambio;
    }
}
