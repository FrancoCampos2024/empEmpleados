package com.example.demo.Controlador;

import com.example.demo.Entidades.Boletapago;
import com.example.demo.Entidades.Contrato;
import com.example.demo.Servicios.ContratoServicios;
import com.example.demo.Servicios.Serviciosimpl.BoleService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.Month;
import java.util.List;

@Controller
public class BoletaController {

    @Autowired
    @Qualifier("boleService")
    private BoleService boleService;

    @Autowired
    @Qualifier("contratoservicios")
    private ContratoServicios contratoServicios;

    // Página para ver los empleados pendientes de boleta
    @GetMapping("/boletas/pendientes")
    public String verBoletasPendientes(Model model) {
        List<Contrato> pendientes = boleService.verificarBoletasPendientes();
        model.addAttribute("pendientes", pendientes);
        model.addAttribute("mes", Month.values().toString());
        model.addAttribute("css", "/assets/css/BoletaPago/listar.css");
        model.addAttribute("view", "Boleta/boletasPendientes");
        return "index";
    }

    // Generar boleta para un empleado específico
    @PostMapping("/boletas/generar")
    public String generarBoleta(@RequestParam("idContrato") int idContrato, Model model) {
        Contrato contrato = contratoServicios.buscarcontratoporidcontrato(idContrato);
        String bancoContrato = contratoServicios.buscarBancoContrato(idContrato);
        Boletapago boleta = boleService.generarBoleta(contrato);
        model.addAttribute("boleta", boleta);
        model.addAttribute("bancoContrato", bancoContrato);
        return "Boleta/boletaGenerada";
    }

    // Generar PDF de la boleta
    @GetMapping("/boletas/pdf")
    public void generarBoletaPDF(@RequestParam("idBoleta") int idBoleta, HttpServletResponse response)
            throws IOException {
        boleService.generarBoletaPDF(idBoleta, response);
    }
}
