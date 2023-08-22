package com.example.cruprueba.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "rol_usuarios")
public class RolUsuarios {

    @EmbeddedId
    private RolUsuariosId id;

    @ManyToOne
    @MapsId("rolId")
    @JoinColumn(name = "Rol_idRol")
    private Rol rol;

    @ManyToOne
    @MapsId("usuariosId")
    @JoinColumn(name = "usuarios_idUsuasio")
    private Usuarios usuario;
}
