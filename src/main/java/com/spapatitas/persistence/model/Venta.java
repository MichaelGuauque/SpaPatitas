package com.spapatitas.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    @OneToOne(targetEntity = Cliente.class, cascade = CascadeType.PERSIST)
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDateTime fechaVenta;
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;
    @Column(nullable = false)
    private double valorIva;
    @Column(nullable = false)
    private double valorSinIva;

    @OneToMany(targetEntity = DetalleVenta.class,fetch = FetchType.EAGER, mappedBy = "venta")
    private List<DetalleVenta> detallesVenta;

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
