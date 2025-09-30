package com.spring.drivechecksi.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "propietarios")
public class Propietario {

    @Id
    @Column(nullable = false, unique = true)
    @NotBlank(message = "el id es obligatorio")
    private String id;

    @Column(nullable = false)
    @NotBlank(message = "el nombre no puede estar vacio")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "el apellido no puede estar vacio")
    private String apellido;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date f_nacimiento;

    @Column(nullable = false)
    @NotBlank(message = "la direccion no puede estar vacia")
    private String direccion;

    @Column(nullable = false)
    private Long telefono;

    @Column(nullable = false, unique = true)
    @Email(message = "correo invalido")
    @NotBlank(message = "el correo es obligatorio")
    private String correo;

    @Column(nullable = false)
    @Size(min = 6, message = "la contraseña debe tener al menos 6 caracteres")
    private String contraseña;
}
