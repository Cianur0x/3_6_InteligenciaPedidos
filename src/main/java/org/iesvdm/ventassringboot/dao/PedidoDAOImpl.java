package org.iesvdm.ventassringboot.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.ventassringboot.domain.Cliente;
import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class PedidoDAOImpl implements PedidoDAO<Pedido> {
    private final JdbcTemplate jdbcTemplate;


    public PedidoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Pedido pedido) {
        jdbcTemplate.update("INSERT INTO pedido ( total , fecha, id_cliente, id_comercial) VALUES (?, ?, ?, ?)",
                pedido.getTotal(), pedido.getFecha(), pedido.getIdCliente(), pedido.getIdComercial());
    }

    @Override
    public List<Pedido> getAll() {
        List<Pedido> pedidosList = jdbcTemplate.query("SELECT * FROM pedido",
                (rs, rowNum) -> new Pedido(rs.getInt("id"), rs.getDouble("total"), rs.getDate("fecha").toLocalDate(), rs.getInt("id_cliente"), rs.getInt("id_comercial"))
        );
        return pedidosList;
    }

    @Override
    public Optional<Pedido> find(int id) {
        Pedido pedido = jdbcTemplate
                .queryForObject("SELECT * FROM pedido WHERE id = ?"
                        , (rs, rowNum) -> new Pedido(rs.getInt("id"), rs.getDouble("total"), rs.getDate("fecha").toLocalDate(), rs.getInt("id_cliente"), rs.getInt("id_comercial"))
                        , id
                );


        if (pedido != null) return Optional.of(pedido);
        else return Optional.empty();
    }

    @Override
    public void update(Pedido pedido) {
        this.jdbcTemplate.update("""
                      update pedido set total = ?, fecha = ?, id_cliente = ?, id_comercial = ? where id = ?
                    """, pedido.getTotal(), pedido.getFecha(), pedido.getCliente().getId(), pedido.getComercial().getId(), pedido.getId());
    }

    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM pedido WHERE id = ?", id);
        if (rows == 0) System.out.println("Update de pedido con 0 registros actualizados.");
    }

    @Override
    public List<Pedido> getPedidosFromComercial(int id) {
        List<Pedido> comercialPedList = jdbcTemplate.query("""
                        select  * from pedido as P left join cliente as C on P.id_cliente = C.id left join comercial co on P.id_comercial = co.id WHERE co.id = ?;
                        """,
                (rs, rowNum) -> new Pedido(rs.getInt("id"),
                        rs.getDouble("total"),
                        rs.getDate("fecha").toLocalDate(),
                        new Cliente(rs.getInt("C.id"),
                                rs.getString("C.nombre"),
                                rs.getString("C.apellido1"),
                                rs.getString("C.apellido2"),
                                rs.getString("C.ciudad"),
                                rs.getInt("C.categoría"),
                                rs.getString("C.ciudad")
                        ),
                        new Comercial(rs.getInt("CO.id"),
                                rs.getString("CO.nombre"),
                                rs.getString("CO.apellido1"),
                                rs.getString("CO.apellido2"),
                                BigDecimal.valueOf(rs.getDouble("CO.comisión"))
                        ), rs.getInt("C.id"), rs.getInt("CO.id")), id);

        return comercialPedList;
    }

    @Override
    public Optional<Cliente> findClienteBy(int pedidoId) {
        Optional<Cliente> cliente = Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT c.* FROM pedido p join cliente c on p.id_cliente = c.id where p.id = ?",
                        (rs, rowNum) -> new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("ciudad"), rs.getInt("categoría"), rs.getString("email"))
                        , pedidoId));

        return cliente;
    }

    @Override
    public Optional<Comercial> findComercialBy(int pedidoId) {
        Optional<Comercial> comercial = Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT co.* FROM pedido p join comercial co on p.id_comercial = co.id where p.id = ?",
                        (rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), BigDecimal.valueOf(rs.getFloat("comisión")))
                        , pedidoId));

        return comercial;
    }
}
