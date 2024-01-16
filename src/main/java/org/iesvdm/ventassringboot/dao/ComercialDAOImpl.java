package org.iesvdm.ventassringboot.dao;

import org.iesvdm.ventassringboot.domain.Comercial;
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

    /**
     * Se inserta un nuevo comercial en la base de datos
     * @param comercial, objeto que se recoge en un formulario
     */
    @Override
    public void create(Comercial comercial) {
        jdbcTemplate.update("INSERT INTO comercial (nombre , apellido1 , apellido2, comisión) VALUES (?, ?, ?, ?)", comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision());
    }

    /**
     * Recoge todos los coemrciales de la tabla comercial, crea un nuevo Objeto Comercial
     * y este se inserta en la List<Comercial>
     *
     * @return List<Comercial> listado de comerciales econtrado
     */
    @Override
    public List<Comercial> getAll() {

        List<Comercial> comercialList = jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getDouble("comisión"))
        );

        return comercialList;
    }

    /***
     * Se busca un Comercial por id y se recogen sus datos
     * @param id, por el cual se busca el comercial
     * @return Optional<Comercial>
     */
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

    /**
     * Se actualizan los datos del comercial, menos el ID
     *
     * @param comercial, nuevo objeto con datos a cambiar de un comercial
     */
    @Override
    public void update(Comercial comercial) {
        int rows = jdbcTemplate
                .update("UPDATE comercial SET nombre = ? , apellido1 = ?, apellido2 = ?, comisión = ?  WHERE id = ?", comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision(), comercial.getId());
        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados.");
    }

    /**
     * Se borra un comercial por su ID
     *
     * @param id, para saber que comercial se debe borrar
     */
    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);

        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados.");
    }
}
