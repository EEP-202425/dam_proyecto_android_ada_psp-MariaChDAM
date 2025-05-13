package com.encamino.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.encamino.model.Trayecto;
import com.encamino.repository.TrayectoRepository;

@Service
public class TrayectoService {

	@Autowired
	private TrayectoRepository trayectoRepository;

	public List<Trayecto> getAllTrayectos() {
		return trayectoRepository.findAll();
	}

	public Page<Trayecto> getFilteredTrayectos(int page, int size) {
		return trayectoRepository.findAll(PageRequest.of(page, size, 
				Sort.by("fecha").ascending().and(Sort.by("hora").ascending())));

	}

	public Optional<Trayecto> getTrayectoById(Long id) {
		return trayectoRepository.findById(id);
	}

	public Trayecto saveTrayecto(Trayecto trayecto) {
		return trayectoRepository.save(trayecto);
	}

	public void deleteTrayecto(Long id) {
		trayectoRepository.deleteById(id);
	}

	public Page<Trayecto> getBuscarPorOrigenDestino(
			String origen, String destino, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, 
				Sort.by("fecha").ascending().and(Sort.by("hora").ascending()));
		return trayectoRepository.findByOrigenAndDestino(origen, destino, pageable);
	}


	public Page<Trayecto> getBuscarPorOrigenDestinoPrecio(
			String origen, String destino, Double precio, 
			int page,int size) {
		Pageable pageable = PageRequest.of(page, size, 
				Sort.by("fecha").ascending().and(Sort.by("hora").ascending()));
		return trayectoRepository.findByOrigenAndDestinoAndPrecioLessThanEqual
				(origen, destino, precio, pageable);
	}
	
	public Page<Trayecto> getBuscarPorFechaHora(
			String origen, String destino, LocalDate fecha, 
			LocalTime hora, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, 
				Sort.by("fecha").ascending().and(Sort.by("hora").ascending()));
		return trayectoRepository.findByOrigenAndDestinoAndFechaAndHoraLessThanEqual
				(origen, destino, fecha, hora, pageable);
	} 

	public Page<Trayecto> getBuscarTodo(
			String origen, String destino, LocalDate fecha, 
			LocalTime hora, Double precio, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, 
				Sort.by("fecha").ascending().and(Sort.by("hora").ascending()));
		return trayectoRepository.findByOrigenAndDestinoAndFechaAndHoraLessThanEqualAndPrecioLessThanEqual(
				origen, destino, fecha, hora, precio, pageable);
	}
}
