package com.spring.drivechecksi.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.drivechecksi.model.Vehiculo;
import com.spring.drivechecksi.repository.VehiculoRepository;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public void guardar(Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> listarPorPropietario(String id) {
        return vehiculoRepository.findByPropietarioId(id);
    }

    public Date calcularVencimiento(Date fechaBase, int meses) {
        if (fechaBase == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaBase);
        calendar.add(Calendar.MONTH, meses);
        return calendar.getTime();
    }

    public Vehiculo buscarPorPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa).orElse(null);
    }

    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }
}
