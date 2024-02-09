package io.com.pacifico.JavaApplication.domain.repositorio;

import io.com.pacifico.JavaApplication.domain.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class Clientes {

  private static String SELECT_ALL = "select * from cliente";


  @Autowired
  private EntityManager entityManager;

  @Transactional
  public Cliente salvarCliente(Cliente cliente) {
    entityManager.persist(cliente);
    return cliente;
  }

  @Transactional
  public Cliente atualizarCliente(Cliente cliente) {
    entityManager.merge(cliente);
    return cliente;
  }

  @Transactional
  public void deletar (Cliente cliente) {
    if(!entityManager.contains(cliente)){
      cliente = entityManager.merge(cliente);
    }
    entityManager.remove(cliente);
  }

  @Transactional
  public void deletar(Integer id) {
    Cliente cliente = entityManager.find(Cliente.class, id);
    deletar(cliente);
  }

  @Transactional(readOnly = true)
  public List<Cliente> buscarPorNome(String nome) {
    String jpql = "select c from Cliente c where c.nome like :nome ";
    TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
    query.setParameter("nome", "%" + nome + "%");
    return query.getResultList();
  }

  @Transactional
  public List<Cliente> listarClientes() {
    return entityManager
            .createQuery("from Cliente", Cliente.class)
            .getResultList();
  }

}
