package pe.edu.pucp.lab3gtics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.pucp.lab3gtics.entity.*;
import pe.edu.pucp.lab3gtics.repository.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("servicio")
public class ServicioController {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    MascotaRepository mascotaRepository;
    @Autowired
    ResponsableRepository responsableRepository;
    @Autowired
    OpcionRepository opcionRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    OpcionServicioRepository opcionServicioRepository;

    @GetMapping(value = {"", "/lista"})
    public String listarServicios(Model model,
                                  @RequestParam("id") Integer id) {
        Optional<Mascota> optionalM = mascotaRepository.findById(id);
        Optional<Mascota> optionalR = mascotaRepository.findById(id);

        if (optionalM.isPresent()){
            model.addAttribute("listaServicio", serviceRepository.obtenerServicioMascota(id));
            model.addAttribute("mascota",optionalM.get());
            model.addAttribute("responsable",optionalR.get());


            return "servicio/lista";
        }
        return  "redirect:/mascota/lista";
    }

    @GetMapping("/nuevo")
    public String nuevaServicioFrm(Model model, // en la vista añadir que esta info la del boton
                               @RequestParam("idmascota") Integer idmascota,
                               @RequestParam("idcuenta") String idcuenta) {

        model.addAttribute("listaResponsable", responsableRepository.findAll());
        model.addAttribute("listaOpciones", opcionRepository.findAll());
        model.addAttribute("idmascota", idmascota);
        model.addAttribute("idcuenta", idcuenta);
        return "servicio/nuevo";
    }

    @PostMapping("/save")
    public String nuevoServicio(Servicio servicio,
                                @RequestParam("idopcion") Integer idopcion,
                                @RequestParam("idmascota") Integer idmascota,
                                @RequestParam("idcuenta") Integer idcuenta) {
        servicio.setMascota(mascotaRepository.findById(idmascota).get());
        servicio.setCuenta(cuentaRepository.findById(idcuenta).get());

        serviceRepository.save(servicio);

        OpcionServicio opcionServicio = new OpcionServicio();
        opcionServicio.setOpcion(opcionRepository.findById(idopcion).get());
        opcionServicio.setServicio(servicio);
        opcionServicioRepository.save(opcionServicio);

        return "redirect:/servicio/lista?id="+idmascota;

    }

}
