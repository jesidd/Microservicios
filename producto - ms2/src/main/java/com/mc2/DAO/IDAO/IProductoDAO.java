package com.mc2.DAO.IDAO;

import com.mc2.model.Producto;

import java.util.List;

public interface IProductoDAO {
    List<Producto> obtenerTodos();
    Producto obtenerPorId(int id);
    Producto guardar(Producto producto);
    void eliminar(int id);
    Producto actulizar(Producto producto);
}
