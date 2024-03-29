package org.iesvdm.ventassringboot.controller;

import jakarta.validation.Valid;
import org.iesvdm.ventassringboot.domain.Cliente;
import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.iesvdm.ventassringboot.dto.ComercialDTO;
import org.iesvdm.ventassringboot.mapper.ComercialMapper;
import org.iesvdm.ventassringboot.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
// @RequestMapping("/comerciales")
public class ComercialController {
    // Field injection is not recommended cuando usamos el @Autowired
    @Autowired
    private ComercialService comercialService;

    @Autowired
    private ComercialMapper comercialMapper;

    /**
     * Maneja las solicitudes GET para la página de inicio.
     *
     * @return El nombre de la vista "index".
     */
    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    /**
     * Maneja las solicitudes GET para mostrar la lista de comerciales.
     *
     * @param model El modelo que se utilizará para pasar datos a la vista.
     * @return El nombre de la vista "comerciales" que mostrará la lista de comerciales.
     */
    @GetMapping({"/comerciales", "/comercials"})
    public String listar(Model model) {

        List<Comercial> comercialList = comercialService.listAll();
        model.addAttribute("listaComerciales", comercialList);

        return "comerciales";
    }

    /**
     * Maneja las solicitudes GET para mostrar los detalles de un comercial específico.
     *
     * @param model El modelo que se utilizará para pasar datos a la vista.
     * @param id    El identificador del comercial cuyos detalles se mostrarán.
     * @return El nombre de la vista "detalle-comercial" que mostrará los detalles del comercial.
     */
    @GetMapping("/comerciales/{id}")
    public String detalle(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.one(id);
        List<Pedido> pedidos = comercialService.pedidosFromComercial(id);
        ComercialDTO comercialDTO = null;
        if (!pedidos.isEmpty()) {
            List<Map.Entry<Cliente, Double>> sortedTotales = comercialService.totalClienteSorted(pedidos);
            List<Pedido> sortedByTotal = comercialService.pedidosSortedByTotal(pedidos);
            BigDecimal sum = comercialService.totalPedidosComercial(pedidos);
            BigDecimal media = comercialService.mediaPedidosComercial(pedidos);
            comercialDTO = comercialMapper.comercialAComercialDTO(comercial, media, sum, sortedTotales, sortedByTotal);
        } else {
            comercialDTO = comercialMapper.comercialDTOtoComercial(comercial);
        }

        model.addAttribute("comercialDTO", comercialDTO);

        return "detalle-comercial";
    }

    /**
     * Maneja las solicitudes GET para mostrar el formulario de creación de un nuevo comercial.
     *
     * @param model El modelo que se utilizará para pasar datos a la vista.
     * @return El nombre de la vista "crear-comercial" que mostrará el formulario de creación de comercial.
     */
    @GetMapping("/comerciales/crear")
    public String crear(Model model) {

        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        return "crear-comercial";
    }

    /**
     * Maneja las solicitudes POST para procesar la creación de un nuevo comercial.
     *
     * @param comercial El objeto Comercial que contiene la información del comercial a ser creado.
     * @return Una redirección a la lista de comerciales después de la creación.
     */
    @PostMapping("/comerciales/crear")
    public String submitCrear(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("comercial", comercial);

            return "crear-comercial";
        }

        comercialService.newComercial(comercial);

        return "redirect:/comerciales";
    }

    /**
     * Maneja las solicitudes GET para mostrar el formulario de edición de un comercial existente.
     *
     * @param model El modelo que se utilizará para pasar datos a la vista.
     * @param id    El identificador del comercial que se va a editar.
     * @return El nombre de la vista "editar-comercial" que mostrará el formulario de edición de comercial.
     */
    @GetMapping("/comerciales/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "editar-comercial";
    }

    /**
     * Maneja las solicitudes POST para procesar la edición de un comercial existente.
     *
     * @param comercial El objeto Comercial que contiene la información editada del comercial.
     * @return Una redirección a la lista de comerciales después de la edición.
     */
    @PostMapping("/comerciales/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("comercial", comercial);

            return "editar-comercial";
        }

        comercialService.replaceComercial(comercial);
        return "redirect:/comerciales";
    }

    /**
     * Maneja las solicitudes POST para procesar la eliminación de un comercial existente.
     *
     * @param id El identificador del comercial que se va a eliminar.
     * @return Una redirección a la lista de comerciales después de la eliminación.
     */
    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        comercialService.deleteComercial(id);

        return new RedirectView("/comerciales");
    }
}
