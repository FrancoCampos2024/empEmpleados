/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controlador;

import com.example.demo.Entidades.*;
import com.example.demo.Servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Empleado")
public class EmpleadoControlador {

    // LLamada de servicios de cada entidad
    @Autowired
    @Qualifier("empleadoservicios")
    private EmpleadoServicios empleadoServicios;

    @Autowired
    @Qualifier("contratoservicios")
    private ContratoServicios contratoservicios;

    @Autowired
    @Qualifier("bancoservicios")
    private BancoServicios bancoservicios;

    @Autowired
    @Qualifier("estadocivilservicios")
    private EstadoCivilServicios estadocivilservicios;

    @Autowired
    @Qualifier("historialsalarioservicios")
    private HistorialSalarioServicios historialsalarioservicios;

    @Autowired
    @Qualifier("areaservicios")
    private AreaServicios areaservicios;

    @Autowired
    @Qualifier("jornadalaboralservicios")
    private JornadalaboralServicios jornadalaboralServicios;

    @Autowired
    @Qualifier("modalidadcontratoservicios")
    private ModalidadContratoServicios modalidadContratoServicios;

    @Autowired
    @Qualifier("beneficioservicios")
    private BeneficioServicios beneficioServicios;

    @Autowired
    @Qualifier("beneempleaservicios")
    private BeneEmpleaServicios beneempleaservicios;

    @GetMapping("/ListaEmpleados")
    public String mostrarEmpleados(Model modelo) {
        for (Empleado e : empleadoServicios.listarempleados()) {
            Contrato co = contratoservicios.buscarcontratoxempleado(e.getIdEmpleado());
            if (co != null) {
                if (co.getContFechfin() != null) {
                    LocalDate hoy = LocalDate.now();
                    LocalDate fechaFinContrato = co.getContFechfin().toLocalDate();
                    long diferenciaDias = ChronoUnit.DAYS.between(fechaFinContrato, hoy);
                    if (diferenciaDias > 0) {
                        Empleado emp = empleadoServicios.buscarid(co.getContempleado().getIdEmpleado());
                        co.setContEstadoContrato((byte) 0);
                        emp.setEmpEstado((byte) 0);
                        contratoservicios.actualizarcontrato(co);
                        empleadoServicios.actualizar(emp);
                    }
                }
            }
        }

        List<String[]> empleadosactualizado = new ArrayList<>();
        for (Empleado e : empleadoServicios.listarempleados()) {
            Contrato c = contratoservicios.buscarcontratoxempleado(e.getIdEmpleado());
            String[] em = new String[8];
            em[0] = e.getEmpCodigo();
            em[1] = e.getEmpNombre() + " " + e.getEmpApePaterno() + " " + e.getEmpApeMaterno();
            if (c == null) {
                em[2] = "-";
                em[3] = "-";
                em[4] = "-";
            } else {
                em[2] = c.getContarea().getANombre();
                em[3] = c.getContmodContrato().getModNombre();
                em[4] = c.getContjornada().getJorTipo();
            }

            em[5] = "" + e.getEmpSalario();
            em[6] = "" + e.getIdEmpleado();
            em[7] = "" + e.getEmpEstado();
            empleadosactualizado.add(em);
        }
        modelo.addAttribute("empleados", empleadosactualizado);
        modelo.addAttribute("css", "/assets/css/empleado/listar.css");
        modelo.addAttribute("js", "/assets/js/empleado.js");
        modelo.addAttribute("view", "Empleados/ListaEmpleados");
        return "index";

    }

    @GetMapping("/DetalleEmpleado")
    public String DetalleEmpleado(@RequestParam("id") String id, Model modelo) {
        int ide = Integer.parseInt(id);
        Empleado em = empleadoServicios.buscarid(ide);
        Contrato cont = contratoservicios.buscarcontratoxempleado(em.getIdEmpleado());
        String[] de = new String[18];

        de[0] = em.getEmpNombre();
        de[1] = em.getEmpApePaterno();
        de[2] = em.getEmpApeMaterno();
        de[3] = em.getEmpDni();
        de[4] = String.valueOf(em.getEmpFecNaci());

        LocalDate fechaActual = LocalDate.now();
        int edad = Period.between(em.getEmpFecNaci().toLocalDate(), fechaActual).getYears();
        de[5] = String.valueOf(edad);

        Period diff = Period.between(em.getEmpFecIngreso().toLocalDate(), fechaActual);
        if (diff.getYears() >= 1) {
            de[6] = diff.getYears() + " año" + (diff.getYears() > 1 ? "s" : "");
        } else if (diff.getMonths() >= 1) {
            de[6] = diff.getMonths() + " mes" + (diff.getMonths() > 1 ? "es" : "");
        } else {
            int dias = Math.max(diff.getDays(), 0);
            de[6] = dias + " día" + (dias != 1 ? "s" : "");
        }

        de[7] = em.getEmpGenero();
        de[8] = em.getEmpestadocivil().getEstTipo();

        if (cont == null) {
            de[9] = "-";
            de[10] = "-";
            de[11] = "-";
            de[12] = "-";
            de[13] = "-";
            de[14] = "Inactivo";
        } else {
            de[9] = cont.getContarea().getANombre();
            de[10] = cont.getContmodContrato().getModNombre();
            de[11] = cont.getContjornada().getJorTipo();
            de[12] = String.valueOf(cont.getContFecIni());
            de[13] = String.valueOf(cont.getContFechfin());
            de[14] = "Activo";
        }

        de[15] = em.getEmpCci();
        de[16] = em.getEmpbanco().getBanNombre();

        if (em.getEmpFoto() != null) {
            de[17] = "/Empleado/imagenEmpleado/" + em.getIdEmpleado();
        } else {
            de[17] = "https://via.placeholder.com/150";
        }

        modelo.addAttribute("de", de);
        modelo.addAttribute("css", "/assets/css/empleado/modelo.css");
        modelo.addAttribute("view", "Empleados/ModalDatosEmpleado");
        return "index";
    }

    @GetMapping("/NuevoEmpleado")
    public String Nuevoempleado(Model modelo) {
        InserEmpleado ie = new InserEmpleado();
        ie.setEmpFecIngreso(Date.valueOf(LocalDate.now()));
        modelo.addAttribute("empleado", ie);
        modelo.addAttribute("bancos", bancoservicios.listabanco());
        modelo.addAttribute("estcivil", estadocivilservicios.listarestadocivil());
        modelo.addAttribute("area", areaservicios.listarAreas());
        modelo.addAttribute("jornada", jornadalaboralServicios.listarjornadas());
        modelo.addAttribute("modalidad", modalidadContratoServicios.listarModalidadContrato());
        return "Empleados/NuevoEmpleado";
    }

    @PostMapping("/insertarempleado")
    public String nuevoEmpleado(@ModelAttribute("empleado") InserEmpleado datos,
            @RequestParam("empFoto") MultipartFile file) {

        // Para Empleado
        try {
            Empleado emp = new Empleado();

            emp.setEmpNombre(datos.getEmpNombre());
            emp.setEmpApePaterno(datos.getEmpApePaterno());
            emp.setEmpApeMaterno(datos.getEmpApeMaterno());
            emp.setEmpDni(datos.getEmpDni());

            emp.setEmpCodigo(datos.getEmpNombre().charAt(0) + datos.getEmpApeMaterno().charAt(0)
                    + datos.getEmpApePaterno().charAt(0) + "");

            emp.setEmpGenero(datos.getEmpGenero());
            emp.setEmpSalario(areaservicios.buscarAreaPorID(datos.getIdarea()).getASalario());
            emp.setEmpFecNaci(datos.getEmpFecNaci());
            emp.setEmpFecIngreso(datos.getEmpFecIngreso());
            emp.setEmpEstado((byte) 1);
            emp.setEmpestadocivil(estadocivilservicios.buscarid(datos.getIdestacivil()));
            emp.setEmpCci(datos.getEmpCci());
            emp.setEmpbanco(bancoservicios.buscarid(datos.getIdbanco()));

            if (!file.isEmpty()) {
                // Validar tipo de archivo (solo JPG y PNG)
                String contentType = file.getContentType();
                assert contentType != null;
                if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
                    throw new IllegalArgumentException("Formato de imagen no válido. Solo se permiten JPG y PNG.");
                }

                // Validar tamaño (máximo 500KB)
                if (file.getSize() > 500 * 1024) {
                    throw new IllegalArgumentException("El tamaño máximo permitido es 500KB.");
                }

                emp.setEmpFoto(file.getBytes()); // Convertir imagen a bytes
            }

            empleadoServicios.insertar(emp);

            // Para Contrato
            Contrato co = new Contrato();
            Empleado empl = empleadoServicios.obtenerultimoid();
            co.setContempleado(empleadoServicios.buscarid(empl.getIdEmpleado()));
            co.setContarea(areaservicios.buscarAreaPorID(datos.getIdarea()));
            co.setContmodContrato(modalidadContratoServicios.buscaridmoda(datos.getIdmodalidadcontrato()));
            co.setContjornada(jornadalaboralServicios.buscarid(datos.getIdjornada()));
            co.setContFecIni(datos.getEmpFecIngreso());
            co.setContFechfin(null);
            co.setContSalario(areaservicios.buscarAreaPorID(datos.getIdarea()).getASalario());
            co.setContEstadoContrato((byte) 1);

            contratoservicios.insertarcontrato(co);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException ex) {
            System.out.println("Error en la carga de imagen: " + ex.getMessage());
        }

        return "redirect:/Empleado/ListaEmpleados";
    }

    @GetMapping("/imagenEmpleado/{id}")
    public ResponseEntity<byte[]> obtenerImagenEmpleado(@PathVariable int id) {
        Empleado empleado = empleadoServicios.buscarid(id);

        if (empleado.getEmpFoto() != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(empleado.getEmpFoto());
        } else {
            return ResponseEntity.notFound().build(); // Si no tiene imagen, devuelve un error 404
        }
    }

    @GetMapping("/EditarEmpleado/{id}")
    public String prepararEditarEmpleado(@PathVariable("id") String id, Model modelo) {
        int ide = Integer.parseInt(id);
        modelo.addAttribute("empleado", empleadoServicios.buscarid(ide));
        modelo.addAttribute("bancos", bancoservicios.listabanco());
        modelo.addAttribute("estcivil", estadocivilservicios.listarestadocivil());
        return "Empleados/EditarEmpleados";
    }

    @PostMapping("/EditadoEmpleado/{id}")
    public String EditarEmpleado(@PathVariable("id") String id,
            @ModelAttribute Empleado empleado,
            @RequestParam(value = "empFoto", required = false) MultipartFile file) {

        Empleado empExistente = empleadoServicios.buscarid(Integer.parseInt(id));

        if (!historialsalarioservicios.buscarsalarioigual(empExistente.getIdEmpleado(), empleado.getEmpSalario())) {
            HistorialSalario histsalario = new HistorialSalario();
            histsalario.setHisEmpleado(empExistente);
            histsalario.setHisSalario(empleado.getEmpSalario());
            histsalario.setHisFechaCambio(Date.valueOf(LocalDate.now()));
            historialsalarioservicios.insertar(histsalario);
        }

        empExistente.setEmpNombre(empleado.getEmpNombre());
        empExistente.setEmpApePaterno(empleado.getEmpApePaterno());
        empExistente.setEmpApeMaterno(empleado.getEmpApeMaterno());
        empExistente.setEmpDni(empleado.getEmpDni());
        empExistente.setEmpGenero(empleado.getEmpGenero());
        empExistente.setEmpSalario(empleado.getEmpSalario());
        empExistente.setEmpFecNaci(empleado.getEmpFecNaci());
        empExistente.setEmpFecIngreso(empleado.getEmpFecIngreso());
        empExistente.setEmpestadocivil(estadocivilservicios.buscarid(empleado.getEmpestadocivil().getIdEstadoCivil()));
        empExistente.setEmpCci(empleado.getEmpCci());
        empExistente.setEmpbanco(bancoservicios.buscarid(empleado.getEmpbanco().getIdBanco()));

        empleadoServicios.actualizar(empExistente);

        return "redirect:/Empleado/ListaEmpleados";
    }

}
