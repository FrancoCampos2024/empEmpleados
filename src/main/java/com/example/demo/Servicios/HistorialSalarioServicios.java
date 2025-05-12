package com.example.demo.Servicios;

import com.example.demo.Entidades.HistorialSalario;

public interface HistorialSalarioServicios {

    public void insertar(HistorialSalario histsal);
    public boolean buscarsalarioigual(int idempleado, double monto);
}
