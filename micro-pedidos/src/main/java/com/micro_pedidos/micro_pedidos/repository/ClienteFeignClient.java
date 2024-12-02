package com.micro_pedidos.micro_pedidos.repository;

import com.micro_pedidos.micro_pedidos.entities.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientes", url = "http://localhost:8082/clientes")
public interface ClienteFeignClient {
    @GetMapping("/{id}")
    Cliente buscarCliente(@PathVariable Long id);
}
