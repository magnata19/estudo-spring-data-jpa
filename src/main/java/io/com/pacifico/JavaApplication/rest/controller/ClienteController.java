package io.com.pacifico.JavaApplication.rest.controller;

import io.com.pacifico.JavaApplication.domain.entity.Cliente;
import io.com.pacifico.JavaApplication.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

  @Autowired
  private Clientes clientes;

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity getClienteById(@PathVariable Integer id) {
    Optional<Cliente> cliente = clientes.findById(id);

    if(cliente.isPresent()) {
      return ResponseEntity.ok(cliente.get());
    }
    return ResponseEntity.status(404).build();
  }

  @PostMapping()
  @ResponseBody
  public ResponseEntity save(@RequestBody Cliente cliente) {
    Cliente clienteSave = clientes.save(cliente);
    return ResponseEntity.ok(clienteSave);
  }
}
