/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.Repositorio;

import com.example.demo.Entidades.JornadaLaboral;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo Campos
 */
@Repository("jornadalaboralrepositorio")
public interface JornadaLaboralRepositorio extends JpaRepository<JornadaLaboral, Serializable> {
    @Query("SELECT j FROM JornadaLaboral j WHERE j.idJornada = :idjornada")
    public JornadaLaboral buscarPorId(@Param("idjornada") int id);
}
