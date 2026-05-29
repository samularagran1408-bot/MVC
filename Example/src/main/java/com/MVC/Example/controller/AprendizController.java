package com.MVC.Example.controller;

import com.MVC.Example.model.Aprendiz;
import com.MVC.Example.service.AprendizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller 
@RequestMapping("/aprendices")
public class AprendizController {

    @Autowired
    private AprendizService aprendizService;

    @GetMapping
    public String listarAprendices(Model model) {
        model.addAttribute("aprendices", aprendizService.obtenerTodos());
        model.addAttribute("nuevoAprendiz", new Aprendiz());
        model.addAttribute("titulo", "Gestión de Aprendices");
        return "lista-aprendices";
    }

    @PostMapping("/guardar")
    public String guardarAprendiz(@Valid @ModelAttribute("nuevoAprendiz") Aprendiz aprendiz, 
                                  BindingResult result, 
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("aprendices", aprendizService.obtenerTodos());
            model.addAttribute("titulo", "Gestión de Aprendices");
            return "lista-aprendices";
        }
        
        if (aprendizService.existePorCorreo(aprendiz.getCorreo())) {
            redirectAttributes.addFlashAttribute("error", "❌ El correo ya está registrado");
            return "redirect:/aprendices";
        }
        
        aprendizService.guardarAprendiz(aprendiz);
        redirectAttributes.addFlashAttribute("success", "✅ Aprendiz registrado exitosamente");
        return "redirect:/aprendices";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return aprendizService.buscarPorId(id).map(aprendiz -> {
            model.addAttribute("aprendiz", aprendiz);
            model.addAttribute("titulo", "Editar Aprendiz");
            return "editar-aprendiz";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("error", "❌ Aprendiz no encontrado");
            return "redirect:/aprendices";
        });
    }
    
    @PostMapping("/actualizar/{id}")
    public String actualizarAprendiz(@PathVariable Long id, 
                                     @Valid @ModelAttribute("aprendiz") Aprendiz aprendiz,
                                     BindingResult result,
                                     RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "editar-aprendiz";
        }
        
        try {
            aprendizService.actualizarAprendiz(id, aprendiz);
            redirectAttributes.addFlashAttribute("success", "✅ Aprendiz actualizado exitosamente");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "❌ " + e.getMessage());
        }
        
        return "redirect:/aprendices";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarAprendiz(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            aprendizService.eliminarAprendiz(id);
            redirectAttributes.addFlashAttribute("success", "✅ Aprendiz eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "❌ Error al eliminar el aprendiz");
        }
        return "redirect:/aprendices";
    }
    
    @GetMapping("/buscar")
    public String buscarAprendices(@RequestParam(required = false) String texto, Model model) {
        if (texto != null && !texto.isEmpty()) {
            model.addAttribute("aprendices", aprendizService.busquedaGeneral(texto));
            model.addAttribute("busqueda", texto);
            model.addAttribute("titulo", "Resultados de búsqueda: " + texto);
        } else {
            model.addAttribute("aprendices", aprendizService.obtenerTodos());
            model.addAttribute("titulo", "Gestión de Aprendices");
        }
        model.addAttribute("nuevoAprendiz", new Aprendiz());
        return "lista-aprendices";
    }
    
    // Endpoint de prueba
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "✅ Controlador funcionando correctamente!";
    }
}