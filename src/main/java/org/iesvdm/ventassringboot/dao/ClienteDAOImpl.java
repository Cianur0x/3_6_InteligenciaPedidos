package org.iesvdm.ventassringboot.dao;

import org.iesvdm.ventassringboot.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Cliente cliente) {
        jdbcTemplate.update("INSERT INTO cliente (nombre , apellido1 , apellido2, ciudad, categoría) VALUES (?, ?, ?, ?, ?)", cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getCiudad(), cliente.getCategoria());
    }

    @Override
    public List<Cliente> getAll() {

        List<Cliente> listaClientes = jdbcTemplate.query(
                "SELECT * FROM cliente",
                (rs, rowNum) -> new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("ciudad"), rs.getString("categoría"))
        );

        return listaClientes;
    }

    @Override
    public Optional<Cliente> find(int id) {
        Cliente cliente = jdbcTemplate
                .queryForObject("SELECT * FROM cliente WHERE id = ?"
                        , (rs, rowNum) -> new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("ciudad"), rs.getString("categoría"))
                        , id
                );

        if (cliente != null) return Optional.of(cliente);
        else return Optional.empty();
    }

    @Override
    public void update(Cliente cliente) {
        int rows = jdbcTemplate
                .update("UPDATE cliente SET nombre = ? , apellido1 = ?, apellido2 = ?, categoría = ?  WHERE id = ?", cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getCategoria(), cliente.getId());
        if (rows == 0) System.out.println("Update de cliente con 0 registros actualizados.");
    }

    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);

        if (rows == 0) System.out.println("Update de cliente con 0 registros actualizados.");
    }
}
