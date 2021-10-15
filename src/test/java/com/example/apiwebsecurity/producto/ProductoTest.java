package com.example.apiwebsecurity.producto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductoTest {

    @Test
    public void testConstructor() {
        //Arrange & Act
        Producto actualProducto = new Producto();
        actualProducto.setNombre("Nombre");
        actualProducto.setPrecio(10.0);
        actualProducto.setProducto_id(1);
        actualProducto.setStock(1);

        //Assert
        assertEquals("Nombre", actualProducto.getNombre());
        assertEquals(10.0, actualProducto.getPrecio().doubleValue());
        assertEquals(1, actualProducto.getProducto_id().intValue());
        assertEquals(1, actualProducto.getStock().intValue());
        assertEquals("com.example.apiwebsecurity.producto.Producto{id_Producto=1, nombre='Nombre', precio=10.0, stock=1}",
                actualProducto.toString());
    }

    @Test
    public void testConstructor2() {
        Producto actualProducto = new Producto(1, "Nombre", 10.0, 1);
        actualProducto.setNombre("Nombre");
        actualProducto.setPrecio(10.0);
        actualProducto.setProducto_id(1);
        actualProducto.setStock(1);
        assertEquals("Nombre", actualProducto.getNombre());
        assertEquals(10.0, actualProducto.getPrecio().doubleValue());
        assertEquals(1, actualProducto.getProducto_id().intValue());
        assertEquals(1, actualProducto.getStock().intValue());
        assertEquals("com.example.apiwebsecurity.producto.Producto{id_Producto=1, nombre='Nombre', precio=10.0, stock=1}",
                actualProducto.toString());
    }

    @Test
    public void testConstructor3() {
        Producto actualProducto = new Producto("Nombre", 10.0, 1);
        actualProducto.setNombre("Nombre");
        actualProducto.setPrecio(10.0);
        actualProducto.setProducto_id(1);
        actualProducto.setStock(1);
        assertEquals("Nombre", actualProducto.getNombre());
        assertEquals(10.0, actualProducto.getPrecio().doubleValue());
        assertEquals(1, actualProducto.getProducto_id().intValue());
        assertEquals(1, actualProducto.getStock().intValue());
        assertEquals("com.example.apiwebsecurity.producto.Producto{id_Producto=1, nombre='Nombre', precio=10.0, stock=1}",
                actualProducto.toString());
    }
}
