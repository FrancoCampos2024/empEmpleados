package com.example.demo.Servicios;

import com.example.demo.Entidades.ModalidadContrato;

import java.util.List;

public interface ModalidadContratoServicios {
    public List<ModalidadContrato> listarModalidadContrato();
    public ModalidadContrato buscaridmoda(int id);
}
