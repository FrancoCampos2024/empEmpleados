package com.example.demo.Repositorio;

import com.example.demo.Entidades.Boletapago;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletaPagoRepositorio extends JpaRepository<Boletapago, Integer> {

    @Query(value = "SELECT bol_fec_emision_bole FROM boletapago WHERE id_contrato = :idContrato ORDER BY bol_fec_emision_bole DESC LIMIT 1", nativeQuery = true)
    Optional<Date> findUltimaFechaByContrato(@Param("idContrato") int idContrato);
}