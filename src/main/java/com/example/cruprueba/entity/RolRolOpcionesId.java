package com.example.cruprueba.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RolRolOpcionesId implements Serializable {

    private Long rolId;
    private Long rolOpcionesId;
}