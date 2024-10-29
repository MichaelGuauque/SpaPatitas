
package com.spapatitas.persistence.model;

import java.time.LocalDateTime;
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
public class Cita {
    private Long idCita;
    private LocalDateTime fechaHora;
}
