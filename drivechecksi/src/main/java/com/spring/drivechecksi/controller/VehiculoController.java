package com.spring.drivechecksi.controller;

import java.util.Date;

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
@RequestMapping("/vehiculo")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private PropietarioService propietarioService;

    @GetMapping("/registro")
    public String mostrarFormulario(@RequestParam("propietarioId") String propietarioId, Model model) {
        Vehiculo vehiculo = new Vehiculo();
        Propietario propietario = propietarioService.obtenerPorId(propietarioId);
        vehiculo.setPropietario(propietario);
        model.addAttribute("vehiculo", vehiculo);
        return "vehiculo/registro";
    }

    @PostMapping("/registro")
    public String guardarVehiculo(@Valid @ModelAttribute Vehiculo vehiculo, Model model) {
        if (vehiculoService.buscarPorPlaca(vehiculo.getPlaca()) != null) {
            model.addAttribute("error", "ya existe un vehiculo con esa placa");
            return "vehiculo/registro";
        }
        vehiculoService.guardar(vehiculo);
        Date vencimientoSoat = vehiculoService.calcularVencimiento(vehiculo.getFechaSoat(), 12);
        Date vencimientoTecno = vehiculoService.calcularVencimiento(vehiculo.getFechaTecnomecanica(), 12);
        Date vencimientoMant = vehiculoService.calcularVencimiento(vehiculo.getFechaUltimoMantenimiento(), 6);
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("vencimientoSoat", vencimientoSoat);
        model.addAttribute("vencimientoTecno", vencimientoTecno);
        model.addAttribute("vencimientoMant", vencimientoMant);

        return "vehiculo/resumen";
    }

    @GetMapping("/detalle")
    public String verDetalle(@RequestParam("placa") String placa, Model model) {
        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
        if (vehiculo == null) {
            model.addAttribute("error", "Vehiculo no encontrado");
            return "redirect:/";
        }

        Date soat = vehiculoService.calcularVencimiento(vehiculo.getFechaSoat(), 12);
        Date tecno = vehiculoService.calcularVencimiento(vehiculo.getFechaTecnomecanica(), 12);
        Date mant = vehiculoService.calcularVencimiento(vehiculo.getFechaUltimoMantenimiento(), 6);

        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("vencimientoSoat", soat);
        model.addAttribute("vencimientoTecno", tecno);
        model.addAttribute("vencimientoMant", mant);

        return "vehiculo/detalle";
    }

    @GetMapping("/editar")
    public String mostrarFormularioEdicion(@RequestParam("placa") String placa, Model model) {
        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
        if (vehiculo == null) {
            model.addAttribute("error", "Vehiculo no encontrado");
            return "redirect:/";
        }
        model.addAttribute("vehiculo", vehiculo);
        return "vehiculo/editar";
    }

    @PostMapping("/actualizar")
    public String actualizarVehiculo(@Valid @ModelAttribute Vehiculo vehiculo) {
        vehiculoService.guardar(vehiculo);
        return "redirect:/propietario/panelPropietario?id=" + vehiculo.getPropietario().getId();
    }
}
