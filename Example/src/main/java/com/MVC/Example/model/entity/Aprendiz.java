package com.MVC.Example.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Aprendiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    
    private String correo;
}
