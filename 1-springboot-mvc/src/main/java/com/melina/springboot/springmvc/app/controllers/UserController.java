package com.melina.springboot.springmvc.app.controllers;

import com.melina.springboot.springmvc.app.entitiesmodels.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")

public class UserController {

    @GetMapping({"/view", "/", "/another"})// tambien se conoce como handler a los metodos creados en el controlador
    public String vista(Model model){ // es un metodo del controlador

        model.addAttribute("title", "Spring Boot");
        model.addAttribute("message", "Aplicacion con Spring Boot");
        model.addAttribute("user", new User("Melina", "Magali"));

        return "view";
    }
}
