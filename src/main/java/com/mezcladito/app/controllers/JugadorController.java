package com.mezcladito.app.controllers;

import com.mezcladito.app.dto.JugadorDto;
import com.mezcladito.app.mapper.JugadorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mezcladito.app.entidades.Jugador;
import com.mezcladito.app.servicios.EquipoServicio;
import com.mezcladito.app.servicios.JugadorServicio;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jugador")
@RequiredArgsConstructor
public class JugadorController {

	private final JugadorServicio jugadorServicio;

	private final EquipoServicio equipoServicio;

	private final JugadorMapper mapper;


	@PostMapping("/guardar")
	public ResponseEntity<JugadorDto> crear(Model model, @RequestBody JugadorDto jugadorDto)
			 {
		try {
			Jugador jugador = jugadorServicio.guardar(mapper.jugadorDtoToJugador(jugadorDto));
			jugadorDto = mapper.jugadorToJugadorDto(jugador);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(jugadorDto, HttpStatus.CREATED);



	}

	@PatchMapping("/modificar/{id}")
	public String modificar(Model model, @RequestBody JugadorDto jugadorDto, @PathVariable("id") String id) throws Exception {
		try {
			Jugador jugador  = mapper.jugadorDtoToJugador(jugadorDto);
			jugadorServicio.modificar(id, jugador);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			throw new Exception(e.getMessage());
		}
		return "/";

	}
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> eliminar(@PathVariable("id") String id){
		jugadorServicio.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/listar")
	public ResponseEntity<List<JugadorDto>> mostrarTodos() {
			List<JugadorDto> jugadores = jugadorServicio.listar().stream()
					.map(jugador -> mapper.jugadorToJugadorDto(jugador))
					.collect(Collectors.toList());
			return ResponseEntity.ok(jugadores);

	}
	@GetMapping("/listarConfirmados")
	public ResponseEntity<List<JugadorDto>> mostrarActivos() {
		List<JugadorDto> jugadores = jugadorServicio.listar().stream().filter(j -> j.isActivo())
				.map(jugador -> mapper.jugadorToJugadorDto(jugador))
				.collect(Collectors.toList());
		return ResponseEntity.ok(jugadores);

	}

	@PostMapping("/seleccionar/{id}")
	public ResponseEntity<JugadorDto> seleccionar( @PathVariable("id") String id) {
		try {
			Jugador jugador = jugadorServicio.activar(id);
			return ResponseEntity.ok(mapper.jugadorToJugadorDto(jugador));
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}
	@PostMapping("/quitarSeleccion/{id}")
	public ResponseEntity<JugadorDto> quitarseleccion( @PathVariable("id") String id){
		try {
		Jugador jugador = jugadorServicio.desactivar(id);
		return ResponseEntity.ok(mapper.jugadorToJugadorDto(jugador));
	}catch (Exception e){
		return ResponseEntity.badRequest().build();
	}
	}

	

}
