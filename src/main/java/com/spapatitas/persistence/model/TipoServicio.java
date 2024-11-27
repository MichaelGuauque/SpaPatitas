package com.spapatitas.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class TipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String nombreServicio;

    @Column(nullable = false, columnDefinition = "VARCHAR(300)")
    private String descripcion;

    @Column(nullable = false)
    private double precioPublico;

    @Column(nullable = false)
    private double costoInterno;

    @Column(nullable = false)
    private boolean estado;


    @Override
    public String toString() {
        return "TipoServicio{" +
                "id='" + id +
                ", nombreServicio='" + nombreServicio + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioPublico='" + precioPublico + '\'' +
                ", costoInterno='" + costoInterno + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

}
