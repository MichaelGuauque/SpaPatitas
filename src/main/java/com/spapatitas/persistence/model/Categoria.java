package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private double porcentajeIva;

    @OneToMany (targetEntity = Producto.class, mappedBy = "categoria")
    private List<Producto> productos;
}
