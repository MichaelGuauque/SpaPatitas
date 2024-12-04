package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class PromocionProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Producto.class)
    private Producto producto;
    @Column(nullable = false)
    private int procentajeDescuento;
    @Column(nullable = false)
    private double totalPrecio;
    @Column(nullable = false)
    private boolean estado;

    public PromocionProducto(Producto producto, int procentajeDescuento, boolean estado){
        this.producto = producto;
        this.procentajeDescuento = procentajeDescuento;
        this.estado = estado;
        this.totalPrecio = this.producto.getPrecioPublico() - (this.producto.getPrecioPublico() * ((double) this.procentajeDescuento/100));
    }
}
