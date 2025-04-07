package com.libcode.crud.controller;

import com.libcode.crud.model.Estudiante;
import com.libcode.crud.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Vista principal con lista + modal
    @GetMapping
    public String listarEstudiantes(Model model,
                                    @RequestParam(value = "mensaje", required = false) String mensaje) {
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        model.addAttribute("mensaje", mensaje);
        return "estudiantes/estudiantesindex";
    }

    // Guardar estudiante (nuevo o editado)
    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante,
                                    RedirectAttributes redirectAttributes) {
        estudianteRepository.save(estudiante);
        String nombre = estudiante.getNombre() + " " + estudiante.getApellidos();
        redirectAttributes.addAttribute("mensaje", "Modificación exitosa del estudiante " + nombre);
        return "redirect:/estudiantes";
    }

    // Eliminar estudiante
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        estudianteRepository.deleteById(id);
        redirectAttributes.addAttribute("mensaje", "Estudiante eliminado correctamente");
        return "redirect:/estudiantes";
    }
}
