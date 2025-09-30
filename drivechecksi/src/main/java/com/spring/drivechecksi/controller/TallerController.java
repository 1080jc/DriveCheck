package com.spring.drivechecksi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.drivechecksi.model.Taller;
import com.spring.drivechecksi.model.Vehiculo;
import com.spring.drivechecksi.service.TallerService;
import com.spring.drivechecksi.service.VehiculoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/taller")
public class TallerController {

    @Autowired
    private TallerService tallerService;

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("taller", new Taller());
        return "taller/registro";
    }

    @PostMapping("/registro")
    public String registrarTaller(@Valid @ModelAttribute Taller taller, Model model) {
        if (tallerService.existePorNit(taller.getNit())) {
            model.addAttribute("error", "el nit ya esta registrado");
            return "taller/registro";
        }
        tallerService.guardar(taller);
        return "redirect:/?registroTaller=exito";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("taller", new Taller());
        return "taller/login";
    }

    @PostMapping("/login")
    public String loginTaller(@ModelAttribute Taller taller, Model model) {
        Taller encontrado = tallerService.buscarPorNitYContraseña(taller.getNit(), taller.getContraseña());
        if (encontrado != null) {
            model.addAttribute("taller", encontrado);
            return "redirect:/taller/panelTaller?nit=" + encontrado.getNit();
        } else {
            model.addAttribute("error", "nit o contraseña incorrectos");
            return "taller/login";
        }
    }

    @GetMapping("/panelTaller")
    public String mostrarpanelTaller(@RequestParam("nit") String nit, Model model) {
        model.addAttribute("taller", tallerService.buscarPorNit(nit));
        return "taller/panelTaller";
    }

    @PostMapping("/buscar")
    public String buscarVehiculo(@RequestParam("placa") String placa, @RequestParam("nit") String nit, Model model) {
        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
        Taller taller = tallerService.buscarPorNit(nit);
        if (vehiculo != null) {
            model.addAttribute("vehiculo", vehiculo);
            model.addAttribute("taller", taller);
            return "taller/vehiculo_detalle";
        } else {
            model.addAttribute("error", "vehiculo no encontrado");
            model.addAttribute("taller", taller);
            return "taller/panelTaller";
        }
    }
}
