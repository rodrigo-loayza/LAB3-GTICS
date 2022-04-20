package pe.edu.pucp.lab3gtics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.pucp.lab3gtics.entity.Cuenta;
import pe.edu.pucp.lab3gtics.entity.RazaEspecie;
import pe.edu.pucp.lab3gtics.repository.CuentaRepository;
import pe.edu.pucp.lab3gtics.repository.MascotaRepository;
import pe.edu.pucp.lab3gtics.repository.RazaEspecieRepository;

import javax.print.attribute.standard.PresentationDirection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("mascota")
public class MascotaController {

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    RazaEspecieRepository razaEspecieRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @GetMapping(value = {"", "/lista"})
    public String listarMascotas(Model model) {
        model.addAttribute("listaMascotas", mascotaRepository.obtenerListaMascotas());
        return "mascota/lista";
    }

    @PostMapping("/buscar")
    public String filtrarMascotas(Model model, RedirectAttributes attr,
                                  @RequestParam("filtro") String filtro,
                                  @RequestParam("parametro") String parametro) {

        if (parametro.equals("")) {
            attr.addFlashAttribute("msg", "La búsqueda no debe ser vacía.");
            return "redirect:/employee";
        } else {
            model.addAttribute("parametro", parametro);
            model.addAttribute("filtro", filtro);
            parametro = parametro.toLowerCase();

            switch (filtro) {
                case "raza":
                    model.addAttribute("listaMascotas", mascotaRepository.obtenerMascotaPorRaza(parametro));
                    break;
                case "contacto":
                    model.addAttribute("listaMascotas", mascotaRepository.obtenerMascotaPorCuenta(parametro));
                    break;
                case "sexo":
                    model.addAttribute("listaMascotas", mascotaRepository.obtenerMascotaPorSexo(parametro));
                    break;
                default:
                    model.addAttribute("listaMascotas", mascotaRepository.obtenerListaMascotas());
                    break;
            }
        }

        return "mascota/lista";
    }

}
