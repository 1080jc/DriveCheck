package com.spring.drivechecksi.model;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "talleres")
public class Taller {

    @Id
    @Column(nullable = false, unique = true)
    @NotBlank(message = "el nit es obligatorio")
    private String nit;

    @Column(nullable = false)
    @NotBlank(message = "el nombre no puede estar vacio")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "La direccion no puede estar vacia")
    private String direccion;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false, unique = true)
    @Email(message = "correo invalido")
    @NotBlank(message = "el correo es obligatorio")
    private String correo;

    @Column(nullable = false)
    @Size(min = 6, message = "la contraseña debe tener al menos 6 caracteres")
    private String contraseña;
}
