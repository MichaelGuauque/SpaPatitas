
package com.spapatitas.persistence.model;

import lombok.*;

/**
 *
 * @author Paul S. Figueroa
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Servicio {
    private TipoServicio tipoServicio;
    private Cita cita;
    private int valorTotal;
}
