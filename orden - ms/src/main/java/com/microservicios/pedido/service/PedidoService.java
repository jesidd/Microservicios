package com.microservicios.pedido.service;

import com.microservicios.pedido.model.DAO.PedidoDAO;
import com.microservicios.pedido.model.DTO.PedidoDTO;
import com.microservicios.pedido.model.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;


    public PedidoDTO createPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = mapToEntity(pedidoDTO);
        Pedido savedPedido = pedidoDAO.create(pedido);
        return mapToDTO(savedPedido);
    }

    public Optional<PedidoDTO> getPedidoById(int id) {
        return pedidoDAO.findById(id)
                .map(this::mapToDTO);
    }

    public boolean deletePedidoById(int id) {
        return pedidoDAO.deleteById(id);
    }

    public Optional<PedidoDTO> updatePedido(PedidoDTO pedidoDTO) {
        Pedido pedido = mapToEntity(pedidoDTO);
        return pedidoDAO.update(pedido)
                .map(this::mapToDTO);
    }

    public Optional<PedidoDTO> updatePartialPedido(int id, PedidoDTO pedidoDTO) {
        Pedido partialPedido = mapToEntity(pedidoDTO);
        return pedidoDAO.updatePartial(id, partialPedido)
                .map(this::mapToDTO);
    }

    private Pedido mapToEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getId());
        pedido.setIdUsuario(pedidoDTO.getIdUsuario());
        pedido.setIdProduto(pedidoDTO.getIdProduto());
        pedido.setCantidad(pedidoDTO.getCantidad());
        pedido.setEstado(pedidoDTO.getEstado());
        return pedido;
    }

    private PedidoDTO mapToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setIdUsuario(pedido.getIdUsuario());
        pedidoDTO.setIdProduto(pedido.getIdProduto());
        pedidoDTO.setCantidad(pedido.getCantidad());
        pedidoDTO.setEstado(pedido.getEstado());
        return pedidoDTO;
    }
}
