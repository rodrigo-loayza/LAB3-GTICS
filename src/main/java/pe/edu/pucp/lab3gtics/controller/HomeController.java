package pe.edu.pucp.lab3gtics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping(value = {"", "/"})
    public String home() {
        return "index";
    }

}
