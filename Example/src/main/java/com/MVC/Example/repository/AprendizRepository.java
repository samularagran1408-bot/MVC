package com.MVC.Example.repository;

import com.MVC.Example.model.Aprendiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AprendizRepository extends JpaRepository<Aprendiz, Long> {
    Optional<Aprendiz> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    List<Aprendiz> findByNombreContainingIgnoreCase(String nombre);
}