package com.example.demo.Servicios.Serviciosimpl;

import com.example.demo.Entidades.JornadaLaboral;
import com.example.demo.Repositorio.JornadaLaboralRepositorio;
import com.example.demo.Servicios.JornadalaboralServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jornadalaboralservicios")
public class JornadalaboralServiciosimpl implements JornadalaboralServicios {

    @Autowired
    @Qualifier("jornadalaboralrepositorio")
    private JornadaLaboralRepositorio jornadalaboralrepo;

    @Override
    public List<JornadaLaboral> listarjornadas() {
        return jornadalaboralrepo.findAll();
    }

    @Override
    public JornadaLaboral buscarid(int idjornada) {
        return jornadalaboralrepo.buscarPorId(idjornada);
    }

}
