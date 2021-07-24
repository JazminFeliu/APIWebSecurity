package com.example.apiwebsecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {


    List<Producto> findAll();

    Optional<Producto> findById (Integer producto_id);

    Producto save(Producto producto);





}
