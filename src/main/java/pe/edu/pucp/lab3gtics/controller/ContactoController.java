package pe.edu.pucp.lab3gtics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.pucp.lab3gtics.repository.ContactoRepository;

import java.util.Optional;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
    @Autowired
    ContactoRepository contactoRepository;


    @GetMapping("/listaContacto")
    public String ContactoPorMascota(Model model){
        model.addAttribute("listaContacto", contactoRepository.obtenerContactoMascota());
        return "/contacto/lista";
    }


}
