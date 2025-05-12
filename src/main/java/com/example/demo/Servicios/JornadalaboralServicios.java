package com.example.demo.Servicios;

import com.example.demo.Entidades.JornadaLaboral;

import java.util.List;

public interface JornadalaboralServicios {
    public List<JornadaLaboral>listarjornadas();
    public JornadaLaboral buscarid(int idjornada);
}
