package com.ecomerce.ma.usuario.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private int id;
    @Column(name = "nombreUsuario")
    private String nombre;
    @Column(name = "correoUsuario")
    private String correo;
    @Column(name = "contraseñaUsuario")
    private String contrasena;

    public Usuario(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }
}
