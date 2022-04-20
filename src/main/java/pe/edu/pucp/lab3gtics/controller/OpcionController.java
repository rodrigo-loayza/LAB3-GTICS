package pe.edu.pucp.lab3gtics.controller;

import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.pucp.lab3gtics.entity.Opcion;
import pe.edu.pucp.lab3gtics.entity.Responsable;
import pe.edu.pucp.lab3gtics.repository.OpcionRepository;

import java.util.Optional;

@Controller
@RequestMapping("opciones")
public class OpcionController{

    @Autowired
    OpcionRepository opcionRepository;

    @GetMapping("lista")
    public String listaOpciones(Model model){
        model.addAttribute("listaOpciones",opcionRepository.findAll());
        return "opciones/lista";
    }

    @GetMapping("/new")
    public String nuevaOpcion(){

        return "opciones/new";
    }

    @GetMapping("/editar")
    public String editarOpcion(@RequestParam("id") int id, Model model){
        Optional<Opcion> optionalOpcion = opcionRepository.findById(id);
        if (optionalOpcion.isPresent()) {
            Opcion opcion= optionalOpcion.get();
            model.addAttribute("opcion", opcion);
            return "opciones/editar";
        } else {
            return "redirect:/opciones/lista";
        }
    }

    @PostMapping("/guardar")
    public String guardarOpcion(Opcion opcion, RedirectAttributes attributes){
        opcionRepository.save(opcion);
        attributes.addFlashAttribute("msg", "Servicio creado exitosamente");
        return "redirect:/opciones/lista";
    }

    @PostMapping("/actualizar")
    public String actualizarMarca(Opcion opcion, RedirectAttributes attributes){
        opcionRepository.save(opcion);
        attributes.addFlashAttribute("msg1", "Servicio editado exitosamente");
        return "redirect:/opciones/lista";
    }

    @GetMapping("/borrar")
    public String borrarOpcion(@RequestParam("id") int id, RedirectAttributes attributes){
        Optional<Opcion> optionalOpciones = opcionRepository.findById(id);
        if (optionalOpciones.isPresent()) {
            opcionRepository.deleteById(id);
            attributes.addFlashAttribute("msg2", "Servicio borrado exitosamente");
        }
        return "redirect:/opciones/lista";
    }


}
