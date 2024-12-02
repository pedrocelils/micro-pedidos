package com.micro_pedidos.micro_pedidos.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservaMesa {
    private Long id;
    private Long clienteId;
    private int mesaId;
    private LocalDateTime dataReserva;
}
