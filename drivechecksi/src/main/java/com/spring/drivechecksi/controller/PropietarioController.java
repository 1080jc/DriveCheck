package com.spring.drivechecksi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.drivechecksi.model.Propietario;
import com.spring.drivechecksi.model.Vehiculo;
import com.spring.drivechecksi.service.PropietarioService;
import com.spring.drivechecksi.service.VehiculoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/propietario")
public class PropietarioController {

    @Autowired
    private PropietarioService propietarioService;

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("propietario", new Propietario());
        return "propietario/registro";
    }

    @PostMapping("/registro")
    public String registrarPropietario(@Valid @ModelAttribute Propietario propietario, Model model) {
        if (propietarioService.existePorId(propietario.getId())) {
            model.addAttribute("error", "el id ya esta registrado");
            return "propietario/registro";
        }
        propietarioService.guardar(propietario);
        return "redirect:/?registroPropietario=exito";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("propietario", new Propietario());
        return "propietario/login";
    }

    @PostMapping("/login")
    public String LoginPropietario(@ModelAttribute Propietario propietario, Model model) {
        Propietario encontrado = propietarioService.buscarPorIdYContraseña(propietario.getId(), propietario.getContraseña());

        if (encontrado != null) {
            model.addAttribute("propietario", encontrado);
            return "redirect:/propietario/panelPropietario?id=" + encontrado.getId();
        } else {
            model.addAttribute("error", "id o contraseña incorrectos");
            return "propietario/login";
        }
    }

    @GetMapping("/panelPropietario")
    public String mostrarpanelPropietario(@RequestParam("id") String id, Model model) {
        Propietario propietario = propietarioService.obtenerPorId(id);
        List<Vehiculo> vehiculos = vehiculoService.listarPorPropietario(id);

        model.addAttribute("propietario", propietario);
        model.addAttribute("vehiculos", vehiculos);
        return "propietario/panelPropietario";
    }
}
