package io.com.pacifico.JavaApplication.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Integer id;

  @Column(name = "nome", length = 100)
  @NotBlank(message = "{campo.nome.obrigatorio}")
  private String nome;

  @Column(name = "cpf", length = 11)
  @NotBlank(message = "{campo.cpf.obrigatorio}")
  @CPF(message = "{campo.cpf.invalido}")
  private String cpf;

  @JsonIgnore
  @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
  private Set<Pedido> pedido;


  public Cliente(String nome) {
    this.nome = nome;
  }

  public Cliente(Integer id, String nome) {
    this.id = id;
    this.nome = nome;
  }
}
