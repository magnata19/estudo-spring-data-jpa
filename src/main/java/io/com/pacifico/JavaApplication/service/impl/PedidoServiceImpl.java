package io.com.pacifico.JavaApplication.service.impl;

import io.com.pacifico.JavaApplication.domain.entity.Cliente;
import io.com.pacifico.JavaApplication.domain.entity.ItemPedido;
import io.com.pacifico.JavaApplication.domain.entity.Pedido;
import io.com.pacifico.JavaApplication.domain.entity.Produto;
import io.com.pacifico.JavaApplication.domain.repository.Clientes;
import io.com.pacifico.JavaApplication.domain.repository.ItemsPedido;
import io.com.pacifico.JavaApplication.domain.repository.Pedidos;
import io.com.pacifico.JavaApplication.domain.repository.Produtos;
import io.com.pacifico.JavaApplication.exception.RegraNegocioException;
import io.com.pacifico.JavaApplication.rest.dto.ItemPedidoDTO;
import io.com.pacifico.JavaApplication.rest.dto.PedidoDTO;
import io.com.pacifico.JavaApplication.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

  private final Pedidos pedidoRepository;
  private final Clientes clienteRepository;
  private final Produtos produtosRepository;
  private final ItemsPedido itemsPedidosRepository;

  @Override
  @Transactional
  public Pedido salvar(PedidoDTO dto) {
    Integer idCliente = dto.getCliente();
    Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() ->
                    new RegraNegocioException("Código de cliente inválido"));

    Pedido pedido = new Pedido();
    pedido.setTotal(dto.getTotal());
    pedido.setDataPedido(LocalDate.now());
    pedido.setCliente(cliente);

    List<ItemPedido> itemsPedidos = converterItems(pedido, dto.getItems());
    pedidoRepository.save(pedido);
    itemsPedidosRepository.saveAll(itemsPedidos);
    return pedido;
  }

  public List<ItemPedido> converterItems (Pedido pedido, List<ItemPedidoDTO> items) {
    if(items.isEmpty()) {
      new RegraNegocioException("Não é possível realizar um pedido sem itens.");
    }

    return items.stream()
            .map(dto -> {
              Integer idProduto = dto.getProduto();
              Produto produto = produtosRepository.findById(idProduto).orElseThrow(() ->
                              new RegraNegocioException("Código de produto inválido: " + idProduto));
              ItemPedido itemPedido = new ItemPedido();
              itemPedido.setQuantidade(dto.getQuantidade());
              itemPedido.setPedido(pedido);
              itemPedido.setProduto(produto);
              return itemPedido;
            }).collect(Collectors.toList());
  }
}
