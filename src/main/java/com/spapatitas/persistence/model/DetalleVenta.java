package com.spapatitas.persistence.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVenta {
    private Producto producto;
    private int cantidad;
    private double valorIva;
    private double valorSinIva;
    
    public DetalleVenta(int cantidad, Producto producto){
        this.cantidad = cantidad;
        this.producto = producto;
        this.valorSinIva = cantidad * producto.getPrecioPublico() ;
        this.valorIva = valorSinIva * producto.getCategoria().getPorcentajeIva() ;
    }
}