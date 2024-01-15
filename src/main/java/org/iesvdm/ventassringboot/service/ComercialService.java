package org.iesvdm.ventassringboot.service;

import org.iesvdm.ventassringboot.dao.ComercialDAO;
import org.iesvdm.ventassringboot.domain.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {
    @Autowired
    private ComercialDAO comercialDAO;

    public List<Comercial> listAll() {

        return comercialDAO.getAll();
    }

    public Comercial one(Integer id) {
        Optional<Comercial> optFab = comercialDAO.find(id);
        return optFab.orElse(null);
    }

    public void newComercial(Comercial cliente) {

        comercialDAO.create(cliente);
    }

    public void replaceComercial(Comercial cliente) {

        comercialDAO.update(cliente);
    }

    public void deleteComercial(int id) {

        comercialDAO.delete(id);
    }

}
