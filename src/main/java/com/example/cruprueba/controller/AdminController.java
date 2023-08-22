package com.example.cruprueba.controller;

import com.example.cruprueba.entity.Session;
import com.example.cruprueba.repository.SessionRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("api/v1/admin")
public class AdminController {

    private final SessionRepository  sessionRepository;

    public AdminController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @PreAuthorize("hasRole('REVISAR_REGISTROS')")
    @GetMapping("/login-records")
    public String viewLoginRecords(@RequestParam Long userId, Model model) {
        List<Session> loginRecords = sessionRepository.findByUsuariosIdUsuarioOrderByFechaIngresoDesc(Math.toIntExact(userId));
        model.addAttribute("loginRecords", loginRecords);
        return "login_records";
    }
}
