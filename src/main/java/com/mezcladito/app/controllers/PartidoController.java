package com.mezcladito.app.controllers;

import java.util.List;

import com.mezcladito.app.servicios.JugadorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mezcladito.app.entidades.Equipo;
import com.mezcladito.app.entidades.Jugador;
import com.mezcladito.app.servicios.PartidoServicio;

@RestController
@RequestMapping("/partido")
@RequiredArgsConstructor
public class PartidoController {
	private final PartidoServicio partidoServicio;

	private final EquipoController equipoController;

	private final JugadorServicio jugadorServicio;

	@PostMapping("/crear")
	public String crearPartido() throws Exception {
		
		return "/";
	}
	@PostMapping("/modificar")
	public String modificarPartido(@RequestParam String id, @RequestParam Equipo equipo1, @RequestParam Equipo equipo2)throws Exception {
		partidoServicio.modificarPartido(id, equipo1, equipo2);
		
		return "/";
	}
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("partidos", partidoServicio.listar());
		
		return "listarPartidos.html";
	}
	@GetMapping("/contar")
	public String contar(Model model) {
		
		
		model.addAttribute("partidos", partidoServicio.listar());
		
		return "listarPartidos.html";
	}
	@GetMapping("/guardarElegidos")
	public String guardarElegidos(Model model) {
		List<Jugador> elegidos = partidoServicio.elegidos();
		model.addAttribute("elegidos", elegidos);
		return "elegidos.html";
	}

	
	
	
}
