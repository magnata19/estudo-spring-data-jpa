package io.com.pacifico.JavaApplication.rest.controller;

import io.com.pacifico.JavaApplication.domain.entity.Pedido;
import io.com.pacifico.JavaApplication.rest.dto.PedidoDTO;
import io.com.pacifico.JavaApplication.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

  private PedidoService service;

  public PedidoController (PedidoService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public Integer save (@RequestBody PedidoDTO dto) {
    Pedido pedido = service.salvar(dto);
    return pedido.getId();
  }

}
