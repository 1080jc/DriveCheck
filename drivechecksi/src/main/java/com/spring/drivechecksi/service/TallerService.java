package com.spring.drivechecksi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.drivechecksi.model.Taller;
import com.spring.drivechecksi.repository.TallerRepository;

@Service
public class TallerService {

    @Autowired
    private TallerRepository tallerRepository;

    public void guardar(Taller taller) {
        tallerRepository.save(taller);
    }

    public boolean existePorNit(String nit) {
        return tallerRepository.existsById(nit);
    }

    public Taller buscarPorNitYContraseña(String nit, String contraseña) {
        return tallerRepository.findByNitAndContraseña(nit, contraseña);
    }

    public Taller buscarPorNit(String nit) {
    return tallerRepository.findById(nit).orElse(null);
    }
}
