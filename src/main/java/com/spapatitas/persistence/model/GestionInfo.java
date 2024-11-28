package com.spapatitas.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class GestionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Seccion "Quienes Somos" ---------------------------------
    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String informacion;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String mision;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String vision;

    //Seccion "PQRS" ------------------------------------------
    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String infoAdicional;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String contacto;

    //Seccion "Centro de Ayuda" -------------------------------
    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String agendamientoCitas;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String compraProductos;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String metodosPago;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String cuentaPerfil;

    //aqui Otra vez contacto

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String contactoAdicional;

    //Seccion "Terminos y Condiciones" ------------------------
    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String definiciones;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String usoSitioWeb;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String cuentasRegistro;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String codicionesVenta;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String condicionesAgendamiento;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String limitacionesResponsabilidad;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String propiedadIntelectual;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String politicaPrivacidad;

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String modificacionTerminos;

    //Contacto nuevamente

    @Column(nullable = true, columnDefinition = "VARCHAR(500)")
    private String aceptacionTerminos;


    @Override
    public String toString() {
        return "GestionInfo{" +
                "id=" + id +
                ", informacion='" + informacion + '\'' +
                ", mision='" + mision + '\'' +
                ", vision='" + vision + '\'' +
                ", infoAdicional='" + infoAdicional + '\'' +
                ", contacto='" + contacto + '\'' +
                ", agendamientoCitas='" + agendamientoCitas + '\'' +
                ", compraProductos='" + compraProductos + '\'' +
                ", metodosPago='" + metodosPago + '\'' +
                ", cuentaPerfil='" + cuentaPerfil + '\'' +
                ", contactoAdicional='" + contactoAdicional + '\'' +
                ", definiciones='" + definiciones + '\'' +
                ", usoSitioWeb='" + usoSitioWeb + '\'' +
                ", cuentasRegistro='" + cuentasRegistro + '\'' +
                ", codicionesVenta='" + codicionesVenta + '\'' +
                ", condicionesAgendamiento='" + condicionesAgendamiento + '\'' +
                ", limitacionesResponsabilidad='" + limitacionesResponsabilidad + '\'' +
                ", propiedadIntelectual='" + propiedadIntelectual + '\'' +
                ", politicaPrivacidad='" + politicaPrivacidad + '\'' +
                ", modificacionTerminos='" + modificacionTerminos + '\'' +
                ", aceptacionTerminos='" + aceptacionTerminos + '\'' +
                '}';
    }


}
