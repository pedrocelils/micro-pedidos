package com.micro_pedidos.micro_pedidos.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long clienteId;
        private Double total;

        @ElementCollection
        private List<Long> itens;

        private LocalDateTime dataPedido;

        private String nomeCliente;
        private String emailCliente;

        private int Mesaid;



    // Getters e Setters
}
