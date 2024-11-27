package com.microservicios.pedido.model.DAO;

import com.microservicios.pedido.model.entity.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class PedidoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Pedido create(Pedido pedido) {
        try {
            entityManager.persist(pedido);
            return pedido;
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<Pedido> findById(int id) {
        Pedido pedido = entityManager.find(Pedido.class, id);
        return Optional.ofNullable(pedido);
    }

    public boolean deleteById(int id) {
        try {
            Pedido pedido = entityManager.find(Pedido.class, id);
            if (pedido != null) {
                entityManager.remove(pedido);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<Pedido> update(Pedido pedido) {
        try {
            Pedido existingPedido = entityManager.find(Pedido.class, pedido.getId());
            if (existingPedido != null) {
                existingPedido.setIdProduto(pedido.getIdProduto());
                existingPedido.setCantidad(pedido.getCantidad());
                existingPedido.setEstado(pedido.getEstado());
                entityManager.merge(existingPedido);
                return Optional.of(existingPedido);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw e;
        }
    }

  //este es con patch deivis
    public Optional<Pedido> updatePartial(int id, Pedido partialPedido) {
        try {
            Pedido existingPedido = entityManager.find(Pedido.class, id);
            if (existingPedido != null) {
                if (partialPedido.getIdUsuario() != 0) existingPedido.setIdUsuario(partialPedido.getIdUsuario());
                if (partialPedido.getIdProduto() != 0) existingPedido.setIdProduto(partialPedido.getIdProduto());
                if (partialPedido.getCantidad() != 0) existingPedido.setCantidad(partialPedido.getCantidad());
                if (partialPedido.getEstado() != null) existingPedido.setEstado(partialPedido.getEstado());
                entityManager.merge(existingPedido);
                return Optional.of(existingPedido);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
