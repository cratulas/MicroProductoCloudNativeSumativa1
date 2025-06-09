package com.example.microproducto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PRODUCTOS")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "STOCK")
    private Integer stock;
}
