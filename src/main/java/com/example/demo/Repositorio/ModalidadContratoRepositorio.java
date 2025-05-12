/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.Repositorio;

import com.example.demo.Entidades.ModalidadContrato;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo Campos
 */
@Repository("modcontratorepositorio")
public interface ModalidadContratoRepositorio extends JpaRepository<ModalidadContrato, Serializable> {

    @Query("SELECT m FROM ModalidadContrato m WHERE m.idModalidad = :idmodalidad")
    public ModalidadContrato buscarPorId(@Param("idmodalidad") int id);
}
