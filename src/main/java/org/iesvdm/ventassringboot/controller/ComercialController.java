package org.iesvdm.ventassringboot.controller;

import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.iesvdm.ventassringboot.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
// @RequestMapping("/comerciales")
public class ComercialController {
    // Field injection is not recommended cuando usamos el @Autowired
    @Autowired
    private ComercialService comercialService;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping({"/comerciales", "/comercials"})
    public String listar(Model model) {

        List<Comercial> comercialList = comercialService.listAll();
        model.addAttribute("listaComerciales", comercialList);

        return "comerciales";
    }

    @GetMapping("/comerciales/{id}")
    public String detalle(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        // metodo en service de comercial
        List<Pedido> pedidos = comercialService.pedidosFromComercial(id);

        // TODO ordenar segun el TOTAL de todos los pedidos de un cliente
        // listado de clientes ordenados por cuantía de pedido de mayor a menor. El listado iría a continuación del listado de pedidos
        List<Pedido> ordenPedidosClientes = pedidos.stream().sorted(Comparator.comparing(Pedido::getIdCliente)).collect(Collectors.toList());
        model.addAttribute("ordenClientes", ordenPedidosClientes);

        List<Pedido> ordenPedidos = pedidos.stream().sorted(Comparator.comparing(Pedido::getTotal)).collect(Collectors.toList());
        model.addAttribute("pedidosLista", ordenPedidos);

        var sum = pedidos.stream().mapToDouble(Pedido::getTotal).sum();
        model.addAttribute("total", sum);

        var media = pedidos.stream().mapToDouble(Pedido::getTotal).sum() / pedidos.size();
        model.addAttribute("media", media);



        // get pedido by ID
        return "detalle-comercial";
    }

    @GetMapping("/comerciales/crear")
    public String crear(Model model) {

        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        return "crear-comercial";
    }

    @PostMapping("/comerciales/crear")
    public RedirectView submitCrear(@ModelAttribute("comercial") Comercial comercial) {

        comercialService.newComercial(comercial);

        return new RedirectView("/comerciales");
    }

    @GetMapping("/comerciales/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "editar-comercial";
    }


    @PostMapping("/comerciales/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("comercial") Comercial comercial) {

        comercialService.replaceComercial(comercial);

        return new RedirectView("/comerciales");
    }

    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        comercialService.deleteComercial(id);

        return new RedirectView("/comerciales");
    }
}
