
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
@Table(name = "Contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContrato;

    @Column
    private java.sql.Date ContFecIni;

    @Column
    private java.sql.Date ContFechfin;

    @Column
    private byte ContEstadoContrato;

    @Column
    private double ContSalario;

    @ManyToOne
    @JoinColumn(name = "idArea", foreignKey = @ForeignKey(name = "fk_contrato_area"))
    private Area Contarea;

    @ManyToOne
    @JoinColumn(name = "idModalidad", foreignKey = @ForeignKey(name = "fk_contrato_modalidadcontrato"))
    private ModalidadContrato ContmodContrato;

    @ManyToOne
    @JoinColumn(name = "idEmpleado", foreignKey = @ForeignKey(name = "fk_contrato_empleado"))
    private Empleado Contempleado;

    @ManyToOne
    @JoinColumn(name = "idJornada", foreignKey = @ForeignKey(name = "fk_contrato_jornadalaboral"))
    private JornadaLaboral Contjornada;

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Date getContFecIni() {
        return ContFecIni;
    }

    public void setContFecIni(Date contFecIni) {
        ContFecIni = contFecIni;
    }

    public Date getContFechfin() {
        return ContFechfin;
    }

    public void setContFechfin(Date contFechfin) {
        ContFechfin = contFechfin;
    }

    public byte getContEstadoContrato() {
        return ContEstadoContrato;
    }

    public void setContEstadoContrato(byte contEstadoContrato) {
        ContEstadoContrato = contEstadoContrato;
    }

    public Area getContarea() {
        return Contarea;
    }

    public void setContarea(Area contarea) {
        Contarea = contarea;
    }

    public ModalidadContrato getContmodContrato() {
        return ContmodContrato;
    }

    public void setContmodContrato(ModalidadContrato contmodContrato) {
        ContmodContrato = contmodContrato;
    }

    public Empleado getContempleado() {
        return Contempleado;
    }

    public void setContempleado(Empleado contempleado) {
        Contempleado = contempleado;
    }

    public JornadaLaboral getContjornada() {
        return Contjornada;
    }

    public void setContjornada(JornadaLaboral contjornada) {
        Contjornada = contjornada;
    }

    public double getContSalario() {
        return ContSalario;
    }

    public void setContSalario(double contSalario) {
        ContSalario = contSalario;
    }
}
