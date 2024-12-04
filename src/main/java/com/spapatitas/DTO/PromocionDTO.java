package com.spapatitas.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PromocionDTO {
    private int porcentajeDescuento;
    private long codigo;
    private boolean estado;

    @Override
    public String toString() {
        return "PromocionDTO{" +
                "porcentajeIva=" + porcentajeDescuento +
                ", codigo=" + codigo +
                ", estado=" + estado +
                '}';
    }
}
