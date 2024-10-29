package com.spapatitas.persistence.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Pqrs {
    private Long idPqrs;
    private Cliente cliente;
    private String descripcion;
}
