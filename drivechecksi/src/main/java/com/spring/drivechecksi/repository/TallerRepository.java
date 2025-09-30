package com.spring.drivechecksi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.drivechecksi.model.Taller;

@Repository
public interface TallerRepository extends JpaRepository<Taller, String> {
    Taller findByNitAndContraseña(String nit, String contraseña);
}
