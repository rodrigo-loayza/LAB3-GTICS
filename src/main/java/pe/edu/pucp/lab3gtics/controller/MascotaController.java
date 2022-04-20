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
import pe.edu.pucp.lab3gtics.entity.Mascota;
import pe.edu.pucp.lab3gtics.entity.RazaEspecie;
import pe.edu.pucp.lab3gtics.repository.CuentaRepository;
import pe.edu.pucp.lab3gtics.repository.MascotaRepository;
import pe.edu.pucp.lab3gtics.repository.RazaEspecieRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        model.addAttribute("filtro", filtro);
        model.addAttribute("parametro", parametro);

        switch (filtro) { // raza, contacto o sexo
            case "raza":
                model.addAttribute("listaOpcionesFiltro", razaEspecieRepository.findAll());
                break;
            case "contacto":
                model.addAttribute("listaOpcionesFiltro", cuentaRepository.findAll());
                break;
            default: // filtrar por sexo por default
                model.addAttribute("listaOpcionesFiltro", Arrays.asList("Masculino", "Femenino"));
                break;
        }
        model.addAttribute("listaMascotas", mascotaRepository.obtenerListaMascotas());


        return "mascota/lista";
    }

    @GetMapping("/nuevo")
    public String nuevaMascota(Model model) {
        //RazaEspecie
        List<RazaEspecie> listaRazaespecie = razaEspecieRepository.findAll();
        model.addAttribute("listaRazaespecie", listaRazaespecie);

        //Marca
        List<Cuenta> listaCuenta = cuentaRepository.findAll();
        model.addAttribute("listaCuenta", listaCuenta);

        return "mascota/nuevo";
    }

    @PostMapping("/save")
    public String guardarMascota(Mascota mascota,
                                    RedirectAttributes attr) {
        if (mascota.getId() == null) {
            attr.addFlashAttribute("msg", "Inventario creado exitosamente.");
        } else {
            attr.addFlashAttribute("msg", "Inventario editado exitosamente.");
        }
        mascotaRepository.save(mascota);
        return "redirect:/mascota";
    }

    @GetMapping(value = {"/edit"})
    public String editarInventario(Model model,
                                   @RequestParam("id") Integer mascotaid) {

        Optional<Mascota> optional = mascotaRepository.findById(mascotaid);

        if (optional.isPresent()) {
            //RazaEspecie
            List<RazaEspecie> listaRazaespecie = razaEspecieRepository.findAll();
            model.addAttribute("listaRazaespecie", listaRazaespecie);

            //Marca
            List<Cuenta> listaCuenta = cuentaRepository.findAll();
            model.addAttribute("listaCuenta", listaCuenta);

            Mascota mascota = optional.get();
            model.addAttribute("mascota", mascota);

            return "mascota/editar";
        } else {
            return "redirect:/mascota/lista";
        }
    }

}
