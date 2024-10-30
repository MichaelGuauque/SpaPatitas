package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.metamodel.mapping.internal.ImmutableAttributeMappingsMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nit;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private int telefono;

    @Column(nullable = false)
    private String correo;
}

