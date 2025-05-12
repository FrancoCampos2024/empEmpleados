package com.example.demo.Servicios;

import com.example.demo.Entidades.Empleado;

import java.util.List;

public interface EmpleadoServicios {

    public  List<Empleado> listarempleados();
    public  Empleado buscarid(int idempleado);
    public  void insertar(Empleado empleado);
    public  Empleado actualizar(Empleado empleado);
    public List<Empleado> lisEmpleadosSinContrato();
    public Empleado obtenerultimoid();
    public void editarestadoporidempleado(int id);

}
