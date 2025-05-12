/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.Repositorio;

import com.example.demo.Entidades.HistorialSalario;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo Campos
 */
@Repository("historialsalariorespositorio")
public interface HistorialSalarioRepositorio extends JpaRepository<HistorialSalario, Serializable>{

    @Query("SELECT COUNT(s) > 0 FROM HistorialSalario s WHERE s.HisEmpleado.idEmpleado = :idEmpleado AND s.HisSalario = :monto")
    boolean existeSalarioPorEmpleado(@Param("idEmpleado") int idEmpleado, @Param("monto") double monto);


}
