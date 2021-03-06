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
import pe.edu.pucp.lab3gtics.entity.Cuenta;
import pe.edu.pucp.lab3gtics.entity.Mascota;
import pe.edu.pucp.lab3gtics.entity.RazaEspecie;
import pe.edu.pucp.lab3gtics.repository.CuentaRepository;
import pe.edu.pucp.lab3gtics.repository.MascotaRepository;
import pe.edu.pucp.lab3gtics.repository.RazaEspecieRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        if (parametro.equals("") || filtro.equals("")) {
            attr.addFlashAttribute("msgBuscar", "La búsqueda no debe ser vacía y debe seleccionar un filtro.");
            return "redirect:/mascota/lista";
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

    @GetMapping("/nuevo")
    public String nuevaMascota(Model model) {
        List<RazaEspecie> listaRazaespecie = razaEspecieRepository.findAll();
        model.addAttribute("listaRazaespecie", listaRazaespecie);
        List<Cuenta> listaCuenta = cuentaRepository.findAll();
        model.addAttribute("listaCuenta", listaCuenta);
        return "mascota/nuevo";
    }

    @PostMapping("/save")
    public String guardarMascota(Mascota mascota,
                                 RedirectAttributes attr) {
        if (mascota.getId() == null) {
            attr.addFlashAttribute("msg", "Mascota creada exitosamente.");
        } else {
            attr.addFlashAttribute("msg", "Mascota editada exitosamente.");
        }
        mascotaRepository.save(mascota);
        return "redirect:/mascota";
    }

    @GetMapping(value = {"/edit"})
    public String editarInventario(Model model,
                                   @RequestParam("id") Integer mascotaid) {

        Optional<Mascota> optional = mascotaRepository.findById(mascotaid);

        if (optional.isPresent()) {
            List<RazaEspecie> listaRazaespecie = razaEspecieRepository.findAll();
            model.addAttribute("listaRazaespecie", listaRazaespecie);

            List<Cuenta> listaCuenta = cuentaRepository.findAll();
            model.addAttribute("listaCuenta", listaCuenta);

            Mascota mascota = optional.get();
            model.addAttribute("mascota", mascota);

            return "mascota/editar";
        } else {
            return "redirect:/mascota/lista";
        }
    }

    @GetMapping("/delete")
    public String borrarEmpleado(Model model,
                                 @RequestParam("id") int id,
                                 RedirectAttributes attr) {

        Optional<Mascota> optMascota = mascotaRepository.findById(id);
        if (optMascota.isPresent()) {
            mascotaRepository.deleteById(id);
            attr.addFlashAttribute("msgDel", "Mascota borrada exitosamente");
        }
        return "redirect:/mascota/lista";
    }

}
