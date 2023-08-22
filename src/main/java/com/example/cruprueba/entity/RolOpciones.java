package com.example.cruprueba.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "RolOpciones")
public class RolOpciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOpcion")
    private Long idOpcion;

    @Column(name = "NombreOpcion", nullable = false, length = 50)
    private String nombreOpcion;
}