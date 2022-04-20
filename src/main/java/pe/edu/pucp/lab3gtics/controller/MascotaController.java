package pe.edu.pucp.lab3gtics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.pucp.lab3gtics.repository.MascotaRepository;

@Controller
@RequestMapping("mascota")
public class MascotaController {

    @Autowired
    MascotaRepository mascotaRepository;

    @GetMapping(value = {"", "/lista"})
    public String listarMascotas(Model model) {
//        model.addAttribute("listaMascotas", mascotaRepository.);
    }



}
