package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleVenta;

    @ManyToOne (targetEntity = Producto.class)
    private Producto producto;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double valorIva;

    @Column(nullable = false)
    private double valorSinIva;

    @ManyToOne(targetEntity = Venta.class, fetch = FetchType.EAGER)
    private Venta venta;

    public DetalleVenta(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.valorSinIva = cantidad * producto.getPrecioPublico();
        this.valorIva = valorSinIva * producto.getCategoria().getPorcentajeIva();
    }
}
