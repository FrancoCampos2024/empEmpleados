/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.Repositorio;

import com.example.demo.Entidades.Empleado;
import java.io.Serializable;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo Campos
 */
@Repository("empleadorepositorio")
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Serializable> {

    @Query("SELECT e FROM Empleado e WHERE e.idEmpleado = :idempleado")
    public Empleado buscarPorId(@Param("idempleado") int id);

    @Query("SELECT e FROM Empleado e WHERE e.EmpEstado = 0")
    List<Empleado> buscarEmpleadosSinContratoActivo();

    @Query(value = "SELECT * FROM empleado ORDER BY id_empleado DESC LIMIT 1", nativeQuery = true)
    public Empleado obtenerUltimoEmpleado();

    @Modifying
    @Transactional
    @Query(value = "update  empleado set emp_estado=1 where id_empleado = :idempleado", nativeQuery = true)
    public void Editarestadosegunidempleado(@Param("idempleado") int id);

}
