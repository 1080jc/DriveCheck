package com.spring.drivechecksi.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @Column(nullable = false, unique = true)
    @NotBlank(message = "la placa es obligatoria")
    private String placa;

    @Column(nullable = false)
    @NotBlank(message = "la marca no puede estar vacia")
    private String marca;

    @Column(nullable = false)
    @NotBlank(message = "la linea no puede estar vacia")
    private String linea;

    @Column(nullable = false)
    @Min(value = 1900, message = "ll modelo debe ser mayor a 1900")
    private int modelo;

    @Column(nullable = false)
    @NotBlank(message = "el color no puede estar vacio")
    private String color;

    @Column(nullable = false)
    @NotBlank(message = "la clase de vehiculo no puede estar vacia")
    private String claseVehiculo;

    @Column(nullable = false)
    @NotBlank(message = "el servicio no puede estar vacio")
    private String servicio;

    @Column(nullable = false)
    @NotBlank(message = "el tipo de carroceria no puede estar vacio")
    private String tipoCarroceria;

    @Column(nullable = false)
    @NotBlank(message = "el combustible no puede estar vacio")
    private String combustible;

    @Column(nullable = false)
    @Min(value = 1, message = "la capacidad debe ser mayor a 0")
    private int capacidad;

    @ManyToOne
    @JoinColumn(name = "propietario_id", nullable = false)
    private Propietario propietario;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaSoat;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaTecnomecanica;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaUltimoMantenimiento;
}
