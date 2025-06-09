package com.example.microproducto.service;

import com.example.microproducto.model.Producto;
import com.example.microproducto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> getAll() {
        return repository.findAll();
    }

    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    public Producto getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
