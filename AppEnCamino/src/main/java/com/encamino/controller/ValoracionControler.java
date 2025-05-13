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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encamino.model.Valoracion;
import com.encamino.service.ValoracionService;

@RestController
@RequestMapping("/api/valoraciones")
public class ValoracionControler {

    @Autowired
    private ValoracionService valoracionService;

    //TODO:posible quitar todas menos create and delete
    @GetMapping("/all")
    public List<Valoracion> getAllValoraciones() {
        return valoracionService.getAllValoraciones();
    }
   
//    @GetMapping("/pageable")
//    public Page<Valoracion> getAllValoracionesPaged(Pageable pageable) {
//        return valoracionService.getAllValoracionesPaginated(pageable);
//    }

    @GetMapping("/get-{id}")
    public Optional<Valoracion> getValoracionById(@PathVariable Long id) {
        return valoracionService.getValoracionById(id);
    }

    @PostMapping
    public Valoracion createValoracion(@RequestBody Valoracion valoracion) {
        return valoracionService.saveValoracion(valoracion);
    }

    @DeleteMapping("/delete-{id}")
    public void deleteValoracion(@PathVariable Long id) {
       valoracionService.deleteValoracion(id);
    }
}
