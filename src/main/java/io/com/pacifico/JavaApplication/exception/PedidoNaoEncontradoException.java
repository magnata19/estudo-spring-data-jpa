package io.com.pacifico.JavaApplication.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

  public PedidoNaoEncontradoException() {
    super("Pedido não encontrado.");
  }
}
