package com.example.cruprueba.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Rol",
        indexes = {
                @Index(name = "Rol__IDX", columnList = "idRol")
        })
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol")
    private Long idRol;

    @Column(name = "RolName", nullable = false, length = 50)
    private String rolName;

    public static final String ROL_REVISAR_REGISTROS = "REVISAR_REGISTROS";
}