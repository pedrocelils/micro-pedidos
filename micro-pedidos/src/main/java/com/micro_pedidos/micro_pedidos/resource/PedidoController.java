package com.micro_pedidos.micro_pedidos.resource;

import com.micro_pedidos.micro_pedidos.entities.Cliente;
import com.micro_pedidos.micro_pedidos.entities.ReservaMesa;
import com.micro_pedidos.micro_pedidos.entities.Pedido;
import com.micro_pedidos.micro_pedidos.repository.ClienteFeignClient;
import com.micro_pedidos.micro_pedidos.repository.PedidoRepository;
import com.micro_pedidos.micro_pedidos.repository.ReservaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ClienteFeignClient clienteFeignClient;
    private final ReservaFeignClient reservaFeignClient;

    // Injeção via construtor
    @Autowired
    public PedidoController(PedidoRepository pedidoRepository, ClienteFeignClient clienteFeignClient, ReservaFeignClient reservaFeignClient) {
        this.pedidoRepository = pedidoRepository;
        this.clienteFeignClient = clienteFeignClient;
        this.reservaFeignClient = reservaFeignClient;
    }

    @GetMapping
    public List<Pedido> listar() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        // Para cada pedido, buscar o cliente e adicionar o nome ao pedido
        for (Pedido pedido : pedidos) {
            // Buscar cliente usando clienteId
            Cliente cliente = clienteFeignClient.buscarCliente(pedido.getClienteId());
            ReservaMesa mesa = reservaFeignClient.obterReserva((long) pedido.getMesaid());

            if (cliente != null) {
                pedido.setNomeCliente(cliente.getNome());
                pedido.setEmailCliente(cliente.getEmail());
                pedido.setMesaid(mesa.getMesaId());// Definir o nome do cliente no pedido
            }
        }

        return pedidos;
    }
    /*@PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        // Validar cliente
        Cliente cliente = clienteFeignClient.buscarCliente(pedido.getClienteId());
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado!");
        }

        // Calcular total do pedido (simplificado)
        double total = pedido.getItens().size() * 10.0; // Exemplo: cada item vale R$10
        pedido.setTotal(total);
        pedido.setDataPedido(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }*/
    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {

        System.out.println("Reserva ID recebido: " + pedido.getEmailCliente());

        // Validar cliente (já deve ser feito com Feign Client, por exemplo)
        Cliente cliente = clienteFeignClient.buscarCliente(pedido.getClienteId());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado!");
        }

        // Validar reserva (utilizando o Feign Client)
        ReservaMesa reservaMesa = reservaFeignClient.obterReserva((long) pedido.getMesaid());
        /*if (reservaMesa == null) {
            throw new RuntimeException("Reserva não encontrada!");
        }*/

        // Calcular total do pedido (simplificado)
        double total = pedido.getItens().size() * 10.0; // Exemplo: cada item vale R$10
        pedido.setTotal(total);
        pedido.setDataPedido(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

}
