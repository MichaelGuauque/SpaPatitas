
package com.spapatitas.persistence.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Servicio {
    private TipoServicio tipoServicio;
    private Cita cita;    
}
