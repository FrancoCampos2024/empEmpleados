/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.Repositorio;

import com.example.demo.Entidades.Contrato;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Leonardo Campos
 */
@Repository("contratorepositorio")
public interface ContratoRepositorio extends JpaRepository<Contrato, Serializable> {

        @Query("SELECT c FROM Contrato c WHERE c.Contempleado.idEmpleado = :idempleado AND c.ContEstadoContrato = 1")
        public Contrato buscarPorIdempleado(@Param("idempleado") int id);

        @Query("SELECT c FROM Contrato c WHERE c.idContrato = :idcontrato")
        public Contrato buscarPorIdcontrato(@Param("idcontrato") int id);

        @Modifying
        @Transactional
        @Query("UPDATE Contrato c SET c.Contempleado.EmpSalario = :nuevoSalario WHERE c.Contarea.idArea = :idArea AND c.ContEstadoContrato = 1")
        void actualizarSalarioEmpleadoPorAreaActiva(@Param("nuevoSalario") Double nuevoSalario,
                        @Param("idArea") Integer idArea);

        @Query(value = "SELECT b.ban_nombre " +
                        " FROM contrato c inner join empleado ep on c.id_empleado = ep.id_empleado " +
                        " inner join banco b on ep.id_banco = b.id_banco where c.id_contrato= :idcontrato", nativeQuery = true)
        public String buscarBanco(@Param("idcontrato") int idcontrato);

}
