package com.microservicios.pedido.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PedidoDTO {
    int id;
    int idUsuario;
    int idProduto;
    int cantidad;
    String estado;
}
