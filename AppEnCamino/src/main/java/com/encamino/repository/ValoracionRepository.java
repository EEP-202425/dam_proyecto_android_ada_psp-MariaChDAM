package com.encamino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encamino.model.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
    // TODO: añadir queries findAll, findById, save...
}
