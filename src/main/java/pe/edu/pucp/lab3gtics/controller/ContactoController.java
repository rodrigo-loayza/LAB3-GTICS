package pe.edu.pucp.lab3gtics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.pucp.lab3gtics.entity.Cuenta;
import pe.edu.pucp.lab3gtics.repository.CuentaRepository;


import java.util.Optional;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
    @Autowired
    CuentaRepository cuentaRepository;


    @GetMapping("/listaContacto")
    public String ContactoPorMascota(Model model){
        model.addAttribute("listaContacto", cuentaRepository.obtenerContactoMascota());
        return "/contacto/lista";
    }

    @GetMapping("/newContacto")
    public String nuevoContactoForm() {

        return "/contacto/newContacto";
    }

    @PostMapping("/save")
    public String guardarContacto(Cuenta cuenta, RedirectAttributes attr) {
        if (cuenta.getId()== null) {
            attr.addFlashAttribute("msg", "Contacto creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Contacto actualizado exitosamente");
        }
        cuentaRepository.save(cuenta);
        return "redirect:/contacto/listaContacto";

    }


}
