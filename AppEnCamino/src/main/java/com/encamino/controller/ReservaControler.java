package com.encamino.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.encamino.model.EstadoReserva;
import com.encamino.model.Reserva;
import com.encamino.service.ReservaService;

@RestController
@RequestMapping("/api/reservas")
public class ReservaControler {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/all") 
    public List<Reserva> getAllReservas() {
        return reservaService.getAllReservas();
    }
//    @GetMapping("/pageable")
//    public Page<Reserva> getAllReservasPaged(Pageable pageable) {
//        return reservaService.getAllReservasPaginated(pageable);
//    }

    @GetMapping("/get-{id}")
    public Optional<Reserva> getReservatoById(@PathVariable Long id) {
        return reservaService.getReservaById(id);
    }

    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva) {
        return reservaService.saveReserva(reserva);
    }
    
    @PutMapping("/update-{id}")
    public Optional<Reserva> updateEstado(@PathVariable Long id, @RequestParam EstadoReserva estado) {
        return reservaService.updateEstado(id, estado);
    }

    @DeleteMapping("/delete-{id}")
    public void deleteReserva(@PathVariable Long id) {
    	reservaService.deleteReserva(id);
    }
    
}
