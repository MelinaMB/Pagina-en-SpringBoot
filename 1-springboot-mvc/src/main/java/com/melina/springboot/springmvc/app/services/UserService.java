package com.melina.springboot.springmvc.app.services;

import com.melina.springboot.springmvc.app.entitiesmodels.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById();
    User save(User user);// guarda un objeto y devuelve el objeto guardado
    void remove(Long id);
}
