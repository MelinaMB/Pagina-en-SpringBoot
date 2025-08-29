package com.melina.springboot.springmvc.app.repositoriesDAO;


import com.melina.springboot.springmvc.app.entitiesmodels.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Long>{

}
