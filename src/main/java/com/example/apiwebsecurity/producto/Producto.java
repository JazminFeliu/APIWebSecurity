package com.example.apiwebsecurity.producto;

import javax.persistence.*;

@Entity
@Table( name = "productos")
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer producto_id;
    private String nombre;
    private Double precio;
    private Integer stock;

    public Producto() {
    }

    public Producto(Integer producto_id, String nombre, Double precio, Integer stock) {
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(String nombre, Double precio, Integer stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Integer getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer id_Producto) {
        this.producto_id = id_Producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "com.example.apiwebsecurity.producto.Producto{" +
                "id_Producto=" + producto_id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
