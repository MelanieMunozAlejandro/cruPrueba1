package com.example.cruprueba.controller;

import com.example.cruprueba.entity.Usuarios;
import com.example.cruprueba.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/Auto")
public class autoController {
    @Autowired
    private AutoService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Usuarios authenticatedUser = authService.authenticateUser(username, password);
        if (authenticatedUser != null) {
            // Return a token or session information
            return ResponseEntity.ok("Authentication successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam Long userId) {
        authService.logoutUser(userId);
        return ResponseEntity.ok("Logged out successfully");
    }
}
