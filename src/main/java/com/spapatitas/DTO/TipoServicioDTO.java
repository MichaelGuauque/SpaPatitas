package com.spapatitas.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoServicioDTO {
    private String nombreServicio;
    private String descripcion;
    private double precioPublico;
    private double costoInterno;
    private boolean estado;


    @Override
    public String toString() {
        return "TipoServicioDTO{" +
                ", nombreServicio='" + nombreServicio + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioPublico='" + precioPublico + '\'' +
                ", costoInterno='" + costoInterno + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

}
