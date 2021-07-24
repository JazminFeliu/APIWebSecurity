package com.example.apiwebsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiWebSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiWebSecurityApplication.class, args);
        System.out.println("Bienvenido a una API que implementa un CRUD en un DB Relacional que accede con RBAC!!! ");
    }

}
