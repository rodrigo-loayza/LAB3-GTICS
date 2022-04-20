package pe.edu.pucp.lab3gtics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.pucp.lab3gtics.entity.Responsable;
import pe.edu.pucp.lab3gtics.repository.ResponsableRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("empleados")
public class ResponsableController {

    @Autowired
    ResponsableRepository responsableRepository;

    @GetMapping("/lista")
    public String listaMarcas (Model model){
        model.addAttribute("listaEncargados",responsableRepository.findAll());
        return "responsable/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoEmpleado(){

        return "responsable/new";
    }

    @PostMapping("/guardar")
    public String guardarMarca(Responsable responsable, RedirectAttributes attributes){
        responsableRepository.save(responsable);
        attributes.addFlashAttribute("msg", "Marca creada exitosamente");
        return "redirect:/empleados/lista";
    }

    @GetMapping("/borrar")
    public String borrarMarca(@RequestParam("id") int id, RedirectAttributes attributes){
        Optional<Responsable> optionalResponsable = responsableRepository.findById(id);
        if (optionalResponsable.isPresent()) {
            responsableRepository.deleteById(id);
            attributes.addFlashAttribute("msg2", "Marca borrada exitosamente");
        }
        return "redirect:/empleados/lista";
    }
}


