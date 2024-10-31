package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Cliente dueno;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String nombre;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String raza;

    @Column(columnDefinition = "DATE")
    private LocalDate fechaNacimiento;

    @Column(nullable = false, columnDefinition = "VARCHAR(300)")
    private String observaciones;

    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente;
}
