package com.MVC.Example.service;

import com.MVC.Example.model.Aprendiz;
import com.MVC.Example.repository.AprendizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AprendizService {
    
    @Autowired
    private AprendizRepository aprendizRepository;
    
    public List<Aprendiz> obtenerTodos() {
        List<Aprendiz> aprendices = aprendizRepository.findAll();
        
        /**
         * Si la base de datos está vacía, agrega datos de ejemplo
         */
        if (aprendices.isEmpty()) {
            System.out.println("Base de datos vacía - Agregando datos de ejemplo...");
            aprendizRepository.save(new Aprendiz("Ana García", "ana@ejemplo.com"));
            aprendizRepository.save(new Aprendiz("Luis Pérez", "luis@ejemplo.com"));
            aprendizRepository.save(new Aprendiz("Carolina Mendoza", "carolina@ejemplo.com"));
            aprendices = aprendizRepository.findAll();
        }
        
        return aprendices;
    }
    
    public Aprendiz guardarAprendiz(Aprendiz aprendiz) {
        aprendiz.setFechaRegistro(LocalDateTime.now());
        return aprendizRepository.save(aprendiz);
    }
    
    public Optional<Aprendiz> buscarPorId(Long id) {
        return aprendizRepository.findById(id);
    }
    
    public boolean existePorCorreo(String correo) {
        return aprendizRepository.existsByCorreo(correo);
    }
    
    public Aprendiz actualizarAprendiz(Long id, Aprendiz aprendizActualizado) {
        return aprendizRepository.findById(id).map(aprendiz -> {
            aprendiz.setNombre(aprendizActualizado.getNombre());
            aprendiz.setCorreo(aprendizActualizado.getCorreo());
            return aprendizRepository.save(aprendiz);
        }).orElseThrow(() -> new RuntimeException("Aprendiz no encontrado con ID: " + id));
    }
    
    public void eliminarAprendiz(Long id) {
        aprendizRepository.deleteById(id);
    }
    
    public List<Aprendiz> busquedaGeneral(String texto) {
        return aprendizRepository.findByNombreContainingIgnoreCase(texto);
    }
}