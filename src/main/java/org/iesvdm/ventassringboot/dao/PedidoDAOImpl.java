package org.iesvdm.ventassringboot.dao;

import org.iesvdm.ventassringboot.domain.Cliente;
import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoDAOImpl implements PedidoDAO {
    private final JdbcTemplate jdbcTemplate;

    public PedidoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pedido> getAll() {
        List<Pedido> pedidosList = jdbcTemplate.query("SELECT * FROM pedido",
                (rs, rowNum) -> new Pedido(rs.getInt("id"), rs.getDouble("total"), rs.getDate("fecha").toLocalDate(), rs.getInt("id_cliente"), rs.getInt("id_comercial"))
        );
        return pedidosList;
    }

    @Override
    public List<Pedido> getPedidosFromComercial(int id) {
        List<Pedido> comercialPedList = jdbcTemplate.query("SELECT * FROM pedido WHERE id_comercial = ?",
                (rs, rowNum) -> new Pedido(rs.getInt("id"), rs.getDouble("total"), rs.getDate("fecha").toLocalDate(), rs.getInt("id_cliente"), rs.getInt("id_comercial"))
                , id);
        return comercialPedList;
    }

    @Override
    public Optional<Cliente> findClienteBy(int pedidoId) {

//        Optional<Cliente> cliente = this.jdbcTemplate.queryForObject("select C.* from pedido P join cliente C on P"
//                , (rs, rowNum) -> new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("ciudad"), rs.getInt("categoría"))
//        );

        return null;
    }

    @Override
    public Optional<Comercial> findComercialBy(int pedidoId) {
        return null;
    }

//    @Override
//    public void create(Pedido pedido) {
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        //Con recuperación de id generado
//        int rows = jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement("""
//                        insert into pedido ( total, fecha, id_cliente, id_comercial)
//                        values (?, ?, ?, ?);
//                        """, new String[] { "id" });
//            int idx = 1;
//            ps.setDouble(idx++, pedido.getTotal());
//            ps.setDate(idx++, new java.sql.Date(pedido.getFecha().getTime()));
//            ps.setInt(idx++, pedido.getCliente().getId());
//            ps.setInt(idx++, pedido.getComercial().getId());
//            return ps;
//        },keyHolder);
//
//        log.info("Filas creadas {}", rows);
//        log.debug("Pedido con id = {} grabado correctamente",keyHolder.getKey().intValue());
//
//        pedido.setId(keyHolder.getKey().intValue());
//
//    }


//    @Override
//    public Optional<Pedido> find(int id) {
//
//        Pedido pedido= this.jdbcTemplate.queryForObject("""
//                    select * from pedido P left join cliente C on  P.id_cliente = C.id
//                                        left join comercial CO on P.id_comercial = CO.id
//                                        WHERE P.id = ?
//                """, (rs, rowNum) -> PedidoDAO.newPedido(rs), id);
//
//        if (pedido != null) return Optional.of(pedido);
//        log.debug("No encontrado pedido con id {} devolviendo Optional.empty()", id);
//        return Optional.empty();
//    }

//    @Override
//    public void update(Pedido pedido) {
//
//        this.jdbcTemplate.update("""
//                      update pedido set total = ?, fecha = ?, id_cliente = ?, id_comercial = ? where id = ?
//                    """, pedido.getTotal(), pedido.getFecha(), pedido.getCliente().getId(), pedido.getComercial().getId(), pedido.getId());
//
//    }

//    @Override
//    public void delete(long id) {
//
//        this.jdbcTemplate.update("""
//                            delete from pedido where id = ?
//                            """
//                , id
//        );
//
//    }


}
