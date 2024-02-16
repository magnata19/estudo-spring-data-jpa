package io.com.pacifico.JavaApplication.service;

import io.com.pacifico.JavaApplication.domain.entity.Pedido;
import io.com.pacifico.JavaApplication.rest.dto.PedidoDTO;

public interface PedidoService {

  Pedido salvar(PedidoDTO dto);
}
