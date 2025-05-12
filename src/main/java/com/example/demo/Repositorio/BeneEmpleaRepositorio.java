/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.Repositorio;

import com.example.demo.Entidades.Beneficio;
import com.example.demo.Entidades.BeneficiosEmpleado;
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
@Repository("beneemplearepositorio")
public interface BeneEmpleaRepositorio extends JpaRepository<BeneficiosEmpleado, Serializable> {

    @Query("SELECT be FROM BeneficiosEmpleado be WHERE be.idEmpleadoBeneficio = :idEmpleadoBeneficio")
    public BeneficiosEmpleado buscarPorId(@Param("idEmpleadoBeneficio") int id);

    @Query(value = "SELECT b.id_beneficio, b.ben_descripcion, b.ben_monto, b.mes_beneficio FROM beneficio_empleado be "
            +
            "INNER JOIN empleado e on be.id_empleado= e.id_empleado " +
            "INNER JOIN beneficio b on be.id_beneficio= b.id_beneficio where e.id_empleado= :idempleado and b.mes_beneficio=:mesBeneficio", nativeQuery = true)
    public List<Beneficio> findByEmpleadoAndMesBeneficio(@Param("idempleado") int idempleado,
            @Param("mesBeneficio") int mesBeneficio);


    @Query("SELECT be.BEBeneficio.idBeneficio FROM BeneficiosEmpleado be WHERE be.BEempleado.idEmpleado = :empleadoId")
    List <Integer> idBeneficiosAsignadosxEmpleado(@Param("empleadoId") int empleadoId);

    @Modifying
    @Transactional
    @Query("DELETE FROM BeneficiosEmpleado be WHERE be.BEempleado.idEmpleado = :empleadoId AND be.BEBeneficio.idBeneficio = :beneficioId")
    public void eliminarBeneficioPorEmpleadoYBeneficio(@Param("empleadoId") Integer empleadoId, @Param("beneficioId") Integer beneficioId);

    @Query("SELECT COUNT(be) > 0 FROM BeneficiosEmpleado be WHERE be.BEempleado.idEmpleado = :empleadoId AND be.BEBeneficio.idBeneficio = :beneficioId")
    public boolean estaAsignadoAunBeneficio(@Param("empleadoId") Integer empleadoId,@Param("beneficioId") Integer beneficioId);

}
