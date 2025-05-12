package com.example.demo.Servicios;

import com.example.demo.Entidades.BeneficiosEmpleado;

import java.util.List;

public interface BeneEmpleaServicios {

    public List<BeneficiosEmpleado> listarBeneEmpleados();
    public BeneficiosEmpleado buscarBeneEmpleado(int idbeneficioempleado);
    public void insertarBeneEmpleado(BeneficiosEmpleado beneficioempleado);
    public void actualizarBeneEmpleado(BeneficiosEmpleado beneficioempleado);
    public List<Integer> idbeneficioasignadoxempelado(int idempleado);
    public void borrarBeneficioxEmpleado(int idempleado, int idbeneficio);
    public boolean existeBeneficioxEmpleado(int idempleado, int idbeneficio);
}
