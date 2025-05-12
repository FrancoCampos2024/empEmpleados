/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.Repositorio;

import com.example.demo.Entidades.Beneficio;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo Campos
 */
@Repository("beneficiorepositorio")
public interface BeneficioRepositorio extends JpaRepository<Beneficio, Serializable> {

    @Query("SELECT b FROM Beneficio b WHERE b.idBeneficio = :idbeneficio")
    public Beneficio buscarPorId(@Param("idbeneficio") int id);

    @Query(value = "SELECT * FROM beneficio b WHERE b.mes_beneficio = :mesBeneficio", nativeQuery = true)
    public List<Beneficio> findByMesBeneficio(@Param("mesBeneficio") int mesBeneficio);

}
