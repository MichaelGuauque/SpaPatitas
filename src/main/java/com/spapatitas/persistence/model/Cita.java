package com.spapatitas.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @ManyToMany(targetEntity = TipoServicio.class, fetch = FetchType.LAZY)
    @JoinTable(name = "servicio")
    private List<TipoServicio> tipoServicios;

    @Column(nullable = false)
    private double ValorTotal;

    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente;

    public void addTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicios.add(tipoServicio);
        this.ValorTotal += tipoServicio.getPrecio();
    }

    public void removeTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicios.remove(tipoServicio);
        this.ValorTotal -= tipoServicio.getPrecio();
    }
}
