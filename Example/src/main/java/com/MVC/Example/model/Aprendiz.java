package com.MVC.Example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "aprendiz")
public class Aprendiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El correo es incorrecto")
    @Email(message = "Formato de correo incorrecto")
    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
    
    /**
     * Constructor por defecto
     */
    public Aprendiz() {
        this.fechaRegistro = LocalDateTime.now();
    }
    
    /**
     * Constructor con parámetros
     * @param nombre
     * @param correo
     */
    public Aprendiz(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.fechaRegistro = LocalDateTime.now();
    }
}