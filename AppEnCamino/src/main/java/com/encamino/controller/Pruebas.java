package com.encamino.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Pruebas {

	@GetMapping("/prueba")
	public String pruebas() {
		return "API Encamino is working :)";
	}
}
