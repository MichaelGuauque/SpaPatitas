package com.spapatitas.persistence.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Categoria {
    private Long codigo;
    private String descripcion;
    private double porcentajeIva;
}
