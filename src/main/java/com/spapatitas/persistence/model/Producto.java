package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precioPublico;

    @Column(nullable = false)
    private double precioProvee;

    @Column(nullable = false)
    private int stock;

    @ManyToOne (targetEntity = Categoria.class)
    private Categoria categoria;
}
