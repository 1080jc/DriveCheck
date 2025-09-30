package com.spring.drivechecksi.repository;

import com.spring.drivechecksi.model.Propietario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario, String> {
    Propietario findByIdAndContraseña(String id, String contraseña);
}
