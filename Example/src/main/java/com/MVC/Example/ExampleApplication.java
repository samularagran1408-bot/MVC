package com.MVC.Example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
        System.out.println("Aplicación MVC iniciada correctamente");
        System.out.println("URL: http://localhost:8080/aprendices");
    }
}