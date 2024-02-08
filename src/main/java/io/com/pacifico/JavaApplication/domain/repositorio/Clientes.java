package io.com.pacifico.JavaApplication.domain.repositorio;

import io.com.pacifico.JavaApplication.domain.entity.Cliente;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class Clientes {

  private static String INSERT = "insert into cliente (nome) values(?)";
  private static String SELECT_ALL = "select * from cliente";
  private static String UPDATE = "update cliente set nome = ? where id = ?";
  private static String DELETE = "delete from cliente where id = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private EntityManager entityManager;

  @Transactional
  public Cliente salvarCliente(Cliente cliente) {
    entityManager.persist(cliente);
    return cliente;
  }

  public Cliente atualizarCliente(Cliente cliente) {
    jdbcTemplate.update(UPDATE, new Object[]{
            cliente.getNome(),
            cliente.getId()
    });
    return cliente;
  }

  public void deletar (Cliente cliente) {
    deletar(cliente.getId());
  }

  public void deletar(Integer id) {
    jdbcTemplate.update(DELETE, new Object[]{id});
  }

  public List<Cliente> buscarPorNome(String nome) {
    return jdbcTemplate.query(
            SELECT_ALL.concat(" where nome like ?"),
            new Object[]{"%" + nome + "%"},
            obterClienteMapper());
  }

  public List<Cliente> listarClientes() {
    return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
  }

  private static RowMapper<Cliente> obterClienteMapper() {
    return new RowMapper<Cliente>() {
      @Override
      public Cliente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Integer id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        return new Cliente(id, nome);
      }
    };
  }
}
