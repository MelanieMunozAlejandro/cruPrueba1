package com.example.cruprueba.service;

import com.example.cruprueba.entity.Session;
import com.example.cruprueba.entity.Usuarios;
import com.example.cruprueba.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.cruprueba.repository.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AutoService {
    @Autowired
    private UsuarioRepositorio usuariosRepositorio;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SessionRepository sessionRepository;

    public Usuarios authenticateUser(String username, String password) {
        Usuarios user = usuariosRepositorio.findByUserName(username);

        if (usuarioService.hasActiveSession(user.getIdUsuario())) {
            throw new IllegalStateException("Ya tienes una sesión activa.");
        }

        if (user != null && user.getPassword().equals(password)) {
            // Autenticación exitosa
            resetFailedLoginAttempts(user);

            Session session = new Session();
            session.setUsuariosIdUsuario(Math.toIntExact(user.getIdUsuario()));
            session.setFechaIngreso(new Date());
            sessionRepository.save(session);

            usuarioService.activateSession(user.getIdUsuario());

            return user;
        } else {
            incrementFailedLoginAttempts(user);
            checkFailedLoginAttempts(user);
            throw new IllegalArgumentException("Credenciales inválidas.");
        }

    }

    public void logoutUser(Long userId) {
        Session session = sessionRepository.findLastActiveSession(userId);
        if (session != null) {
            session.setFechaCierre(new Date());
            sessionRepository.save(session);
        }
        usuarioService.deactivateSession(userId);
    }

    private void resetFailedLoginAttempts(Usuarios user) {
        if (user != null && user.getFailedLoginAttempts() > 0) {
            user.setFailedLoginAttempts(0);
            user.setStatus("Activo");
            usuariosRepositorio.save(user);
        }
    }

    private void incrementFailedLoginAttempts(Usuarios user) {
        if (user != null) {
            int attempts = user.getFailedLoginAttempts();
            user.setFailedLoginAttempts(attempts + 1);

            if (attempts + 1 >= 3) {
                user.setStatus("Bloqueado");
            }

            usuariosRepositorio.save(user);
        }
    }

    private void checkFailedLoginAttempts(Usuarios user) {
        if (user != null && user.getFailedLoginAttempts() >= 3) {
            user.setStatus("Bloqueado");
            usuariosRepositorio.save(user);
            throw new IllegalStateException("Usuario bloqueado debido a intentos fallidos.");
        }
    }

    public Usuarios registerUser(Usuarios newUser) {
        Long userId = newUser.getIdUsuario();

        if (usuarioService.hasExistingAccount(userId)) {
            throw new IllegalArgumentException("El usuario ya tiene una cuenta registrada.");
        }

        Usuarios registeredUser = usuariosRepositorio.save(newUser);

        return registeredUser;
    }

}
