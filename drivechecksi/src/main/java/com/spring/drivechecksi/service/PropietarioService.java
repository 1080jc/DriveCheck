package com.spring.drivechecksi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.drivechecksi.model.Propietario;
import com.spring.drivechecksi.repository.PropietarioRepository;

@Service
public class PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    public boolean existePorId(String id) {
        return propietarioRepository.existsById(id);
    }

    public void guardar(Propietario propietario) {
        propietarioRepository.save(propietario);
    }

    public Propietario buscarPorIdYContraseña(String id, String contraseña) {
        return propietarioRepository.findByIdAndContraseña(id, contraseña);
    }

    public Propietario obtenerPorId(String id) {
        return propietarioRepository.findById(id).orElse(null);
    }
}
