package com.encamino.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.encamino.model.Valoracion;
import com.encamino.repository.ValoracionRepository;

@Service
public class ValoracionService {

    private final ValoracionRepository valoracionRepository;

    @Autowired
    public ValoracionService(ValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    public Valoracion saveValoracion(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }

    public List<Valoracion> getAllValoraciones() {
        return valoracionRepository.findAll();
    }
    
//    public Page<Valoracion> getAllValoracionesPaginated(Pageable pageable) {
//        return valoracionRepository.findAll(pageable);
//    }

    public Optional<Valoracion> getValoracionById(Long id) {
        return valoracionRepository.findById(id);
    }

    public void deleteValoracion(Long id) {
    	valoracionRepository.deleteById(id);
    }
}
