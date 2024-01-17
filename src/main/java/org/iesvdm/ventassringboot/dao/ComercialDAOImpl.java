package org.iesvdm.ventassringboot.dao;

import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ComercialDAOImpl implements ComercialDAO {

    private final JdbcTemplate jdbcTemplate;

    public ComercialDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Comercial comercial) {
        jdbcTemplate.update("INSERT INTO comercial (nombre , apellido1 , apellido2, comisión) VALUES (?, ?, ?, ?)", comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision());
    }

    @Override
    public List<Comercial> getAll() {

        List<Comercial> comercialList = jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getDouble("comisión"))
        );

        return comercialList;
    }

    @Override
    public Optional<Comercial> find(int id) {
        Comercial cliente = jdbcTemplate
                .queryForObject("SELECT * FROM comercial WHERE id = ?"
                        , (rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getDouble("comisión"))
                        , id
                );

        if (cliente != null) return Optional.of(cliente);
        else return Optional.empty();
    }

    @Override
    public void update(Comercial comercial) {
        int rows = jdbcTemplate
                .update("UPDATE comercial SET nombre = ? , apellido1 = ?, apellido2 = ?, comisión = ?  WHERE id = ?", comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision(), comercial.getId());
        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados.");
    }

    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);

        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados.");
    }
}
