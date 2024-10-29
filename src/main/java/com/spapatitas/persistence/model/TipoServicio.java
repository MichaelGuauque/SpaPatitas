
package com.spapatitas.persistence.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class TipoServicio {
    private Long idTipoServicio;
    private String descripcion;
    private double precio;
}
