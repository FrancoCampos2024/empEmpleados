package com.example.demo.Servicios.Serviciosimpl;

import com.example.demo.Entidades.Boletapago;
import com.example.demo.Repositorio.BoletaPagoRepositorio;
import com.example.demo.Servicios.BoletaPagoServicios;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletaPagoServiciosImpl implements BoletaPagoServicios {

    private final BoletaPagoRepositorio boletaPagoRepositorio;

    public BoletaPagoServiciosImpl(BoletaPagoRepositorio boletaPagoRepositorio) {
        this.boletaPagoRepositorio = boletaPagoRepositorio;
    }

    @Override
    public List<Boletapago> obtenerTodasLasBoletas() {
        return boletaPagoRepositorio.findAll();
    }

    @Override
    public Boletapago obtenerBoletaPorId(Integer id) {
        Optional<Boletapago> boleta = boletaPagoRepositorio.findById(id);
        return boleta.orElse(null);
    }

}