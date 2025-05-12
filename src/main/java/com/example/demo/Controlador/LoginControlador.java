package com.example.demo.Controlador;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("usuario")
public class LoginControlador {

    @ModelAttribute("usuario")
    public String getUsuario() {
        return null;
    }

    @GetMapping("/")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
            @RequestParam String password,
            HttpSession session) {
        if ("admin".equals(username) && "1234".equals(password)) {
            session.setAttribute("usuario", username);
            return "redirect:/index";
        }
        return "login";
    }

    @GetMapping("/index")
    public String mostrarIndex(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/";
        }
        model.addAttribute("css", "/assets/css/cssIndex.css");
        model.addAttribute("view", "layaout/content");
        return "index";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
