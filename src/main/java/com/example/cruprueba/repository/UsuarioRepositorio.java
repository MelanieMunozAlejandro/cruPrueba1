package com.example.cruprueba.repository;

import com.example.cruprueba.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuarios, Long> {
    Usuarios findByUserName(String username);
    boolean existsByMail(String mail);
    boolean existsByUserName(String userName);

}
