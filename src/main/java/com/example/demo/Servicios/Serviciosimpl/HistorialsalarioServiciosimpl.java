package com.example.demo.Servicios.Serviciosimpl;

import com.example.demo.Entidades.HistorialSalario;
import com.example.demo.Repositorio.HistorialSalarioRepositorio;
import com.example.demo.Servicios.HistorialSalarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service( "historialsalarioservicios")
public class HistorialsalarioServiciosimpl  implements HistorialSalarioServicios {

    @Autowired
    @Qualifier("historialsalariorespositorio")
    private HistorialSalarioRepositorio historialsalariorepo;

    @Override
    public void insertar(HistorialSalario histsal) {
        historialsalariorepo.save(histsal);
    }

    @Override
    public boolean buscarsalarioigual(int idempleado, double monto) {
        return historialsalariorepo.existeSalarioPorEmpleado(idempleado,monto);
    }
}
