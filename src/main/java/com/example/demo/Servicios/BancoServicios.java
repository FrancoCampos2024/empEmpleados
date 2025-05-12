package com.example.demo.Servicios;

import com.example.demo.Entidades.Banco;

import java.util.List;

public interface BancoServicios {
    public List<Banco> listabanco();
    public Banco buscarid(int idbanco);
}
