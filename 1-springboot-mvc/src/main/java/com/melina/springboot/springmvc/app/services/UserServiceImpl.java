package com.melina.springboot.springmvc.app.services;

import com.melina.springboot.springmvc.app.entitiesmodels.User;
import com.melina.springboot.springmvc.app.repositoriesDAO.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // es una espicificacion de que esto es un service de spring
public class UserServiceImpl implements UserService {


    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) this.repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public User save(User user) {
        return repository.save(user);// se pasa el objeto user y se guarda
    }

    @Transactional
    @Override
    public void remove(Long id) {
        repository.deleteById(id);

    }
}
