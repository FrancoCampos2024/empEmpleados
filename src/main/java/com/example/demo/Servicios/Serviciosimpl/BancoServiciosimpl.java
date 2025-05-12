package com.example.demo.Servicios.Serviciosimpl;


import com.example.demo.Entidades.Banco;
import com.example.demo.Repositorio.BancoRepositorio;
import com.example.demo.Servicios.BancoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bancoservicios")
public class BancoServiciosimpl implements BancoServicios {


    @Autowired
    @Qualifier("bancorepositorio")
    private BancoRepositorio bancorepo;

    @Override
    public List<Banco> listabanco() {
        return bancorepo.findAll();
    }

    @Override
    public Banco buscarid(int idbanco) {
        return bancorepo.buscarPorId(idbanco);
    }

}
