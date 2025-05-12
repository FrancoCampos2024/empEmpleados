package com.example.demo.Controlador;

import com.example.demo.Entidades.Boletapago;
import com.example.demo.Servicios.BoletaPagoServicios;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReporteControlador {

    private final BoletaPagoServicios boletaServicio;

    public ReporteControlador(BoletaPagoServicios boletaServicio) {
        this.boletaServicio = boletaServicio;
    }

    @GetMapping("/ListaBoletas")
    public String mostrarListaBoletas(Model model) {
        List<Boletapago> boletas = boletaServicio.obtenerTodasLasBoletas();

        model.addAttribute("boletas", boletas);
        model.addAttribute("css", "/assets/css/BoletaPago/listar.css");
        model.addAttribute("js", "/assets/js/boleta.js");
        model.addAttribute("view", "Boleta/ListaBoletas");
        return "index";
    }
}