package org.iesvdm.ventassringboot.dao;

import org.iesvdm.ventassringboot.domain.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDAOImpl implements ClienteDAO {


    private final JdbcTemplate jdbcTemplate;


    public ClienteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Se inserta un nuevo cliente en la base de datos
     * @param cliente, objeto que se recoge en un formulario
     */
    @Override
    public void create(Cliente cliente) {
        jdbcTemplate.update("INSERT INTO cliente (nombre , apellido1 , apellido2, ciudad, categoría) VALUES (?, ?, ?, ?, ?)", cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getCiudad(), cliente.getCategoria());
    }

    /**
     * Recoge todos los clientes de la tabla cliente, crea un nuevo Objeto CLiente
     * y este se inserta en la List<CLiente>
     *
     * @return List<Cliente> lsiado de cleintes encontrado
     */
    @Override
    public List<Cliente> getAll() {

        List<Cliente> listaClientes = jdbcTemplate.query(
                "SELECT * FROM cliente",
                (rs, rowNum) -> new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("ciudad"), rs.getInt("categoría"))
        );

        return listaClientes;
    }

    /***
     * Se busca un cliente por id y se recogen sus datos
     * @param id, por el cual se busca el cliente
     * @return Optional<Cliente>
     */
    @Override
    public Optional<Cliente> find(int id) {
        Cliente cliente = jdbcTemplate
                .queryForObject("SELECT * FROM cliente WHERE id = ?"
                        , (rs, rowNum) -> new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("ciudad"), rs.getInt("categoría"))
                        , id
                );

        if (cliente != null) return Optional.of(cliente);
        else return Optional.empty();
    }

    /**
     * Se actualizan los datos del ciente, menos el ID
     * @param cliente, nuevo objeto con datos a cambiar de un cliente
     */
    @Override
    public void update(Cliente cliente) {
        int rows = jdbcTemplate
                .update("UPDATE cliente SET nombre = ? , apellido1 = ?, apellido2 = ?, categoría = ?  WHERE id = ?", cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getCategoria(), cliente.getId());
        if (rows == 0) System.out.println("Update de cliente con 0 registros actualizados.");
    }

    /**
     * Se borra un cliente por su ID
     * @param id, para saber que cliente se debe borrar
     */
    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);

        if (rows == 0) System.out.println("Update de cliente con 0 registros actualizados.");
    }


    public void create_SIN_RECARGA_DE_ID(Cliente cliente) {

        jdbcTemplate.update("""
                        INSERT INTO cliente
                        (nombre, apellido1, apellido2, ciudad, categoría)
                        VALUE
                        (?, ?, ?, ?, ?)
                        """
                , cliente.getNombre()
                , cliente.getApellido1()
                , cliente.getApellido2()
                , cliente.getCiudad()
                , cliente.getCategoria());


        //NO SE ACTUALIZA EL ID AUTO_INCREMENT DE MYSQL EN EL BEAN DE CLIENTE
    }

    public void create_CON_RECARGA_DE_ID_POR_PS(Cliente cliente) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("""
                            INSERT INTO cliente
                            (nombre, apellido1, apellido2, ciudad, categoría)
                            VALUE
                            (?, ?, ?, ?, ?)
                            """, Statement.RETURN_GENERATED_KEYS);
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getApellido1());
            ps.setString(idx++, cliente.getApellido2());
            ps.setString(idx++, cliente.getCiudad());
            ps.setInt(idx++, cliente.getCategoria());
            return ps;
        }, keyHolder);
        //SE ACTUALIZA EL ID AUTO_INCREMENT DE MYSQL EN EL BEAN DE CLIENTE MEDIANTE EL KEYHOLDER
        cliente.setId(keyHolder.getKey().intValue());
    }

    public void create_CON_RECARGA_DE_ID_POR_SIMPLEJDBCINSERT(Cliente cliente) {

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("cliente")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nombre", cliente.getNombre())
                .addValue("apellido1", cliente.getApellido1())
                .addValue("apellido2", cliente.getApellido2())
                .addValue("ciudad", cliente.getCiudad())
                .addValue("categoría", cliente.getCategoria());
        Number number = simpleJdbcInsert.executeAndReturnKey(params);

        cliente.setId(number.intValue());
    }


}
