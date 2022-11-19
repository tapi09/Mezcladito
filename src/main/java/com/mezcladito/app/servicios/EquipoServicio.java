package com.mezcladito.app.servicios;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mezcladito.app.entidades.Equipo;
import com.mezcladito.app.entidades.Jugador;
import com.mezcladito.app.repositorios.EquipoRepositorio;

@Service
public class EquipoServicio {

	@Autowired
	private EquipoRepositorio equipoRepostorio;

	public Equipo buscar(String id) throws Exception {
		Optional<Equipo> respuesta = equipoRepostorio.findById(id);
		if (respuesta.isPresent()) {
			return respuesta.get();
		} else {
			throw new Exception("no se encuentra ningun equipo con ese id");
		}

	}

	@Transactional
	public void crear(List<Jugador> jugadores) throws Exception {
		try {
			Equipo equipo = new Equipo();
			equipo.setJugadores(jugadores);
			equipoRepostorio.save(equipo);
		} catch (Exception e) {
			throw new Exception("error al crear equipo");
		}

	}

	@Transactional
	public void modificar(String id, List<Jugador> jugadores)throws Exception {
		try {
			Equipo equipo= buscar(id);
			
			equipo.setJugadores(jugadores);
			
			equipoRepostorio.save(equipo);
			
		}catch(Exception e) {
			throw new Exception("error al modificar");
		}
	}

	public List<Equipo> listar() {
	equipoRepostorio.findAll();
		return null;
	}
	@Transactional
	public Equipo guardar(Equipo equipo){
		return equipoRepostorio.save(equipo);
	}

}
