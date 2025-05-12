package com.example.demo.Servicios.Serviciosimpl;

import com.example.demo.Entidades.EstadoCivil;
import com.example.demo.Repositorio.EstadocivilRepositorio;
import com.example.demo.Servicios.EstadoCivilServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("estadocivilservicios")
public class EstadocivilServiciosimpl implements EstadoCivilServicios {
    @Autowired
    @Qualifier("estadocivilrepositorio")
    private EstadocivilRepositorio estadocivilrepo;

    @Override
    public List<EstadoCivil> listarestadocivil() {
        return estadocivilrepo.findAll();
    }

    @Override
    public EstadoCivil buscarid(int idestadocivil) {
        return estadocivilrepo.buscarPorId(idestadocivil);
    }
}
