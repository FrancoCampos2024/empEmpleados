package com.example.demo.Servicios.Serviciosimpl;

import com.example.demo.Entidades.Contrato;
import com.example.demo.Repositorio.ContratoRepositorio;
import com.example.demo.Servicios.ContratoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contratoservicios")
public class ContratoServiciosimpl implements ContratoServicios {

    @Autowired
    @Qualifier("contratorepositorio")
    private ContratoRepositorio contratorepo;

    @Override
    public void insertarcontrato(Contrato contrato) {
        contratorepo.save(contrato);
    }

    @Override
    public void salarioempleadocontrato(Contrato contrato) {

    }

    @Override
    public Contrato buscarcontratoporidcontrato(int idcontrato) {
        return contratorepo.buscarPorIdcontrato(idcontrato);
    }

    @Override
    public void actualizarcontrato(Contrato contrato) {
        contratorepo.save(contrato);
    }

    @Override
    public List<Contrato> listarcontratos() {
        return contratorepo.findAll();
    }

    @Override
    public Contrato buscarcontratoxempleado(int idempleado) {
        return contratorepo.buscarPorIdempleado(idempleado);
    }

    @Override
    public String buscarBancoContrato(int idcontrato) {
        return contratorepo.buscarBanco(idcontrato);
    }
}
