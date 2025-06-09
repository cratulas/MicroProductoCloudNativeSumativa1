package com.example.microproducto.controller;

import com.example.microproducto.model.Producto;
import com.example.microproducto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> listar() {
        return service.getAll();
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return service.save(producto);
    }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Producto existente = service.getById(id);
        if (existente != null) {
            existente.setNombre(productoActualizado.getNombre());
            existente.setPrecio(productoActualizado.getPrecio());
            existente.setStock(productoActualizado.getStock());
            return service.save(existente);
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }

    @PutMapping("/{id}/descontar/{cantidad}")
    public void descontarStock(@PathVariable Long id, @PathVariable int cantidad) {
        Producto producto = service.getById(id);
        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }
        producto.setStock(producto.getStock() - cantidad);
        service.save(producto);
    }


}
