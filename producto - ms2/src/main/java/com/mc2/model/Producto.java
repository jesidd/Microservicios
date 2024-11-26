package com.mc2.model;


import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 500)
    private String descripcion;
    @Column(nullable = false)
    private double precio;

    public Producto(String nombre, String descripcion, double precio){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    public Producto(){
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id_){
        this.id = id_;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre_){
        this.nombre=nombre_;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public void setDescripcion(String descripcion_){
        this.descripcion=descripcion_;
    }

    public double getPrecio(){
        return this.precio;
    }

    public void setPrecio(double precio_){
        this.precio = precio_;
    }

}
