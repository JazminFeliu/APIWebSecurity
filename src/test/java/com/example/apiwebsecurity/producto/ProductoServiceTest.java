package com.example.apiwebsecurity.producto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {ProductoService.class})
@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepositoryTest;
    private ProductoService productoServiceTest;

    @BeforeEach
    void setUp() {
        productoServiceTest = new ProductoService(productoRepositoryTest);
    }


    @Test
    public void puedeObtenerProductos() {
        //Arrange
        //dado un productoService y un productoRepository
        Producto pro1 = new Producto(1, "mate", 15.4, 10);
        Producto pro2 = new Producto(2, "termo", 20.7, 15);
        List<Producto> productosEncontrados = List.of(pro1, pro2);


        //Act
        productoRepositoryTest.findAll();
        when(productoServiceTest.listarAll()).thenReturn(productosEncontrados);

        //Assert
        verify(productoRepositoryTest).findAll();
        assertEquals(productosEncontrados, productoRepositoryTest.findAll());
    }

    @Test
    public void puedeObtenerProductoPorId(){
        //Arrange
        Integer id = 1;

        //Act
        productoServiceTest.findById(id);

        //Assert
        verify(productoRepositoryTest).findById(id);
    }

    @Test
    public void puedeObtenerProductoPorNombre(){
        //Arrange
        String nombre = "mate";

        //Act
        productoServiceTest.findByName(nombre);

        //Assert
        verify(productoRepositoryTest).findProductosByNombreContaining(nombre);
    }

    @Test
    void save(){

        //Arrange
        Producto pro = new Producto(1, "mate", 15.4, 10);

        //Act
        productoServiceTest.save(pro);

        //Assert
        ArgumentCaptor<Producto> productoCapto = ArgumentCaptor.forClass(Producto.class);
        verify(productoRepositoryTest).save(productoCapto.capture());
        Producto productoCapturado = productoCapto.getValue();
        assertThat(productoCapturado).isEqualTo(pro);
    }

}