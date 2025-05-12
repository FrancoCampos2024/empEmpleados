/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.Repositorio;

import com.example.demo.Entidades.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo Campos
 */

@Repository("arearepositorio")
public interface AreaRepositorio extends JpaRepository<Area, Integer> {

    @Query("SELECT a FROM Area a WHERE a.idArea = :idarea")
    public Area buscarPorId(@Param("idarea") int id);

}
