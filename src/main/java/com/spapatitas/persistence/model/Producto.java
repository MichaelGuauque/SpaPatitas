package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private double precioPublico;

    @Column(nullable = false)
    private double precioProvee;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private boolean estado;

    @ManyToOne (targetEntity = Categoria.class)
    private Categoria categoria;

    @OneToMany (targetEntity = DetalleVenta.class, mappedBy = "producto")
    private List<DetalleVenta> detallesVenta;
}
