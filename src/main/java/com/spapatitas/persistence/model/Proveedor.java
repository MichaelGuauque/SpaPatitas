package com.spapatitas.persistence.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Proveedor {
    private Long nit;
    private String nombre;
    private String direccion;
    private int telefono;
    private String correo;
}
