package com.spapatitas.persistence.model;

import jakarta.persistence.*;

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
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDateTime fechaVenta;
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;
    @Column(nullable = false)
    private double valorIva;
    @Column(nullable = false)
    private double valorSinIva;
    @Column(nullable = false)
    private double total;

    @OneToMany(targetEntity = DetalleVenta.class,fetch = FetchType.EAGER, mappedBy = "venta")
    private List<DetalleVenta> detallesVenta = new ArrayList<>();

    public Venta(LocalDateTime dataTime) {
        this.fechaVenta = LocalDateTime.now();
        this.detallesVenta = new ArrayList<>();
        this.valorIva = 0;
        this.valorSinIva = 0;
        this.total = 0;
    }

    public void addDetalleVenta(DetalleVenta detalleVenta) {
        this.detallesVenta.add(detalleVenta);
        this.valorIva += detalleVenta.getValorIva();
        this.valorSinIva += detalleVenta.getValorSinIva();
        this.total += this.valorIva + this.valorSinIva;
    }

    public void removeDetalleVenta(DetalleVenta detalleVenta) {
        this.detallesVenta.remove(detalleVenta);
        this.valorIva -= detalleVenta.getValorIva();
        this.valorSinIva -= detalleVenta.getValorSinIva();
        this.total -= this.valorIva + this.valorSinIva;
    }
}
