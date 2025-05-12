package com.example.demo.Servicios;

import com.example.demo.Entidades.Area;

import java.util.List;

public interface AreaServicios {

    public abstract List<Area> listarAreas();
    public abstract Area buscarAreaPorID(int id);
    public abstract void GuardarArea(Area area);

}
