package io.com.pacifico.JavaApplication.domain.repositorio;

import io.com.pacifico.JavaApplication.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;


public interface Clientes extends JpaRepository<Cliente, Integer> {

  @Query(value = " select c from Cliente c where c.nome like :nome")
  List<Cliente> encontrarPorNome(@Param("nome") String nome);

  boolean existsByNome(String nome);
}
