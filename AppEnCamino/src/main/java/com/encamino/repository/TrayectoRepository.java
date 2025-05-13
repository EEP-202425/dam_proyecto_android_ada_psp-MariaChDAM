package com.encamino.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.encamino.model.Trayecto;

public interface TrayectoRepository extends JpaRepository<Trayecto, Long> {
	
    Page<Trayecto> findByOrigenAndDestino(String origen, String destino, Pageable pageable);

    Page<Trayecto> findByOrigenAndDestinoAndPrecioLessThanEqual(
    		String origen, String destino, Double precio, Pageable pageable);

    Page<Trayecto> findByOrigenAndDestinoAndFechaAndHoraLessThanEqual(
    		String origen, String destino, 
    		LocalDate fecha, LocalTime hora,
    		Pageable pageable);

    Page<Trayecto> findByOrigenAndDestinoAndFechaAndHoraLessThanEqualAndPrecioLessThanEqual(
        String origen, String destino,
        LocalDate fecha, LocalTime hora,
        Double precio, Pageable pageable
    );
}
