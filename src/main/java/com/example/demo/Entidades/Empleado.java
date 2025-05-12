
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
@Table(name="Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;
            
    @Column
    private String EmpNombre;
    
    @Column
    private String EmpApePaterno;
    
    @Column
    private String EmpApeMaterno;
    
    @Column(length = 8)
    private String EmpDni;
    
    @Column(length=6)
    private String EmpCodigo;
    
    @Column(length = 1)
    private String EmpGenero;
    
    @Column
    private double EmpSalario;
    
    @Column
    private java.sql.Date EmpFecNaci;

    @Column(name = "EmpFoto", columnDefinition = "LONGBLOB")
    private byte[] EmpFoto;

    @Column
    private java.sql.Date EmpFecIngreso;
    
    @Column
    private byte EmpEstado;
    
    @ManyToOne
    @JoinColumn(name="idEstadoCivil",foreignKey = @ForeignKey(name = "fk_empleado_EstadoCivil"))
    private EstadoCivil Empestadocivil;
       
    @Column
    private String EmpCci;
    
    @ManyToOne
    @JoinColumn(name="idBanco",foreignKey = @ForeignKey(name = "fk_empleado_Banco"))
    private Banco Empbanco;


    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getEmpNombre() {
        return EmpNombre;
    }

    public void setEmpNombre(String empNombre) {
        EmpNombre = empNombre;
    }

    public String getEmpApePaterno() {
        return EmpApePaterno;
    }

    public void setEmpApePaterno(String empApePaterno) {
        EmpApePaterno = empApePaterno;
    }

    public String getEmpApeMaterno() {
        return EmpApeMaterno;
    }

    public void setEmpApeMaterno(String empApeMaterno) {
        EmpApeMaterno = empApeMaterno;
    }

    public String getEmpDni() {
        return EmpDni;
    }

    public void setEmpDni(String empDni) {
        EmpDni = empDni;
    }

    public String getEmpCodigo() {
        return EmpCodigo;
    }

    public void setEmpCodigo(String empCodigo) {
        EmpCodigo = empCodigo;
    }

    public String getEmpGenero() {
        return EmpGenero;
    }

    public void setEmpGenero(String empGenero) {
        EmpGenero = empGenero;
    }

    public double getEmpSalario() {
        return EmpSalario;
    }

    public void setEmpSalario(double empSalario) {
        EmpSalario = empSalario;
    }

    public Date getEmpFecNaci() {
        return EmpFecNaci;
    }

    public void setEmpFecNaci(Date empFecNaci) {
        EmpFecNaci = empFecNaci;
    }

    public byte[] getEmpFoto() {
        return EmpFoto;
    }

    public void setEmpFoto(byte[] empFoto) {
        EmpFoto = empFoto;
    }

    public Date getEmpFecIngreso() {
        return EmpFecIngreso;
    }

    public void setEmpFecIngreso(Date empFecIngreso) {
        EmpFecIngreso = empFecIngreso;
    }

    public byte getEmpEstado() {
        return EmpEstado;
    }

    public void setEmpEstado(byte empEstado) {
        EmpEstado = empEstado;
    }

    public EstadoCivil getEmpestadocivil() {
        return Empestadocivil;
    }

    public void setEmpestadocivil(EstadoCivil empestadocivil) {
        Empestadocivil = empestadocivil;
    }

    public String getEmpCci() {
        return EmpCci;
    }

    public void setEmpCci(String empCci) {
        EmpCci = empCci;
    }

    public Banco getEmpbanco() {
        return Empbanco;
    }

    public void setEmpbanco(Banco empbanco) {
        Empbanco = empbanco;
    }
}
