package com.example.demo.Servicios.Serviciosimpl;

import com.example.demo.Entidades.Beneficio;
import com.example.demo.Repositorio.BeneficioRepositorio;
import com.example.demo.Servicios.BeneficioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("beneficioservicios")
public class BeneficioServiciosimpl implements BeneficioServicios {

    @Autowired
    @Qualifier("beneficiorepositorio")
    private BeneficioRepositorio beneficiorepo;

    @Override
    public List<Beneficio> listarbeneficios() {
        return beneficiorepo.findAll();
    }

    @Override
    public Beneficio buscarbeneficio(int idbeneficio) {
        return beneficiorepo.buscarPorId(idbeneficio);
    }

    @Override
    public void insertarbeneficio(Beneficio beneficio) {
        beneficiorepo.save(beneficio);
    }

    @Override
    public void actualizarbeneficio(Beneficio beneficio) {
        beneficiorepo.save(beneficio);
    }
}
