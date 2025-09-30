package com.spring.drivechecksi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.drivechecksi.model.Mantenimiento;
import com.spring.drivechecksi.repository.MantenimientoRepository;

@Service
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    public void guardar(Mantenimiento mantenimiento) {
        mantenimientoRepository.save(mantenimiento);
    }

    public List<Mantenimiento> listarPorVehiculo(String placa) {
        return mantenimientoRepository.findByVehiculoPlaca(placa);
    }

}
