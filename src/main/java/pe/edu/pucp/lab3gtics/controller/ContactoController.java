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
import pe.edu.pucp.lab3gtics.repository.CuentaRepository;
import pe.edu.pucp.lab3gtics.repository.MascotaRepository;


import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    MascotaRepository mascotaRepository;


    @GetMapping(value = {"", "/","/listaContacto"})
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


    @GetMapping("/verMas")
    public String verMasInfo(Model model, @RequestParam("id") int id){
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);
        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            model.addAttribute("mascotaPorContacto",cuentaRepository.obtenerMascotasPorContacto(id));
            model.addAttribute("contacto",cuenta);
            model.addAttribute("id",id);
            return "/contacto/vermas";
        } else {
            return "redirect:/contacto/listaContacto";
        }

//        model.addAttribute("mascotaPorContacto",cuentaRepository.obtenerMascotasPorContacto(id));
//        model.addAttribute("contacto",cuentaRepository.findById(id));
//        model.addAttribute("id",id);
//        return "/contacto/vermas";
    }

    @GetMapping("/delete")
    public String borrarMascota(Model model,
                                 @RequestParam("id") int id,
                                 RedirectAttributes attr) {

        Optional<Mascota> optionalMascota = mascotaRepository.findById(id);
//        Optional<Employees> optionalEmployees = employeesRepository.findById(id);

        if (optionalMascota.isPresent()) {
            mascotaRepository.deleteById(id);
            attr.addFlashAttribute("msg","Mascota borrado exitosamente");
        }
        return "redirect:/contacto/listaContacto";

    }


}
