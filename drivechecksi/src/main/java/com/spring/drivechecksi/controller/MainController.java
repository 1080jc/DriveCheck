package com.spring.drivechecksi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String redirigirInicio() {
        return "index";
    }

    @GetMapping("/index")
    public String mostrarInicio() {
        return "index";
    }
}
