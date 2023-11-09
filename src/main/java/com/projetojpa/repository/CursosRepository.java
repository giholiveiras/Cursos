package com.projetojpa.repository;

import com.projetojpa.entities.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository  extends JpaRepository <Cursos, Long> {
}

