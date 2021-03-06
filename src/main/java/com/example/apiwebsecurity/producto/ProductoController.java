package com.example.apiwebsecurity.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping()
    public List<Producto>listarTodos(){
        return productoService.listarAll();
    }

    @GetMapping("/{producto_id}")
    @PreAuthorize("hasAnyRole('ROLE_CLIENTE', 'ROLE_ADMIN')")
    public Optional<Producto> findById(@PathVariable Integer producto_id){
        return productoService.findById(producto_id);
    }

    @GetMapping("buscar/{nombre}")
    @PreAuthorize("hasAnyAuthority('producto_write','producto_read')")
    public List<Producto> findByName(@PathVariable String nombre){
        return productoService.findByName(nombre);
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String nuevoProducto(@RequestBody Producto producto){  // solicita los datos x json
        productoService.save(producto);
        return "nuevo Producto guardado!";
    }

    @PutMapping("/guardar")
    @PreAuthorize("hasAuthority('producto:write')")
    public String saveProducto(@ModelAttribute Producto producto){
        productoService.save(producto);
        return "cambio Producto realizado!";
    }

    @DeleteMapping("/borrar/{producto_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void Delete(@PathVariable Integer producto_id){
        productoService.delete(producto_id);
    }

    @GetMapping("/mi-rol")
    public String getMiRol(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getAuthorities().toString();
    }
}

