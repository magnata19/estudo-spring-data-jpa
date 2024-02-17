package io.com.pacifico.JavaApplication.rest.controller;

import io.com.pacifico.JavaApplication.domain.entity.ItemPedido;
import io.com.pacifico.JavaApplication.domain.entity.Pedido;
import io.com.pacifico.JavaApplication.domain.enums.StatusPedido;
import io.com.pacifico.JavaApplication.rest.dto.AtualizacaoStatusPedidoDTO;
import io.com.pacifico.JavaApplication.rest.dto.InformacaoItemPedidoDTO;
import io.com.pacifico.JavaApplication.rest.dto.InformacoesPedidoDTO;
import io.com.pacifico.JavaApplication.rest.dto.PedidoDTO;
import io.com.pacifico.JavaApplication.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

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

  @GetMapping("/{id}")
  public InformacoesPedidoDTO getById (@PathVariable Integer id ) {
    return service.obterPedidoCompleto(id).map(p -> converter(p)).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado")
            );
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
    String novoStatus = dto.getNovoStatus();
    service.atualizarStatus(id, StatusPedido.valueOf(novoStatus));
  }

  public InformacoesPedidoDTO converter ( Pedido pedido) {
    return InformacoesPedidoDTO.builder()
            .codigo(pedido.getId())
            .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .cpf(pedido.getCliente().getCpf())
            .nomeCliente(pedido.getCliente().getNome())
            .total(pedido.getTotal())
            .status(pedido.getStatus().name())
            .items(converter(pedido.getItens()))
            .build();
  }

  public List <InformacaoItemPedidoDTO> converter (List<ItemPedido> itens) {

    if (CollectionUtils.isEmpty(itens)) {
      return Collections.emptyList();
    }

    return  itens.stream().map(item ->
            InformacaoItemPedidoDTO.builder()
                    .descricaoProduto(item.getProduto().getDescription())
                    .precoUnitario(item.getProduto().getPreco())
                    .quantidade(item.getQuantidade())
                    .build()
            ).collect(Collectors.toList());

  }
}
