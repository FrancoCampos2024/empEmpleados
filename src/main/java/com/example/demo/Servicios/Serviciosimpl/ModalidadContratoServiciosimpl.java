package com.example.demo.Servicios.Serviciosimpl;


import com.example.demo.Entidades.ModalidadContrato;
import com.example.demo.Repositorio.ModalidadContratoRepositorio;
import com.example.demo.Servicios.ModalidadContratoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service( "modalidadcontratoservicios")
public class ModalidadContratoServiciosimpl implements ModalidadContratoServicios {


    @Autowired
    @Qualifier("modcontratorepositorio")
    private ModalidadContratoRepositorio modcontratorepo;

    @Override
    public List<ModalidadContrato> listarModalidadContrato() {
        return modcontratorepo.findAll();
    }

    @Override
    public ModalidadContrato buscaridmoda(int id) {
        return modcontratorepo.buscarPorId(id);
    }

}
