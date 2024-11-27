package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.metamodel.mapping.internal.ImmutableAttributeMappingsMap;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nit;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private int telefono;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private boolean estado;

    @OneToMany(targetEntity = DetalleProvee.class, mappedBy = "proveedor")
    private List<DetalleProvee> detallesProvee;

    @Override
    public String toString() {
        return "Proveedor{" +
                "nit=" + nit +
                ", nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", estado=" + estado +
                '}';
    }
}

