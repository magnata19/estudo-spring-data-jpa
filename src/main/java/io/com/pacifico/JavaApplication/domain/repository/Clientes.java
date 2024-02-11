package io.com.pacifico.JavaApplication.domain.repository;

import io.com.pacifico.JavaApplication.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {

  @Query(value = " select c from Cliente c where c.nome like :nome")
  List<Cliente> encontrarPorNome(@Param("nome") String nome);

  @Query(value = "delete from Cliente c where c.nome = :nome")
  void deleteByNome(String nome);

  boolean existsByNome(String nome);

  @Query(value = "select c from Cliente c left join fetch c.pedido where c.id = :id")
  Cliente findClienteFetchPedidos(@Param("id") Integer id);


}
