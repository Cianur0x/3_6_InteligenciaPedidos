package org.iesvdm.ventassringboot.dao;

import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface ComercialDAO {
    public void create(Comercial comercial);

    public List<Comercial> getAll();

    public Optional<Comercial> find(int id);

    public void update(Comercial comercial);

    public void delete(int id);

}
