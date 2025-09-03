package com.melina.springboot.springmvc.app.controllers;

import com.melina.springboot.springmvc.app.entitiesmodels.User;
import com.melina.springboot.springmvc.app.repositoriesDAO.UserRepository;
import com.melina.springboot.springmvc.app.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@SessionAttributes({"user"})//tengo que poner que objeto quiero persistir entre las sesiones, se va a persistir hasta el post

public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping({"/view", "/another"})// tambien se conoce como handler a los metodos creados en el controlador
    public String vista(Model model){ // es un metodo del controlador

        model.addAttribute("title", "Spring Boot");
        model.addAttribute("message", "Aplicacion con Spring Boot");
        model.addAttribute("user", new User("Melina", "Magali"));

        return "view";
    }

    //implementar el listado de usuarios
    @GetMapping
    public String list(Model model){
        model.addAttribute("title", "Listado de usuarios");
        model.addAttribute("users",service.findAll());
        return "list";
    }

    @GetMapping("/form")// uso get para mostrar el formulario
    public String form(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("title", "Crear usuario");
        return "form";
    }

    @GetMapping("/form/{id}")//si en el path pongo id en @PathVariable pongo id
    public String form(@PathVariable Long id, Model model, RedirectAttributes redirect){//@PathVariable nos inyecta el parametro de la ruta
        Optional<User> optionalUser = service.findById(id);
        if(optionalUser.isPresent()){
            model.addAttribute("user", optionalUser.get());
            model.addAttribute("title", "Editar usuario");
            return "form";
        } else {
            redirect.addFlashAttribute("error", "Usuario con " +
                    id +
                    " no fue encontrado en la base de datos");
            return "redirect:/users"; // se redirege a la lista de usuarios si no se encontro el usuario por el id
        }

    }

    @PostMapping
    public String form(User user, Model model, RedirectAttributes redirect, SessionStatus status) {
        String message = (user.getId() != null && user.getId() > 0)? "Usuario " +
                user.getName()  +
                "se ha actualizado exitosamente" : "Usuario " +
                user.getName() +
                "se ha creado exitosamente";

        service.save(user);
        //despues de guardar de persistir  borramos el objeto user de la session, limpiamos la session
        status.setComplete();// se completa el proceso del request que dura un solo request desde que se llena los datos en el formulario y despues se envian
        redirect.addFlashAttribute("success", message);
        return "redirect:/users"; //se redirige a la lista de usuarios para ver los cambios
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        Optional<User> optionalUser = service.findById(id);
        if(optionalUser.isPresent()){
            redirect.addFlashAttribute("success", "Usuario " +
                   optionalUser.get().getName() +
                    "ha sido eliminado con exito");

            service.remove(id);
            return "redirect:/users";
        }
        redirect.addFlashAttribute("error", "Error el usuario con el id " +
                id +
                "no existe en el sistema");

        return "redirect:/users";
    }
}
