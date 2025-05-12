/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.Repositorio;

import com.example.demo.Entidades.Banco;
import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo Campos
 */
@Repository("bancorepositorio")
public interface BancoRepositorio extends JpaRepository<Banco, Serializable> {
    @Query("SELECT b FROM Banco b WHERE b.idBanco = :idbanco")
    public Banco buscarPorId(@Param("idbanco") int id);
}
