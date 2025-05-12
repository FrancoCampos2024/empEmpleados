package com.example.demo.Servicios;

import com.example.demo.Entidades.Boletapago;
import java.util.List;

public interface BoletaPagoServicios {
    List<Boletapago> obtenerTodasLasBoletas();

    Boletapago obtenerBoletaPorId(Integer id);

}