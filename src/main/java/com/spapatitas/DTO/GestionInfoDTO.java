package com.spapatitas.DTO;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GestionInfoDTO {

    private String informacion;
    private String mision;
    private String vision;

    private String infoAdicional;
    private String contacto;

    private String agendamientoCitas;
    private String compraProductos;
    private String metodosPago;
    private String cuentaPerfil;
    private String contactoAdicional;

    private String definiciones;
    private String usoSitioWeb;
    private String cuentasRegistro;
    private String codicionesVenta;
    private String condicionesAgendamiento;
    private String limitacionesResponsabilidad;
    private String propiedadIntelectual;
    private String politicaPrivacidad;
    private String modificacionTerminos;
    private String aceptacionTerminos;

    @Override
    public String toString() {
        return "GestionInfoDTO{" +
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
