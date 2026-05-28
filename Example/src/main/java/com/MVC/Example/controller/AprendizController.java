package com.MVC.Example.controller;

import com.MVC.Example.model.service.AprendizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.MVC.Example.model.entity.Aprendiz;

import java.util.Arrays;
import java.util.List;

@Controller 
public class AprendizController {

    private final AprendizService aprendizService = new AprendizService();

    @GetMapping("/aprendices") 
    public String listarAprendices(Model model) {
        List<String> aprendices = Arrays.asList("Juan Pérez", "María López", "Carlos Ruiz");
        
        model.addAttribute("titulo", "Lista de Aprendices");
        model.addAttribute("lista", aprendices);
        
        return "aprendices";
    }

    @GetMapping("/nuevo") 
    public String mostrarFormulario(Model model) {
        model.addAttribute("aprendiz", new Aprendiz());
        return "formulario"; // Retorna la vista del formulario [9]
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Aprendiz aprendiz) {
        aprendizService.guardar(aprendiz); // El controlador usa el Modelo [9]
        return "redirect:/aprendices"; // Redirige a la lista actualizada [6]
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        aprendizService.eliminar(id);
        return "redirect:/aprendices";
    }
}
