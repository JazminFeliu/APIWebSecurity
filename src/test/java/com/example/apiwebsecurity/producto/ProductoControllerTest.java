package com.example.apiwebsecurity.producto;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductoController.class})
@ExtendWith(SpringExtension.class)
public class ProductoControllerTest {
    @Autowired
    private ProductoController productoController;

    @MockBean
    private ProductoService productoService;

    @Test
    public void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: Nothing to assert: the constructed class does not have observers (e.g. getters or public fields).
        //   Add observers (e.g. getters or public fields) to the class.
        //   See https://diff.blue/R002

        new ProductoController(new ProductoService(mock(ProductoRepository.class)));
    }

    @Test
    public void testFindById() throws Exception {
        Producto producto = new Producto();
        producto.setProducto_id(1);
        producto.setStock(1);
        producto.setNombre("Nombre");
        producto.setPrecio(10.0);
        Optional<Producto> ofResult = Optional.<Producto>of(producto);
        when(this.productoService.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/productos/{producto_id}", 1);
        MockMvcBuilders.standaloneSetup(this.productoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"producto_id\":1,\"nombre\":\"Nombre\",\"precio\":10.0,\"stock\":1}"));
    }

    @Test
    public void testFindByName() throws Exception {
        when(this.productoService.findByName(anyString())).thenReturn(new ArrayList<Producto>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/productos/buscar/{nombre}",
                "Nombre");
        MockMvcBuilders.standaloneSetup(this.productoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testFindByName2() throws Exception {
        when(this.productoService.findByName(anyString())).thenReturn(new ArrayList<Producto>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/productos/buscar/{nombre}", "Nombre");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productoController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(this.productoService).delete((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/productos/borrar/{producto_id}",
                1);
        MockMvcBuilders.standaloneSetup(this.productoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetMiRol() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productoController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testListarTodos() throws Exception {
        when(this.productoService.listarAll()).thenReturn(new ArrayList<Producto>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/productos");
        MockMvcBuilders.standaloneSetup(this.productoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testListarTodos2() throws Exception {
        when(this.productoService.listarAll()).thenReturn(new ArrayList<Producto>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/productos");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productoController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testNuevoProducto() throws Exception {
        doNothing().when(this.productoService).save((Producto) any());

        Producto producto = new Producto();
        producto.setProducto_id(1);
        producto.setStock(1);
        producto.setNombre("Nombre");
        producto.setPrecio(10.0);
        String content = (new ObjectMapper()).writeValueAsString(producto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/productos/nuevo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.productoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("nuevo Producto guardado!"));
    }

    @Test
    public void testSaveProducto() throws Exception {
        doNothing().when(this.productoService).save((Producto) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/productos/guardar");
        MockMvcBuilders.standaloneSetup(this.productoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("cambio Producto realizado!"));
    }

    @Test
    public void testSaveProducto2() throws Exception {
        doNothing().when(this.productoService).save((Producto) any());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/api/productos/guardar");
        putResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productoController)
                .build()
                .perform(putResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("cambio Producto realizado!"));
    }
}

