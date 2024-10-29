package com.spapatitas.persistence.model;

import java.util.Date;
import lombok.*;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleProvee {
    private Date fecha;
    private Producto producto;
    private Proveedor nitP;
    private int cantidad;
}
