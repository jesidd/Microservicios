package com.mc2.DAO;

import com.mc2.DAO.IDAO.IProductoDAO;
import com.mc2.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductoDAO implements IProductoDAO {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Producto> obtenerTodos() {
        return entityManager.createQuery("FROM Producto", Producto.class).getResultList();

    }

    @Override
    public Producto obtenerPorId(int id) {
        return entityManager.find(Producto.class, id);

    }

    @Override
    public Producto guardar(Producto producto) {
        if (producto.getId() == 0) {
            entityManager.persist(producto);
            return producto;
        } else {
            return entityManager.merge(producto);
        }
    }

    @Override
    public void eliminar(int id) {
        Producto producto = obtenerPorId(id);
        if (producto != null) {
            entityManager.remove(producto);
        }
    }

    @Override
    public Producto actulizar(Producto producto) {
        Producto productoExistente = entityManager.find(Producto.class, producto.getId());

        if (productoExistente != null) {
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setPrecio(producto.getPrecio());

            return productoExistente;
        } else {
            throw new RuntimeException("Producto con ID " + producto.getId() + " no encontrado.");
        }
    }
}
