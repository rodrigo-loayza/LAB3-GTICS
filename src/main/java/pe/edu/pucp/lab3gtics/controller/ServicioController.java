package pe.edu.pucp.lab3gtics.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.pucp.lab3gtics.entity.OpcionServicio;
import pe.edu.pucp.lab3gtics.repository.OpcionServicioRepository;
import pe.edu.pucp.lab3gtics.repository.ResponsableRepository;
import pe.edu.pucp.lab3gtics.repository.ServicioRepository;

@Controller
@RequestMapping("servicios")
public class ServicioController {

    @Autowired
    ServicioRepository servicioRepository;

    @Autowired
    OpcionServicioRepository opcionServicioRepository;

    @Autowired
    ResponsableRepository responsableRepository;

    @GetMapping("lista")
    String listaServicios(Model model){
        model.addAttribute("listaServicios",servicioRepository.findAll());
        model.addAttribute("listaServicios1", opcionServicioRepository.findAll());
        return "servicios/lista";
    }

    @GetMapping("new")
    String nuevoServicio(Model model) {
        model.addAttribute("listaEncargados",responsableRepository.findAll());
        return "servicios/newform";
    }

    @PostMapping("/guardar")
    public String guardarServicio(OpcionServicio opcionServicio, RedirectAttributes attributes){
        attributes.addFlashAttribute("msg", "Marca creada exitosamente");
        return "redirect:/servicios/lista";
    }
}
