package com.spapatitas.persistence.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVenta {
    private Venta idVenta;
    private Producto idProducto;
    private int cantidad;
    private double valorTotal;
}
