package io.com.pacifico.JavaApplication.domain.repository;

import io.com.pacifico.JavaApplication.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
