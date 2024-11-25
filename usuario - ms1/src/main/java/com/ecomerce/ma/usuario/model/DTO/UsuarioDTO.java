package com.ecomerce.ma.usuario.model.DTO;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class UsuarioDTO {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;

    public UsuarioDTO(int id, String nombre, String correo, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }
}
