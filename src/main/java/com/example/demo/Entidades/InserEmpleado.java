package com.example.demo.Entidades;

import java.sql.Date;

public class InserEmpleado {

    // Datos empleados

    private String EmpNombre;

    private String EmpApePaterno;

    private String EmpApeMaterno;

    private String EmpDni;

    private java.sql.Date EmpFecNaci;

    private java.sql.Date EmpFecIngreso;

    private String EmpGenero;

    private int idestacivil;

    private byte EmpEstado;

    private String EmpCci;

    private int idbanco;

    // private byte[] EmpFoto;

    // Datos contrato

    private int idarea;

    private int idjornada;

    private int idmodalidadcontrato;

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

    public Date getEmpFecNaci() {
        return EmpFecNaci;
    }

    public void setEmpFecNaci(Date empFecNaci) {
        EmpFecNaci = empFecNaci;
    }

    public Date getEmpFecIngreso() {
        return EmpFecIngreso;
    }

    public void setEmpFecIngreso(Date empFecIngreso) {
        EmpFecIngreso = empFecIngreso;
    }

    public String getEmpGenero() {
        return EmpGenero;
    }

    public void setEmpGenero(String empGenero) {
        EmpGenero = empGenero;
    }

    public int getIdestacivil() {
        return idestacivil;
    }

    public void setIdestacivil(int idestacivil) {
        this.idestacivil = idestacivil;
    }

    public byte getEmpEstado() {
        return EmpEstado;
    }

    public void setEmpEstado(byte empEstado) {
        EmpEstado = empEstado;
    }

    public String getEmpCci() {
        return EmpCci;
    }

    public void setEmpCci(String empCci) {
        EmpCci = empCci;
    }

    public int getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(int idbanco) {
        this.idbanco = idbanco;
    }

    /*
     * public byte[] getEmpFoto() {
     * return EmpFoto;
     * }
     * 
     * public void setEmpFoto(byte[] empFoto) {
     * EmpFoto = empFoto;
     * }
     */

    public int getIdarea() {
        return idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }

    public int getIdjornada() {
        return idjornada;
    }

    public void setIdjornada(int idjornada) {
        this.idjornada = idjornada;
    }

    public int getIdmodalidadcontrato() {
        return idmodalidadcontrato;
    }

    public void setIdmodalidadcontrato(int idmodalidadcontrato) {
        this.idmodalidadcontrato = idmodalidadcontrato;
    }
}
