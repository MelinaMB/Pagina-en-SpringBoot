package com.melina.java.springboot.backend.services;

import com.melina.java.springboot.backend.entities.Product;
import com.melina.java.springboot.backend.repositories.ProductRepository;
import jakarta.transaction.Transactional;


import java.util.List;
import java.util.Optional;

public class ProductServicelmpl implements ProductService{
    final private ProductRepository repository;

    public ProductServicelmpl(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true )
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> deleteById(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        if(productOptional.isPresent()) {
            repository.deleteById(id);
            return productOptional;
        }
        return Optional.empty();
    }
}
