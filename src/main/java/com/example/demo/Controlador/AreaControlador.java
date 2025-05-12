package com.example.demo.Controlador;

import com.example.demo.Entidades.Area;
import com.example.demo.Servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Area")
public class AreaControlador {
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

    @GetMapping("/ListarArea")
    public String listarareas(Model model) {
        model.addAttribute("areas", areaservicios.listarAreas());
        model.addAttribute("css", "/assets/css/area/listar.css");
        model.addAttribute("view", "Area/ListaArea");
        return "index";
    }

    @GetMapping("/NuevaArea")
    public String prepararnuevaarea(Model modelo) {
        Area area = new Area();
        modelo.addAttribute("area", area);
        return "Area/CrearArea";
    }

    @PostMapping("/GuardarArea")
    public String guardarArea(@ModelAttribute("area") Area area) {
        areaservicios.GuardarArea(area);
        return "redirect:/Area/ListarArea";
    }

    @GetMapping("/EditarArea/{id}")
    public String prepararedicionarea(@PathVariable("id") int id, Model modelo) {
        Area area = areaservicios.buscarAreaPorID(id);
        modelo.addAttribute("area", area);
        return "Area/EditarArea";
    }

    @PostMapping("/EditadoArea/{id}")
    public String EditarArea(@PathVariable("id") int id, @ModelAttribute("area") Area area) {
        areaservicios.GuardarArea(area);

        return "redirect:/Area/ListarArea";
    }

}
