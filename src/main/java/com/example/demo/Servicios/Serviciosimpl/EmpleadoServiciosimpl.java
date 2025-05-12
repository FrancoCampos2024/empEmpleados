package com.example.demo.Servicios.Serviciosimpl;

import com.example.demo.Entidades.Empleado;
import com.example.demo.Repositorio.EmpleadoRepositorio;
import com.example.demo.Servicios.EmpleadoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("empleadoservicios")
public class EmpleadoServiciosimpl implements EmpleadoServicios {


    @Autowired
    @Qualifier("empleadorepositorio")
    private EmpleadoRepositorio empleadorepo;

    @Override
    public List<Empleado> listarempleados() {
        return empleadorepo.findAll();
    }

    @Override
    public Empleado buscarid(int idempleado) {
        return empleadorepo.buscarPorId(idempleado);
    }

    @Override
    public void insertar(Empleado empleado) {
        empleadorepo.save(empleado);
    }

    @Override
    public Empleado actualizar(Empleado empleado) {
        return empleadorepo.save(empleado);
    }

    @Override
    public List<Empleado> lisEmpleadosSinContrato() {
        return empleadorepo.buscarEmpleadosSinContratoActivo();
    }

    @Override
    public Empleado obtenerultimoid() {
        return empleadorepo.obtenerUltimoEmpleado();
    }

    @Override
    public void editarestadoporidempleado(int id) {
        empleadorepo.Editarestadosegunidempleado(id);
    }


}
