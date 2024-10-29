package com.spapatitas.persistence.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Cita {

    private Long idCita;
    private Cliente cliente;
    private LocalDateTime fechaHora;
    private List<Servicio> servicios;
    private double ValorTotal;

    public void addServicio(Servicio servicio) {
        this.servicios.add(servicio);
        this.ValorTotal += servicio.getTipoServicio().getPrecio();
    }

    public void removeServicio(Servicio servicio) {
        this.servicios.remove(servicio);
        this.ValorTotal -= servicio.getTipoServicio().getPrecio();
    }

}
