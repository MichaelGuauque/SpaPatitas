package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false, unique = true, length = 10)
    private int cedula;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String primerNombre;

    @Column()
    private String segundoNombre;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String primerApellido;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String segundoApellido;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(columnDefinition = "DATE")
    private LocalDate fechaNacimiento;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String direccion;

    @Column(nullable = false, unique = true, length = 10)
    private int telefono;

    @OneToMany(targetEntity = Mascota.class, fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Mascota> mascotas;

    @OneToOne
    private UserEntity usuario;

    //No modificar
    @OneToMany(targetEntity = Cita.class, fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Cita> citas;
}
