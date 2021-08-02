package com.example.apiwebsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarAll(){
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(Integer producto_id){
        Integer id;
        try {
            id = producto_id;
        }catch (NumberFormatException e){
            id = 0;
        }
        return productoRepository.findById(id);
    }


    public List<Producto> findByName(String nombre) {
        return productoRepository.findProductosByNombreContaining(nombre);
    }

    public void save(Producto producto) {
        if (producto.getProducto_id() != null) {
            Producto productoExistente = productoRepository.findById(producto.getProducto_id()).orElse(null);

            if (productoExistente != null) {
                if (producto.getNombre() != null) productoExistente.setNombre(producto.getNombre());
                if (producto.getPrecio() != null) productoExistente.setPrecio(producto.getPrecio());
                if (producto.getStock() != null) productoExistente.setStock(producto.getStock());

                productoRepository.save(productoExistente);
            }else producto.setProducto_id(null);
        }
        productoRepository.save(producto);
    }


    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }
}
