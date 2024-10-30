package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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
    
    private int cedula;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;

    @Enumerated(EnumType.STRING)
    private Genero genero;
    private Date fechaNacimiento;
    private String direccion;
    private int telefono;

//    No deberia haber una relacion con mascota?
//    para de esta forma ver todas las mascotas de un cliente

    @OneToOne
    private UserEntity usuario;

    //No modificar
    @OneToMany(targetEntity = Cita.class, fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Cita> citas;
}
