package io.com.pacifico.JavaApplication.service;

import io.com.pacifico.JavaApplication.domain.entity.Pedido;
import io.com.pacifico.JavaApplication.domain.enums.StatusPedido;
import io.com.pacifico.JavaApplication.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

  Pedido salvar(PedidoDTO dto);

  Optional<Pedido> obterPedidoCompleto(Integer id);

  void atualizarStatus(Integer id, StatusPedido statusPedido);
}
