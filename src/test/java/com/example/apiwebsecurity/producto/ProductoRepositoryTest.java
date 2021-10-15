package com.example.apiwebsecurity.producto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void shouldReturnProductsIfTheNameIsFound(){
        //Arrange
        String nombre = "mate";
        Producto pro1 = new Producto(1, "mate", 15.4, 10);
        Producto pro2 = new Producto(2, "termo", 20.7, 15);
        productoRepository.save(pro1);
        productoRepository.save(pro2);

        //Act
        List<Producto> productosEncontrados = productoRepository.findProductosByNombreContaining(nombre);

        //Assert
        assertThat(productosEncontrados).isNotEmpty();
        assertThat(productosEncontrados.get(0)).hasFieldOrPropertyWithValue("nombre", "mate");
    }

    @Test
    void shoulNotdReturnProductsIfTheNameIsNotFound(){

        //Arrange
        String nombre = "mate";

        //Act
        List<Producto> productosEncontrados = productoRepository.findProductosByNombreContaining(nombre);

        //Assert
        assertThat(productosEncontrados).isEmpty();

    }
}