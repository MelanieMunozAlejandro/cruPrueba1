package com.example.cruprueba.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol_rolOpciones")
public class RolRolOpciones {

    @EmbeddedId
    private RolRolOpcionesId id;

    @ManyToOne
    @MapsId("rolId")
    @JoinColumn(name = "Rol_idRol")
    private Rol rol;

    @ManyToOne
    @MapsId("rolOpcionesId")
    @JoinColumn(name = "RolOpciones_idOpcion")
    private RolOpciones rolOpcion;
}