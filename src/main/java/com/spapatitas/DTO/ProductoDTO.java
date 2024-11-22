package com.spapatitas.DTO;

import com.spapatitas.persistence.model.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ProductoDTO {
    private String nombre;
    private double precioPublico;
    private double precioProvee;
    private int stock;
    private String descripcion;
    private MultipartFile imagenFile;
    private String imagen;
    private boolean estado;
    private Categoria categoria;

    @Override
    public String toString() {
        return "ProductoDTO{" +
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
