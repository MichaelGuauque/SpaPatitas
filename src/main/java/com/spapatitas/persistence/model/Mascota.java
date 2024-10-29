package com.spapatitas.persistence.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Mascota {
    private Long id;
    private Cliente dueno;
    private String nombre;
    private String raza;
    private Date fechaNacimiento;
    private String observacione;
}
