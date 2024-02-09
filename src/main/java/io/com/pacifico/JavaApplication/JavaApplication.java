package io.com.pacifico.JavaApplication;

import io.com.pacifico.JavaApplication.domain.entity.Cliente;
import io.com.pacifico.JavaApplication.domain.repositorio.Clientes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class JavaApplication {

	@Bean
	public CommandLineRunner exibir(Clientes clientes) {
		return args -> {
			System.out.println("Salvando Cliente");
			clientes.salvarCliente(new Cliente("Davidson"));
			clientes.salvarCliente(new Cliente("Pacifico"));

			List<Cliente> listarClientes = clientes.listarClientes();
			listarClientes.forEach(System.out::println);

			System.out.println("Atualizando Cliente");
			listarClientes.forEach(c -> {
				c.setNome(c.getNome() + "atualizado.");
				clientes.atualizarCliente(c);
				System.out.println(c);
			});

			System.out.println("Buscando Clientes ");
			clientes.buscarPorNome("atual").forEach(System.out::println);

			System.out.println("Deletando clientes");
			clientes.listarClientes().forEach(c -> {
				clientes.deletar(c);
			});

			listarClientes = clientes.listarClientes();
			if(listarClientes.isEmpty()) {
				System.out.println("Nenhum cliente encontrado.");
			} else {
				listarClientes.forEach(System.out::println);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaApplication.class, args);
	}

}
