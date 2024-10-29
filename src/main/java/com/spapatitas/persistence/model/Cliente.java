package com.spapatitas.persistence.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Cliente {
    private Long idCliente;
    private int cedula;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Genero genero;
    private Date fechaNacimiento;
    private String direccion;
    private int telefono;
    private UserEntity usuario;
}
