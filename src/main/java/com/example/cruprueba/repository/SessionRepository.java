package com.example.cruprueba.repository;

import com.example.cruprueba.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByUsuariosIdUsuarioOrderByFechaIngresoDesc(Integer usuariosIdUsuario);
    @Query("SELECT s FROM Session s WHERE s.usuariosIdUsuario = :userId AND s.fechaCierre IS NULL ORDER BY s.idSession DESC")
    Session findLastActiveSession(@Param("userId") Long userId);
}
