package com.spapatitas.persistence.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class DetalleProvee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleProvee;

    @Column (columnDefinition = "DATE", nullable = false)
    private Date fecha;

    @ManyToOne (targetEntity = Producto.class)
    private Producto producto;

    @ManyToOne (targetEntity = Proveedor.class)
    private Proveedor nitP;

    @Column(nullable = false)
    private int cantidad;
}
