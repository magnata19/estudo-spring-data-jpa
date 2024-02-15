package io.com.pacifico.JavaApplication.service.impl;

import io.com.pacifico.JavaApplication.domain.entity.Produto;
import io.com.pacifico.JavaApplication.domain.repository.Pedidos;
import io.com.pacifico.JavaApplication.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

  private Pedidos repository;

  public PedidoServiceImpl (Pedidos repository) {
    this.repository = repository;
  }
}
