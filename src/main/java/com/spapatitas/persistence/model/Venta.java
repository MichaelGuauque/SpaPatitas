
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
public class Venta {
    private LocalDateTime fechaVenta;
    private Cliente cliente;
    private MetodoPago metodoPago;
    private Long idVenta;
}
