package com.spapatitas.DTO;

import com.spapatitas.persistence.model.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class ProductoDTO {
    private Long codigo;
    private String nombre;
    private double precioPublico;
    private double precioProvee;
    private int stock;
    private String descripcion;
    private String imagen;
    private boolean estado;
    private Categoria categoria;

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precioPublico=" + precioPublico +
                ", precioProvee=" + precioProvee +
                ", stock=" + stock +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", estado=" + estado +
                ", categoria=" + categoria +
                '}';
    }
}
