package io.com.pacifico.JavaApplication.domain.repository;

import io.com.pacifico.JavaApplication.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
