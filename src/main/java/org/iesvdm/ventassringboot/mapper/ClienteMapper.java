package org.iesvdm.ventassringboot.mapper;

import org.iesvdm.ventassringboot.domain.Cliente;
import org.iesvdm.ventassringboot.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    // @Mapping(source = "comercialesLista", target = "comercialesAsociados")
    @Mapping(source = "pedidosTrimestreIn", target = "pedidosTrimestre")
    @Mapping(source = "pedidosSemestreIn", target = "pedidosSemestre")
    @Mapping(source = "pedidosAnioIn", target = "pedidosAnio")
    @Mapping(source = "pedidosLustroIn", target = "pedidosLustro")
    public ClienteDTO clienteAClienteDTO(Cliente cliente, Map<Integer, Long> comercialesAsociados, Long pedidosTrimestreIn, Long pedidosSemestreIn, Long pedidosAnioIn, Long pedidosLustroIn);

    public Cliente clienteDTOACliente(ClienteDTO clienteDTO);
}
