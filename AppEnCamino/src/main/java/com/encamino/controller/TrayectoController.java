package com.encamino.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.encamino.model.Trayecto;
import com.encamino.service.TrayectoService;

@RestController
@RequestMapping("/api/trayectos")
public class TrayectoController {

	@Autowired
	private TrayectoService trayectoService;

	@GetMapping("/all")
	public List<Trayecto> getAllTrayectos() {
		return trayectoService.getAllTrayectos();
	}
	

	@GetMapping("/pageable")
	public Page<Trayecto> getTrayectos(
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "10") int size
	) {
	    return trayectoService.getFilteredTrayectos(page, size);
	}

	@GetMapping("/get-{id}")
	public Optional<Trayecto> getTrayectoById(@PathVariable Long id) {
		return trayectoService.getTrayectoById(id);
	}

	@PostMapping("/create")
	public Trayecto createTrayecto(@RequestBody Trayecto trayecto) {
		return trayectoService.saveTrayecto(trayecto);
	}

	@DeleteMapping("/delete-{id}")
	public void deleteTrayecto(@PathVariable Long id) {
		trayectoService.deleteTrayecto(id);
	}
	
	
	@GetMapping("/buscar/origen-destino")
	public Page<Trayecto> buscarPorOrigenDestino(
		    @RequestParam String origen,
		    @RequestParam String destino,
		    @RequestParam(defaultValue = "0") int page,
		    @RequestParam(defaultValue = "5") int size
		) {
		    return trayectoService.getBuscarPorOrigenDestino(origen, destino, page, size);
		}

	@GetMapping("/buscar/origen-destino-precio")
	public Page<Trayecto> buscarPorOrigenDestinoPrecio(
			@RequestParam String origen, @RequestParam String destino,
			@RequestParam Double precio, 
			@RequestParam(defaultValue = "0") int page,
		    @RequestParam(defaultValue = "5") int size) {
		return trayectoService.getBuscarPorOrigenDestinoPrecio(origen, destino, precio, page, size);
	}

	@GetMapping("/buscar/fecha-hora")
	public Page<Trayecto> buscarPorFechaHora(
			@RequestParam String origen, @RequestParam String destino,
			@RequestParam String fecha, @RequestParam String hora,
			@RequestParam(defaultValue = "0") int page,
		    @RequestParam(defaultValue = "5") int size) {
		LocalDate fechaLocal = LocalDate.parse(fecha);
		LocalTime horaLocal = LocalTime.parse(hora);
		return trayectoService.getBuscarPorFechaHora(origen, destino, fechaLocal, horaLocal, page, size);
	}

	@GetMapping("/buscar/todo")
	public Page<Trayecto> buscarTodo(
			@RequestParam String origen, @RequestParam String destino,
			@RequestParam String fecha, @RequestParam String hora, 
			@RequestParam Double precio,
			@RequestParam(defaultValue = "0") int page,
		    @RequestParam(defaultValue = "5") int size) {
		LocalDate fechaLocal = LocalDate.parse(fecha);
		LocalTime horaLocal = LocalTime.parse(hora);
		return trayectoService.getBuscarTodo(origen, destino, fechaLocal, horaLocal, precio, page, size);
	}
}
