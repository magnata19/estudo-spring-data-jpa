package io.com.pacifico.JavaApplication.domain.entity;

import io.com.pacifico.JavaApplication.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @Column(name = "data_pedido")
  private LocalDate dataPedido;

  @Column(name = "total", scale = 2, precision = 20)
  private BigDecimal total;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusPedido status;

  @OneToMany(mappedBy = "pedido")
  List<ItemPedido> itens;


}
