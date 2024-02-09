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
			clientes.save(new Cliente("Davidson"));
			clientes.save(new Cliente("Pacifico"));

			List<Cliente> listarClientes = clientes.findAll();
			listarClientes.forEach(System.out::println);

			System.out.println("Atualizando Cliente");
			listarClientes.forEach(c -> {
				c.setNome(c.getNome() + "atualizado.");
				clientes.save(c);
				System.out.println(c);
			});

			System.out.println("Buscando Clientes ");
			clientes.findByNomeLike("atual").forEach(System.out::println);

			System.out.println("Deletando clientes");
			clientes.findAll().forEach(c -> {
				clientes.delete(c);
			});

			listarClientes = clientes.findAll();
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
