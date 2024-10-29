package com.spapatitas.persistence.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Producto {
    private Long idProducto;
    private String nombre;
    private double precioPublico;
    private double precioProvee;
    private int stock;
    private Categoria categoria;
}
