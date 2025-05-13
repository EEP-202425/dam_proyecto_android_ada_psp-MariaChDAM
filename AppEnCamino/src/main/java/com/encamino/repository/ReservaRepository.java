package com.encamino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encamino.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
 
}
