package pe.edu.pucp.lab3gtics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.pucp.lab3gtics.entity.Mascota;
import pe.edu.pucp.lab3gtics.repository.MascotaRepository;
import pe.edu.pucp.lab3gtics.repository.ServiceRepository;

import java.util.Optional;

@Controller
@RequestMapping("servicio")
public class ServicioController {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    @GetMapping(value = {"", "/lista"})
    public String listarServicios(Model model,
                                  @RequestParam("id") Integer id) {
        Optional<Mascota> optional = mascotaRepository.findById(id);
        if (optional.isPresent()){
            model.addAttribute("listaServicio", serviceRepository.obtenerServicioMascota(id));
            model.addAttribute("mascota",optional.get());
            return "servicio/lista";
        }
        return  "redirect:/mascota/lista";
    }

}
