package com.microservicios.pedido.controller;

import com.microservicios.pedido.model.DTO.PedidoDTO;
import com.microservicios.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO createdPedido = pedidoService.createPedido(pedidoDTO);
        return new ResponseEntity<>(createdPedido, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable int id) {
        Optional<PedidoDTO> pedidoDTO = pedidoService.getPedidoById(id);
        return pedidoDTO.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoById(@PathVariable int id) {
        boolean deleted = pedidoService.deletePedidoById(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> updatePedido(
            @PathVariable int id,
            @RequestBody PedidoDTO pedidoDTO) {
        if (pedidoDTO.getId() != id) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<PedidoDTO> updatedPedido = pedidoService.updatePedido(pedidoDTO);
        return updatedPedido.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PedidoDTO> updatePartialPedido(
            @PathVariable int id,
            @RequestBody PedidoDTO partialPedidoDTO) {
        Optional<PedidoDTO> updatedPedido = pedidoService.updatePartialPedido(id, partialPedidoDTO);
        return updatedPedido.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
