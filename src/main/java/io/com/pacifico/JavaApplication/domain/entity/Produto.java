package io.com.pacifico.JavaApplication.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "descricao")
  @NotBlank(message = "{campo.descricao.obrigatorio}")
  private String description;

  @Column(name = "preco_unitario")
  @NotNull(message = "{campo.preco.obrigatorio}")
  private BigDecimal preco;

}
