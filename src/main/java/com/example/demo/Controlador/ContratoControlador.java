package com.example.demo.Controlador;

import com.example.demo.Entidades.Contrato;
import com.example.demo.Entidades.Empleado;
import com.example.demo.Servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/Contrato")
public class ContratoControlador {

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

    // Visualizar
    @GetMapping("/ListarContratos")
    public String Listartotalcontratos(Model model) {
        List<Contrato> contrato = contratoservicios.listarcontratos();

        for (Contrato co : contrato) {

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

        List<Contrato> contratoactualizado = contratoservicios.listarcontratos();

        model.addAttribute("contratos", contratoactualizado);
        model.addAttribute("css", "/assets/css/contrato/listar.css");
        model.addAttribute("js", "/assets/js/empleado.js");
        model.addAttribute("view", "Contrato/Listarcontratos");
        return "index";
    }

    @GetMapping("/VerContrato/{id}")
    public String listarcontratos(@PathVariable("id") int id, Model model) {
        model.addAttribute("contrato", contratoservicios.buscarcontratoporidcontrato(id));
        model.addAttribute("css", "/assets/css/contrato/ver.css");
        model.addAttribute("view", "Contrato/VerContrato");
        return "index";
    }

    // Nuevo Contrato
    @GetMapping("/RealizarContrato")
    public String prepararcontrato(Model modelo) {
        List<Empleado> lisemp = empleadoServicios.lisEmpleadosSinContrato();

        Contrato co = new Contrato();
        modelo.addAttribute("contrato", co);
        modelo.addAttribute("empleadossc", lisemp);
        modelo.addAttribute("jornadas", jornadalaboralServicios.listarjornadas());
        modelo.addAttribute("areas", areaservicios.listarAreas());
        modelo.addAttribute("modalidadcontrato", modalidadContratoServicios.listarModalidadContrato());
        return "Contrato/NuevoDatoLaboral";
    }

    @PostMapping("/NuevoContrato")
    public String aniadircoontrato(@ModelAttribute("contrato") Contrato ncontrato) {
        // Contrato
        Contrato co = new Contrato();
        co.setContempleado(empleadoServicios.buscarid(ncontrato.getContempleado().getIdEmpleado()));
        co.setContarea(areaservicios.buscarAreaPorID(ncontrato.getContarea().getIdArea()));
        co.setContmodContrato(modalidadContratoServicios.buscaridmoda(ncontrato.getContmodContrato().getIdModalidad()));
        co.setContjornada(jornadalaboralServicios.buscarid(ncontrato.getContjornada().getIdJornada()));
        co.setContFecIni(ncontrato.getContFecIni());
        co.setContFechfin(ncontrato.getContFechfin());
        co.setContSalario(areaservicios.buscarAreaPorID(ncontrato.getContarea().getIdArea()).getASalario());
        co.setContEstadoContrato((byte) 1);
        System.out.println("ID:" + co.getContempleado().getIdEmpleado());
        // Empleado
        Empleado emp = empleadoServicios.buscarid(co.getContempleado().getIdEmpleado());
        emp.setEmpSalario(areaservicios.buscarAreaPorID(ncontrato.getContarea().getIdArea()).getASalario());
        empleadoServicios.editarestadoporidempleado(co.getContempleado().getIdEmpleado());

        // Insertados
        empleadoServicios.actualizar(emp);
        contratoservicios.insertarcontrato(co);

        return "redirect:/Contrato/ListarContratos";
    }

    // Editar Contrato
    @GetMapping("/EditarContrato/{id}")
    public String prepararcontratoeditar(@PathVariable("id") int id, Model modelo) {
        modelo.addAttribute("contrato", contratoservicios.buscarcontratoporidcontrato(id));
        modelo.addAttribute("areas", areaservicios.listarAreas());
        modelo.addAttribute("jornadas", jornadalaboralServicios.listarjornadas());
        modelo.addAttribute("modalidad", modalidadContratoServicios.listarModalidadContrato());
        return "Contrato/EditarContrato";
    }

    @PostMapping("/EditadoContrato/{id}")
    public String editarcontrato(@PathVariable("id") int id, @ModelAttribute("contrato") Contrato ncontrato) {

        Contrato co = contratoservicios.buscarcontratoporidcontrato(id);

        co.setContarea(areaservicios.buscarAreaPorID(ncontrato.getContarea().getIdArea()));
        co.setContmodContrato(modalidadContratoServicios.buscaridmoda(ncontrato.getContmodContrato().getIdModalidad()));
        co.setContjornada(jornadalaboralServicios.buscarid(ncontrato.getContjornada().getIdJornada()));
        co.setContFecIni(ncontrato.getContFecIni());
        co.setContSalario(areaservicios.buscarAreaPorID(ncontrato.getContarea().getIdArea()).getASalario());

        Empleado emp = empleadoServicios.buscarid(co.getContempleado().getIdEmpleado());

        emp.setEmpSalario(areaservicios.buscarAreaPorID(ncontrato.getContarea().getIdArea()).getASalario());

        contratoservicios.actualizarcontrato(co);
        empleadoServicios.actualizar(emp);
        return "redirect:/Contrato/ListarContratos";
    }

    // Finalizar Contrato
    @GetMapping("/FinalizarContrato/{id}")
    public String prepararfincontrato(@PathVariable("id") int id, Model modelo) {
        Contrato co = contratoservicios.buscarcontratoporidcontrato(id);
        modelo.addAttribute("contrato", co);
        return "Contrato/FinalizarContrato";
    }

    @PostMapping("/FinalizoContrato/{id}")
    public String finalizocontrato(@PathVariable("id") int idc, @ModelAttribute("contrato") Contrato ncontrato) {
        Contrato co = contratoservicios.buscarcontratoporidcontrato(idc);
        System.out.println("fechafin:" + ncontrato.getContjornada().getIdJornada());
        co.setContFechfin(ncontrato.getContFechfin());
        contratoservicios.actualizarcontrato(co);
        return "redirect:/Contrato/ListarContratos";
    }

}
