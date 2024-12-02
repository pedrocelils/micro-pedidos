package com.micro_pedidos.micro_pedidos.repository;

import com.micro_pedidos.micro_pedidos.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
