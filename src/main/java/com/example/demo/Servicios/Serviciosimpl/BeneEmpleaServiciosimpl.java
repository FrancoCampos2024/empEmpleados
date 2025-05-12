package com.example.demo.Servicios.Serviciosimpl;


import com.example.demo.Entidades.BeneficiosEmpleado;
import com.example.demo.Repositorio.BeneEmpleaRepositorio;
import com.example.demo.Servicios.BeneEmpleaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("beneempleaservicios")
public class BeneEmpleaServiciosimpl implements BeneEmpleaServicios {
    @Autowired
    @Qualifier("beneemplearepositorio")
    private BeneEmpleaRepositorio beneemplearepo;

    @Override
    public List<BeneficiosEmpleado> listarBeneEmpleados() {
        return beneemplearepo.findAll();
    }

    @Override
    public BeneficiosEmpleado buscarBeneEmpleado(int idbeneficioempleado) {
        return beneemplearepo.buscarPorId(idbeneficioempleado);
    }

    @Override
    public void insertarBeneEmpleado(BeneficiosEmpleado beneficioempleado) {
            beneemplearepo.save(beneficioempleado);
    }

    @Override
    public void actualizarBeneEmpleado(BeneficiosEmpleado beneficioempleado) {
        beneemplearepo.save(beneficioempleado);
    }

    @Override
    public List<Integer> idbeneficioasignadoxempelado(int idempleado) {
        return beneemplearepo.idBeneficiosAsignadosxEmpleado(idempleado);
    }

    @Override
    public void borrarBeneficioxEmpleado(int idempleado, int idbeneficio) {
        beneemplearepo.eliminarBeneficioPorEmpleadoYBeneficio(idempleado,idbeneficio);
    }

    @Override
    public boolean existeBeneficioxEmpleado(int idempleado , int idbeneficio ) {
        return beneemplearepo.estaAsignadoAunBeneficio(idempleado,idbeneficio);
    }


}
