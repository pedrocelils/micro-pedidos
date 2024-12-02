package com.micro_pedidos.micro_pedidos.repository;

import com.micro_pedidos.micro_pedidos.entities.ReservaMesa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mesas", url = "http://localhost:8083/reservas")
public interface ReservaFeignClient {
    @GetMapping("/{id}")
    ReservaMesa obterReserva(@PathVariable("id") Long id);
}
