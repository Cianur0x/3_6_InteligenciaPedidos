package org.iesvdm.ventassringboot.mapper;

import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.iesvdm.ventassringboot.dto.ComercialDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ComercialMapper {
    @Mapping(source = "mediaIn", target = "media")
    @Mapping(source = "totalIn", target = "total")
    @Mapping(source = "totalCLientesListaIn", target = "totalCLientesLista")
    @Mapping(source = "pedidosOrdenadosIn", target = "pedidosOrdenados")
    public ComercialDTO comercialAComercialDTO(Comercial comercial, BigDecimal mediaIn, BigDecimal totalIn, List<Map.Entry<Integer, Double>> totalCLientesListaIn, List<Pedido> pedidosOrdenadosIn);

    public ComercialDTO comercialDTOtoComercial(Comercial comercial);

    public Comercial comercialDTOAComercial(ComercialDTO comercialDTO);
}
