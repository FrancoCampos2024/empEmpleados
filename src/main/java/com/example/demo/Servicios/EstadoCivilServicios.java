package com.example.demo.Servicios;

import com.example.demo.Entidades.EstadoCivil;

import java.util.List;

public interface EstadoCivilServicios {

    public List<EstadoCivil> listarestadocivil();
    public EstadoCivil buscarid(int idestadocivil);
}
