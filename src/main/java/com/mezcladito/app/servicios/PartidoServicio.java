package com.mezcladito.app.servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mezcladito.app.entidades.Equipo;
import com.mezcladito.app.entidades.Jugador;
import com.mezcladito.app.entidades.Partido;
import com.mezcladito.app.repositorios.PartidoRepositorio;

@Service
public class PartidoServicio {

	@Autowired
	private PartidoRepositorio partidoRepositorio;
	
	@Autowired
	private JugadorServicio jugadorServicio;

	@Autowired
	private EquipoServicio equipoServicio;

	@Transactional
	public Partido crearPartido(List<Jugador> elegidos) {
		Equipo equipo1= new Equipo();
		Equipo equipo2= new Equipo();
		for (int i=0; i > elegidos.size(); i++ ){
			if(i % 2 ==0){
				equipo1.getJugadores().add(elegidos.get(i));
			}else{
				equipo2.getJugadores().add(elegidos.get(i));
			}
		}

			Partido partido = new Partido();
		if (!partidoRepositorio.findAll().isEmpty()) {
			Optional<Partido> optional = partidoRepositorio.findAll().stream().findAny();
			if(optional.isPresent()){
				partido = optional.get();
			}
		}
		partido.setEquipo1(equipoServicio.guardar(equipo1));
		partido.setEquipo2(equipoServicio.guardar(equipo2));
		partido.setElegidos(elegidos);
		return partidoRepositorio.save(partido);
	}
	@Transactional
	public void modificarPartido(String id, Equipo equipo1, Equipo equipo2) throws Exception{
		
		Partido partido = buscar(id);
		partido.setEquipo1(equipo1);
		partido.setEquipo2(equipo2);
		
		partidoRepositorio.save(partido);
		

	}
	public Partido buscar(String id) throws Exception{
		Optional<Partido> respuesta= partidoRepositorio.findById(id);
				if(respuesta.isPresent()) {
					return respuesta.get();
					
				}else {
					throw new Exception("no hay partido con ese id");
				}
		
	}
	public List<Partido> listar(){
		return partidoRepositorio.findAll();
		
	}
	@Transactional
	public List<Jugador> elegidos() {
		List<Jugador> jugadores = new ArrayList<>();
		for (Jugador jugador : jugadorServicio.listar()) {
			if(jugador.isActivo()) {
				jugadores.add(jugador);
			}
			Partido partido = new Partido();
			partido.setElegidos(jugadores);
			partidoRepositorio.save(partido);
		
		}
		return jugadores;
	}

}
