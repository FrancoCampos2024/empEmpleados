package com.example.demo.Controlador;

import com.example.demo.Entidades.Beneficio;
import com.example.demo.Servicios.*;

import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Beneficio")
public class BeneficioControlador {

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

    // Beneficio
    @GetMapping("/ListarBeneficios")
    public String listarbeneficios(Model model) {
        model.addAttribute("beneficios", beneficioServicios.listarbeneficios());
        model.addAttribute("css", "/assets/css/beneficio/listar.css");
        model.addAttribute("js", "/assets/js/beneficio.js");
        model.addAttribute("view", "Beneficio/ListaBeneficios");
        return "index";
    }

    @GetMapping("/CrearBeneficio")
    public String prepararcrearbeneficio(Model modelo) {
        modelo.addAttribute("beneficio", new Beneficio());
        modelo.addAttribute("meses", Month.values());
        return "Beneficio/CrearBeneficio";
    }

    @PostMapping("/GuardarBeneficio")
    public String guardarbeneficio(@ModelAttribute("beneficio") Beneficio beneficio) {
        beneficioServicios.insertarbeneficio(beneficio);
        return "redirect:/Beneficio/ListarBeneficios";
    }

    @GetMapping("/EditarBeneficio/{id}")
    public String preparareditar(@PathVariable("id") int id, Model modelo) {
        modelo.addAttribute("beneficio", beneficioServicios.buscarbeneficio(id));
        modelo.addAttribute("meses", Month.values());
        return "Beneficio/EditarBeneficio";
    }

    @PostMapping("/EditadoBeneficio/{id}")
    public String guardarbeneficio(@PathVariable("id") int id, @ModelAttribute("beneficio") Beneficio beneficio) {

        Beneficio beneficioexistente = beneficioServicios.buscarbeneficio(id);

        beneficioexistente.setBenDescripcion(beneficio.getBenDescripcion());
        beneficioexistente.setBenMonto(beneficio.getBenMonto());
        beneficioexistente.setMesBeneficio(beneficio.getMesBeneficio());

        beneficioServicios.actualizarbeneficio(beneficioexistente);
        return "redirect:/Beneficio/ListarBeneficios";
    }

}
