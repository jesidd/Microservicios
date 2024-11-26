package com.mc2.controller;

import com.mc2.DTO.ProductoDTO;
import com.mc2.DTO.SaveProductoDTO;
import com.mc2.productoService.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        List<ProductoDTO> productos = productoService.obtenerTodosLosProductos();

        if (productos.isEmpty()) {
            // Si no hay productos, respondemos con un 404 o 204 No Content
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }


    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ProductoDTO obtenerProducto(@PathVariable int id) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        return producto;
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody SaveProductoDTO saveProductoDTO) {
        try {
            ProductoDTO productoDTO = productoService.guardarProducto(saveProductoDTO);
            return new ResponseEntity<>(productoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            // Aquí se captura cualquier excepción y se agrega más detalle a los logs.
            e.printStackTrace(); // Esto te permitirá ver la excepción en la consola.
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    // Eliminar un producto
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable int id) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productoService.eliminarProducto(id);
    }

    //DEIVIS es cagá
    @PutMapping("/{id}")
    public ProductoDTO actualizarProducto(@PathVariable int id, @RequestBody SaveProductoDTO saveProductoDTO) {
        return productoService.actualizarProducto(id, saveProductoDTO);
    }

}

