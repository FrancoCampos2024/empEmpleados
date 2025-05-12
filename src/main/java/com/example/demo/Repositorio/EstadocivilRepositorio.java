/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.Repositorio;

import com.example.demo.Entidades.EstadoCivil;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo Campos
 */
@Repository("estadocivilrepositorio")
public interface EstadocivilRepositorio extends JpaRepository<EstadoCivil, Serializable> {
    @Query("SELECT estc FROM EstadoCivil estc WHERE estc.idEstadoCivil = :idestadocivil")
    public EstadoCivil buscarPorId(@Param("idestadocivil") int id);
}
