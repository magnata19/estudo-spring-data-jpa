package io.com.pacifico.JavaApplication.rest.controller;

import io.com.pacifico.JavaApplication.domain.entity.Produto;
import io.com.pacifico.JavaApplication.domain.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  @Autowired
  private Produtos produtos;

  @GetMapping("/{id}")
  public Produto getProdutoById(@PathVariable Integer id) {
    return produtos.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.")
            );
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED) // when success
  public Produto save(@RequestBody Produto produto) {
    return produtos.save(produto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT) // when success
  public void delete(@PathVariable Integer id) {
    produtos.findById(id).map(produto -> {
      produtos.delete(produto);
      return Void.class;
    }).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.")
            );
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Produto update(@PathVariable Integer id, @RequestBody Produto produto) {
    return produtos.findById(id).map(produtoExistente -> {
      produto.setId(produtoExistente.getId());
      produtos.save(produto);
      return produtoExistente;
    }).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.")
            );
  }

  @GetMapping
  List<Produto> listar(Produto produto) {
    ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example example = Example.of(produto, matcher);
    return produtos.findAll(example);
  }

}
