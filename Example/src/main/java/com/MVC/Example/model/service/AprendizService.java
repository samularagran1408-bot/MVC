package com.MVC.Example.model.service;

import com.MVC.Example.model.entity.Aprendiz;
import com.MVC.Example.model.repository.AprendizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AprendizService {
    
    @Autowired
    private AprendizRepository aprendizRepository;

    public List<Aprendiz> listarAprendices() {
        return aprendizRepository.findAll();
    }

    public void guardar(Aprendiz aprendiz) {
        aprendizRepository.save(aprendiz);
    }

    public Aprendiz buscarPorId(Long id) {
        return aprendizRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        aprendizRepository.deleteById(id);
    }

}
