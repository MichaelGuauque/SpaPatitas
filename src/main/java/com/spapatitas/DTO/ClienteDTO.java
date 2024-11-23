package com.spapatitas.DTO;

import com.spapatitas.persistence.model.Genero;
import com.spapatitas.persistence.model.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClienteDTO {
    private int cedula;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Genero genero;
    private LocalDate fechaNacimiento;
    private String direccion;
    private long telefono;
    private UserEntity usuario;
}
