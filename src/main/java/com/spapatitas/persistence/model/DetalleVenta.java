package com.spapatitas.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    private Producto producto;
    private int cantidad;
    private double valorIva;
    private double valorSinIva;

    //No modificar
    @ManyToOne(targetEntity = Venta.class, fetch = FetchType.EAGER)
    private Venta venta;

    public DetalleVenta(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.valorSinIva = cantidad * producto.getPrecioPublico();
        this.valorIva = valorSinIva * producto.getCategoria().getPorcentajeIva();
    }
}
