package io.com.pacifico.JavaApplication.domain.repository;

import io.com.pacifico.JavaApplication.domain.entity.Cliente;
import io.com.pacifico.JavaApplication.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

  List<Pedido> findByCliente (Cliente cliente);
}
