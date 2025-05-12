package com.example.demo.Servicios;

import com.example.demo.Entidades.Contrato;

import java.util.List;

public interface ContratoServicios {

    public List<Contrato> listarcontratos();

    public Contrato buscarcontratoxempleado(int idempleado);

    public void insertarcontrato(Contrato contrato);

    public void salarioempleadocontrato(Contrato contrato);

    public Contrato buscarcontratoporidcontrato(int idcontrato);

    public void actualizarcontrato(Contrato contrato);

    public String buscarBancoContrato(int idcontrato);

}
