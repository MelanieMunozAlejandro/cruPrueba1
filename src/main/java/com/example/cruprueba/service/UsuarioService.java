package com.example.cruprueba.service;

import com.example.cruprueba.entity.Persona;
import com.example.cruprueba.entity.Usuarios;
import com.example.cruprueba.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    private UsuarioRepositorio usuariosRepositorio;

    public boolean hasExistingAccount(Long userId) {
        Usuarios existingUser = usuariosRepositorio.findById(userId).orElse(null);
        return existingUser != null;
    }

    public Usuarios createUser(Usuarios user, Persona persona) {
        if (!validateUsername(user.getUserName())) {
            throw new IllegalArgumentException("El nombre de usuario no cumple los requisitos.");
        }

        if (!validatePassword(user.getPassword())) {
            throw new IllegalArgumentException("La contraseña no cumple los requisitos.");
        }

        if (persona.getIdentificacion() == null || persona.getIdentificacion().isEmpty()) {
            throw new IllegalArgumentException("La identificación es requerida.");
        }

        if (!validateIdentification(persona.getIdentificacion())) {
            throw new IllegalArgumentException("La identificación no cumple los requisitos.");
        }

        String originalMail = user.getMail();
        String mail = originalMail;
        int suffix = 1;

        while (usuarioRepositorio.existsByMail(mail)) {
            mail = originalMail + suffix;
            suffix++;
        }

        user.setMail(mail);
        return usuarioRepositorio.save(user);
    }
    private boolean validateUsername(String username) {
        if (!username.matches("^[a-zA-Z0-9]+$")) {
            return false;
        }

        if (usuarioRepositorio.existsByUserName(username)) {
            return false;
        }

        if (!username.matches(".*\\d.*") || !username.matches(".*[A-Z].*")) {
            return false;
        }

        return username.length() >= 8 && username.length() <= 20;
    }

    private boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        if (password.contains(" ")) {
            return false;
        }

        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return false;
        }

        return true;
    }

    private boolean validateIdentification(String identification) {
        if (identification.length() != 10) {
            return false;
        }

        if (!identification.matches("^[0-9]+$")) {
            return false;
        }

        for (int i = 0; i <= identification.length() - 4; i++) {
            if (identification.charAt(i) == identification.charAt(i + 1) &&
                    identification.charAt(i + 1) == identification.charAt(i + 2) &&
                    identification.charAt(i + 2) == identification.charAt(i + 3)) {
                return false;
            }
        }

        return true;
    }

    public boolean hasActiveSession(Long userId) {
        Usuarios user = usuarioRepositorio.findById(userId).orElse(null);
        return user != null && user.getSessionActive() == 'Y';
    }

    public void activateSession(Long userId) {
        Usuarios user = usuarioRepositorio.findById(userId).orElse(null);
        if (user != null) {
            user.setSessionActive('Y');
            usuarioRepositorio.save(user);
        }
    }

    public void deactivateSession(Long userId) {
        Usuarios user = usuarioRepositorio.findById(userId).orElse(null);
        if (user != null) {
            user.setSessionActive('N');
            usuarioRepositorio.save(user);
        }
    }

    public Usuarios updateUser(Usuarios user) {

        return usuarioRepositorio.save(user);
    }

    public void deleteUser(Long userId) {

        usuarioRepositorio.deleteById(userId);
    }

    public Usuarios getUserById(Long userId) {
        return usuarioRepositorio.findById(userId).orElse(null);
    }
}
