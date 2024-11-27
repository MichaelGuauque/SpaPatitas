package com.spapatitas.DTO;

import com.spapatitas.persistence.model.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MascotaDTO {
    private Cliente dueno;
    private String nombre;
    private String raza;
    private LocalDate fechaNacimiento;
    private String observaciones;
    private boolean estado;

    @Override
    public String toString() {
        return "MascotaDTO{" +
                ", dueno='" + dueno + '\'' +
                ", nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
