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

import com.spring.drivechecksi.model.Mantenimiento;
import com.spring.drivechecksi.model.Taller;
import com.spring.drivechecksi.model.Vehiculo;
import com.spring.drivechecksi.service.MantenimientoService;
import com.spring.drivechecksi.service.TallerService;
import com.spring.drivechecksi.service.VehiculoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/mantenimiento")
public class MantenimientoController {

    @Autowired
    private MantenimientoService mantenimientoService;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private TallerService tallerService;

    @GetMapping("/registro")
    public String mostrarFormulario(@RequestParam("placa") String placa, @RequestParam("tallerNit") String tallerNit, Model model) {
        Mantenimiento mantenimiento = new Mantenimiento();
        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
        Taller taller = tallerService.buscarPorNit(tallerNit);

        if (vehiculo == null || taller == null) {
            model.addAttribute("error", "vehiculo o taller no encontrado");
            return "redirect:/taller/panelTaller?nit=" + tallerNit;
        }
        mantenimiento.setVehiculo(vehiculoService.buscarPorPlaca(placa));
        mantenimiento.setTaller(tallerService.buscarPorNit(tallerNit));
        model.addAttribute("mantenimiento", mantenimiento);
        return "mantenimiento/registro";
    }

    @PostMapping("/registro")
    public String guardar(@Valid @ModelAttribute Mantenimiento mantenimiento) {
        mantenimientoService.guardar(mantenimiento);
        return "redirect:/taller/panelTaller?nit=" + mantenimiento.getTaller().getNit();
    }

    @GetMapping("/historial")
    public String verHistorial(@RequestParam("placa") String placa, Model model) {
        List<Mantenimiento> lista = mantenimientoService.listarPorVehiculo(placa);
        model.addAttribute("mantenimientos", lista);
        model.addAttribute("placa", placa);

        if (!lista.isEmpty()) {
            model.addAttribute("propietarioId", lista.get(0).getVehiculo().getPropietario().getId());
        }

        return "vehiculo/historial";
    }
}