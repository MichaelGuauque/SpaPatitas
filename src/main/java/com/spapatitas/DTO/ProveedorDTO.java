package com.spapatitas.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProveedorDTO {

    private Long nit;
    private String nombre;
    private int telefono;
    private String direccion;
    private String correo;
    private boolean estado;

    @Override
    public String toString(){
        return "ProveedorDTO{" +
                ", nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", estado=" + estado +
                '}';
    }
}
