package com.encamino.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.encamino.model.EstadoReserva;
import com.encamino.model.Reserva;
import com.encamino.model.Usuario;
import com.encamino.repository.ReservaRepository;

@Service
public class ReservaService {

	private final ReservaRepository reservaRepository;

	@Autowired
	public ReservaService(ReservaRepository reservaRepository) {
		this.reservaRepository = reservaRepository;
	}

	public Reserva saveReserva(Reserva reserva) {
		return reservaRepository.save(reserva);
	}

	public List<Reserva> getAllReservas() {
		return reservaRepository.findAll();
	}

//	public Page<Reserva> getAllReservasPaginated(Pageable pageable) {
//		return reservaRepository.findAll(pageable);
//	}

	public Optional<Reserva> getReservaById(Long id) {
		return reservaRepository.findById(id);
	}

	public Optional<Reserva> updateEstado(Long id, EstadoReserva estado) {
		Optional<Reserva> reservaOpt = reservaRepository.findById(id);
		if (reservaOpt.isPresent()) {
			Reserva reserva = reservaOpt.get();
			reserva.setEstado(estado);
			return Optional.of(reservaRepository.save(reserva));
		}
		return Optional.empty();
	}

	public void deleteReserva(Long id) {
		reservaRepository.deleteById(id);
	}
}
