package com.spapatitas.persistence.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Venta {

    private Long idVenta;
    private Cliente cliente;
    private LocalDateTime fechaVenta;
    private MetodoPago metodoPago;
    private List<DetalleVenta> detallesVenta;
    private double valorIva;
    private double valorSinIva;

    public Venta(LocalDateTime dataTime) {
        this.fechaVenta = LocalDateTime.now();
        this.detallesVenta = new ArrayList<>();
        this.valorIva = 0;
        this.valorSinIva = 0;
    }

    public void addDetalleVenta(DetalleVenta detalleVenta) {
        this.detallesVenta.add(detalleVenta);
        this.valorIva += detalleVenta.getValorIva();
        this.valorSinIva += detalleVenta.getValorSinIva();
    }

    public void removeDetalleVenta(DetalleVenta detalleVenta) {
        this.detallesVenta.remove(detalleVenta);
        this.valorIva -= detalleVenta.getValorIva();
        this.valorSinIva -= detalleVenta.getValorSinIva();
    }
}
