package org.iesvdm.ventassringboot.controller;

import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.iesvdm.ventassringboot.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping({"/pedidos", "/orders"})
    public String listar(Model model) {

        List<Pedido> pedidoList = pedidoService.listAll();
        model.addAttribute("listaPedidos", pedidoList);

        return "pedidos";
    }
}
