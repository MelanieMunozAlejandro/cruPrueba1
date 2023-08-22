package com.example.cruprueba.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersona")
    private Long idPersona;

    @Column(name = "Nombres", nullable = false)
    private String nombres;

    @Column(name = "Apellidos", nullable = false)
    private String apellidos;

    @Column(name = "Identificacion", nullable = false)
    private String identificacion;

    @Column(name = "FechaNacimiento", nullable = false)
    private Date fechaNacimiento;
}
