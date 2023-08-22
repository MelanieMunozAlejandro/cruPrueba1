package com.example.cruprueba.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios",
        indexes = {
            @Index(name = "usuarios__IDX", columnList = "idUsuario"),
            @Index(name = "usuarios__IDXv1", columnList = "UserName, Mail")
    })
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "UserName", nullable = false, length = 50)
    private String userName;

    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Column(name = "Mail", unique = true, nullable = false, length = 120)
    private String mail;

    @Column(name = "SessionActive", nullable = false, length = 1)
    private char sessionActive;

    @Column(name = "Persona_idPersona2", nullable = false)
    private Integer personaIdPersona2;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    @Column(name = "FailedLoginAttempts", nullable = false)
    private int failedLoginAttempts;

    @ManyToOne
    @JoinColumn(name = "Persona_idPersona2", referencedColumnName = "idPersona", insertable = false, updatable = false)
    private Persona persona;

    @PrePersist
    protected void onCreate() {
        if (sessionActive != 'Y' && sessionActive != 'N') {
            sessionActive = 'N'; // Default value if not 'Y' or 'N'
        }
    }
    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }
}
