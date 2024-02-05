package io.com.pacifico.JavaApplication.domain.entity;

import java.math.BigDecimal;

public class Produto {
  private Integer id;
  private String description;
  private BigDecimal preco;

  public Integer getId(){
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }
}
