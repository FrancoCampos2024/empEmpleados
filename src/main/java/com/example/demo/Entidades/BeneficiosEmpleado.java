
package com.example.demo.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "BeneficioEmpleado")
public class BeneficiosEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleadoBeneficio;

    @ManyToOne
    @JoinColumn(name = "idEmpleado", foreignKey = @ForeignKey(name = "fk_beneficioempleado_empleado"))
    private Empleado BEempleado;

    @ManyToOne
    @JoinColumn(name = "idBeneficio", foreignKey = @ForeignKey(name = "fk_beneficioempleado_beneficio"))
    private Beneficio BEBeneficio;

    public int getIdEmpleadoBeneficio() {
        return idEmpleadoBeneficio;
    }

    public void setIdEmpleadoBeneficio(int idEmpleadoBeneficio) {
        this.idEmpleadoBeneficio = idEmpleadoBeneficio;
    }

    public Empleado getBEempleado() {
        return BEempleado;
    }

    public void setBEempleado(Empleado BEempleado) {
        this.BEempleado = BEempleado;
    }

    public Beneficio getBEBeneficio() {
        return BEBeneficio;
    }

    public void setBEBeneficio(Beneficio BEBeneficio) {
        this.BEBeneficio = BEBeneficio;
    }
}
