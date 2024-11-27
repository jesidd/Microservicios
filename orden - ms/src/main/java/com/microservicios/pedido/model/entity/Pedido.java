package com.microservicios.pedido.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity()
@Table(name = "pedidos")
@RequiredArgsConstructor
@Getter @Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int idUsuario;
    int idProduto;
    int cantidad;
    String estado;

    public Pedido(int idUsuario, int idProduto, int cantidad, String estado) {
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
        this.cantidad = cantidad;
        this.estado = estado;
    }

}
