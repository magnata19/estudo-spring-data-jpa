package io.com.pacifico.JavaApplication.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

  @NotNull(message = "O código do cliente é obnrigatório.")
  private Integer cliente;

  @NotNull(message = "O total de pedido é obrigatório.")
  private BigDecimal total;
  private List<ItemPedidoDTO> items;
}
