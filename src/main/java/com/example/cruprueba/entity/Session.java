package com.example.cruprueba.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSession")
    private Long idSession;

    @Column(name = "FechaIngreso", nullable = false)
    private Date fechaIngreso;

    @Column(name = "FechaCierre", nullable = false)
    private Date fechaCierre;

    @Column(name = "usuarios_idUsuasio", nullable = false)
    private Integer usuariosIdUsuario;

    @ManyToOne
    @JoinColumn(name = "usuarios_idUsuasio", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    private Usuarios usuario;
}
