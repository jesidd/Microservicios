package com.mc2.productoService;
import com.mc2.DAO.ProductoDAO;
import com.mc2.DTO.ProductoDTO;
import com.mc2.DTO.SaveProductoDTO;
import com.mc2.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoDAO productoDAO;

    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoDAO.obtenerTodos()
                .stream()
                .map(producto -> new ProductoDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getPrecio()
                ))
                .collect(Collectors.toList());
    }

    public ProductoDTO obtenerProductoPorId(int id) {
        Producto producto = productoDAO.obtenerPorId(id);
        if (producto == null) {
            return null;
        }
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio()
        );
    }

    public ProductoDTO guardarProducto(SaveProductoDTO saveProductoDTO) {
        Producto producto = new Producto(
                saveProductoDTO.getNombre(),
                saveProductoDTO.getDescripcion(),
                saveProductoDTO.getPrecio()
        );
        producto = productoDAO.guardar(producto);
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio()
        );
    }

    public void eliminarProducto(int id) {
        productoDAO.eliminar(id);
    }

    public ProductoDTO convertirAProductoDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setPrecio(producto.getPrecio());
        return productoDTO;
    }


    public ProductoDTO actualizarProducto(int id, SaveProductoDTO saveProductoDTO) {
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(saveProductoDTO.getNombre());
        producto.setDescripcion(saveProductoDTO.getDescripcion());
        producto.setPrecio(saveProductoDTO.getPrecio());

        Producto productoActualizado = productoDAO.actulizar(producto);

        return convertirAProductoDTO(productoActualizado);
    }

}
