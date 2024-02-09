package io.com.pacifico.JavaApplication.domain.repositorio;

import io.com.pacifico.JavaApplication.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;


public interface Clientes extends JpaRepository<Cliente, Integer> {

  List<Cliente> findByNomeLike(String nome);
}
