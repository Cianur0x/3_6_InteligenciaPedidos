package org.iesvdm.ventassringboot.controller;

import org.iesvdm.ventassringboot.domain.Cliente;
import org.iesvdm.ventassringboot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
// @RequestMapping("/clientes")
public class ClienteController {
    // Field injection is not recommended cuando usamos el @Autowired
    @Autowired
    private ClienteService clienteService;

    /**
     * Maneja las solicitudes GET para mostrar la lista de clientes.
     *
     * @param model El modelo que se utilizará para pasar datos a la vista.
     * @return El nombre de la vista "clientes" que mostrará la lista de clientes.
     */
    @GetMapping({"/clientes", "/clients"})
    public String listar(Model model) {

        List<Cliente> clienteList = clienteService.listAll();
        model.addAttribute("listaClientes", clienteList);

        return "clientes";
    }

    /**
     * Maneja las solicitudes GET para mostrar los detalles de un cliente específico.
     *
     * @param model El modelo que se utilizará para pasar datos a la vista.
     * @param id    El identificador del cliente cuyos detalles se mostrarán.
     * @return El nombre de la vista "detalle-cliente" que mostrará los detalles del cliente.
     */
    @GetMapping("/clientes/{id}")
    public String detalle(Model model, @PathVariable Integer id) {

        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        return "detalle-cliente";
    }

    /**
     * Maneja las solicitudes GET para mostrar el formulario de creación de un nuevo cliente.
     *
     * @param model El modelo que se utilizará para pasar datos a la vista.
     * @return El nombre de la vista "crear-cliente" que mostrará el formulario de creación de cliente.
     */
    @GetMapping("/clientes/crear")
    public String crear(Model model) {

        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "crear-cliente";
    }

    /**
     * Maneja las solicitudes POST para procesar la creación de un nuevo cliente.
     *
     * @param cliente El objeto Cliente que contiene la información del cliente a ser creado.
     * @return Una redirección a la lista de clientes después de la creación.
     */
    @PostMapping("/clientes/crear")
    public RedirectView submitCrear(@ModelAttribute("cliente") Cliente cliente) {

        clienteService.create(cliente);

        return new RedirectView("/clientes");
    }

    /**
     * Maneja las solicitudes GET para la edición de un cliente con el identificador proporcionado.
     *
     * @param model El modelo que se utilizará para pasar datos a la vista.
     * @param id    El identificador del cliente que se va a editar.
     * @return La vista "editar-cliente" que mostrará el formulario de edición del cliente.
     */
    @GetMapping("/clientes/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        return "editar-cliente";
    }

    /**
     * Maneja las solicitudes POST para procesar la edición de un cliente existente.
     *
     * @param cliente El objeto Cliente que contiene la información editada del cliente.
     * @return Una redirección a la lista de clientes después de la edición.
     */
    @PostMapping("/clientes/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("cliente") Cliente cliente) {

        clienteService.replace(cliente);

        return new RedirectView("/clientes");
    }

    /**
     * Maneja las solicitudes POST para procesar la eliminación de un cliente existente.
     *
     * @param id El identificador del cliente que se va a eliminar.
     * @return Una redirección a la lista de clientes después de la eliminación.
     */
    @PostMapping("/clientes/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        clienteService.delete(id);

        return new RedirectView("/clientes");
    }
}
