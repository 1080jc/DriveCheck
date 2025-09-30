package com.spring.drivechecksi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.drivechecksi.model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
    List<Vehiculo> findByPropietarioId(String propietarioId);

    Optional<Vehiculo> findByPlaca(String placa);
}
