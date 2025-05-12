package com.example.demo.Controlador;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("usuario")
public class LoginControlador {

    @GetMapping("/")
    public String mostrarLogin(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos.");
        }
        return "login";
    }

    @GetMapping("/index")
    public String mostrarIndex(HttpSession session, Model model) {
        model.addAttribute("css", "/assets/css/cssIndex.css");
        model.addAttribute("view", "layaout/content");
        return "index";
    }
}
