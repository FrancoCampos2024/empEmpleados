package com.example.demo.Servicios;

import com.example.demo.Entidades.Beneficio;

import java.util.List;

public interface BeneficioServicios {

    public List<Beneficio> listarbeneficios();
    public Beneficio buscarbeneficio(int idbeneficio);
    public void insertarbeneficio(Beneficio beneficio);
    public void actualizarbeneficio(Beneficio beneficio);

}
