package org.iesvdm.ventassringboot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.ventassringboot.domain.Cliente;
import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.iesvdm.ventassringboot.dto.PedidoFormDTO;
import org.iesvdm.ventassringboot.mapper.PedidoMapper;
import org.iesvdm.ventassringboot.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoMapper pedidoMapper;

    /**
     * Maneja las solicitudes GET para mostrar la lista de pedidos.
     *
     * @param model El modelo que se utilizará para pasar datos a la vista.
     * @return El nombre de la vista "pedidos" que mostrará la lista de pedidos.
     */
    @GetMapping({"/pedidos", "/orders"})
    public String listar(Model model) {

        List<Pedido> pedidoList = pedidoService.listAll();
        model.addAttribute("listaPedidos", pedidoList);

        return "pedidos";
    }

    @GetMapping("/pedidos/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        Pedido pedido = pedidoService.one(id);
        model.addAttribute("pedido", pedido);
        return "detalle-pedido";
    }

    @GetMapping("/pedidos/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Pedido pedido = pedidoService.one(id);
        model.addAttribute("pedido", pedido);

        List<Cliente> listaClientes = this.pedidoService.listAllClientes();
        model.addAttribute("listaClientes", listaClientes);

        List<Comercial> listaComerciales = this.pedidoService.listAllComerciales();
        model.addAttribute("listaComerciales", listaComerciales);
        System.out.println(pedido);
        return "editar-pedido";
    }

    @PostMapping("/pedidos/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute("pedidoFormDTO") PedidoFormDTO pedidoFormDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("pedido", pedidoFormDTO);

            System.out.println(pedidoFormDTO);
            List<Cliente> listaClientes = this.pedidoService.listAllClientes();
            model.addAttribute("listaClientes", listaClientes);

            List<Comercial> listaComerciales = this.pedidoService.listAllComerciales();
            model.addAttribute("listaComerciales", listaComerciales);

            return "editar-pedido";
        }
        System.out.println("PEdido recorgido " + pedidoFormDTO);

        Pedido pedido = this.pedidoMapper.pedidoFormDTOAPedido(pedidoFormDTO);
        pedidoService.replacePedido(pedido);

        return "redirect:/pedidos";
    }
}
