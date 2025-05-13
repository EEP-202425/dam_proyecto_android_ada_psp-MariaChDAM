package com.encamino.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Trayecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrayecto;

    private String origen;
    private String destino;
    private LocalDate fecha; // DD/MM/YYYY
    private LocalTime hora; // HH:MM
    private int plazas;
    private double precio;

    @OneToMany(mappedBy = "trayecto", cascade = CascadeType.ALL) 
    @JsonManagedReference("trayecto-reservas")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "trayecto", cascade = CascadeType.ALL) 
    @JsonManagedReference("trayecto-valoraciones")
    private List<Valoracion> valoraciones;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference("usuario-trayectos")
    private Usuario usuario;

    // Getters and setters
    public Long getIdTrayecto() {
        return idTrayecto;
    }

    public void setIdTrayecto(Long idTrayecto) {
        this.idTrayecto = idTrayecto;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
