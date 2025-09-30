package com.spring.drivechecksi.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
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
@Table(name = "mantenimientos")
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "el id es obligatorio")
    private String entregadoPor;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaIngreso;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaSalida;

    @NotBlank(message = "el tipo de servicio es obligatorio")
    private String tipoServicio;

    @Column(length = 1000)
    private String descripcion;

    @Min(value = 1, message = "el kilometraje debe ser mayor a 0")
    private int kilometraje;

    @Column(length = 1000)
    private String observaciones;

    @DecimalMin(value = "0.0", inclusive = false, message = "el costo debe ser mayor a 0")
    private double costoTotal;

    @ManyToOne
    @JoinColumn(name = "taller_nit", nullable = false)
    private Taller taller;

    @ManyToOne
    @JoinColumn(name = "vehiculo_placa", nullable = false)
    private Vehiculo vehiculo;
}
