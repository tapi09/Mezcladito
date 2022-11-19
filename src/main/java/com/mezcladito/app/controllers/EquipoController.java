package com.mezcladito.app.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mezcladito.app.dto.PartidoDto;
import com.mezcladito.app.entidades.Partido;
import com.mezcladito.app.mapper.JugadorMapper;
import com.mezcladito.app.mapper.PartidoMapper;
import com.mezcladito.app.servicios.JugadorServicio;
import com.mezcladito.app.servicios.PartidoServicio;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mezcladito.app.entidades.Jugador;
import com.mezcladito.app.servicios.EquipoServicio;
@RestController
@RequestMapping("/equipo")
@RequiredArgsConstructor
public class EquipoController {
	
	private final JugadorServicio jugadorService;
	private final EquipoServicio equipoServicio;
	private final JugadorMapper jugadorMapper;
	private final PartidoServicio partidoServicio;
	private final PartidoMapper mapper;

	@PostMapping("/armarMezcladito")
	public ResponseEntity<PartidoDto> armarMezcladito(){
		List<Jugador>convocados = jugadorService.listar().stream().filter(j-> j.isActivo())
				.sorted(Comparator.comparing(Jugador::getPromedio)
						.thenComparing(Jugador::isArquero).reversed())
				.collect(Collectors.toList());
		Partido partido = partidoServicio.crearPartido(convocados);
		PartidoDto dto = mapper.partidoToPartidoDto(partido);
		return ResponseEntity.ok(dto);

	}

	@PostMapping("/crear")
	public String crear(Model model, @RequestParam List<Jugador> jugadores)throws Exception {
		try {
		equipoServicio.crear(jugadores);
		
		
		return "/";
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
			throw new Exception("error: crear equipo");
		}
		
	}
	@PostMapping("/modificar")
	public String modificar(Model model,String id, @RequestParam List<Jugador> jugadores)throws Exception {
		try {
		equipoServicio.modificar(id, jugadores);
		
		return "/";
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
			throw new Exception("error: crear equipo");
		}
	}
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("equipos", equipoServicio.listar());
		return "listarEquipos.html";
	}

	
	
	

}
