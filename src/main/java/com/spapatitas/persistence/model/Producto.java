package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private double precioPublico;

    @Column(nullable = false)
    private double precioProvee;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private String descripcion;

    @Transient
    private MultipartFile imagenFile;

    @Column(nullable = false)
    private String imagen;

    @Column(nullable = false)
    private boolean estado;

    @ManyToOne (targetEntity = Categoria.class)
    private Categoria categoria;

    @OneToMany (targetEntity = DetalleVenta.class, mappedBy = "producto")
    private List<DetalleVenta> detallesVenta;

    @OneToMany (targetEntity = PromocionProducto.class, mappedBy = "producto")
    private List<PromocionProducto> promocionesProducto;

    public String getPrecioFormateado() {
        NumberFormat formatoPesosColombianos = NumberFormat.getNumberInstance(new Locale("es", "CO"));
        formatoPesosColombianos.setMaximumFractionDigits(0); // No mostrar decimales
        formatoPesosColombianos.setMinimumFractionDigits(0); // No mostrar decimales
        return "$" + formatoPesosColombianos.format(this.precioPublico);
    }

    @Override
    public String toString() {
        return "Producto{" +
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
