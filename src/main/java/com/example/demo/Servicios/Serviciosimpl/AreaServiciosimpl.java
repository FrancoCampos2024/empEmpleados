package com.example.demo.Servicios.Serviciosimpl;


import com.example.demo.Entidades.Area;
import com.example.demo.Repositorio.AreaRepositorio;
import com.example.demo.Servicios.AreaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("areaservicios")
public class AreaServiciosimpl implements AreaServicios {


    @Autowired
    @Qualifier("arearepositorio")
    private AreaRepositorio arearepo;

    @Override
    public List<Area> listarAreas() {
        return arearepo.findAll();
    }

    @Override
    public void GuardarArea(Area area) {
        arearepo.save(area);
    }

    @Override
    public Area buscarAreaPorID(int id) {
        return arearepo.buscarPorId(id);
    }
}











