package com.mezcladito.app.servicios;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.*;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import com.mezcladito.app.entidades.Jugador;
import com.mezcladito.app.repositorios.JugadorRepositorio;

@Service
@RequiredArgsConstructor
@Data
public class JugadorServicio {

	private final JugadorRepositorio jugadorRepositorio;

	public Jugador crear(String nombre,String apellido, int ataque, int defensa, boolean arquero) throws Exception {
		
		Jugador jugador = new Jugador();
		jugador.setNombre(nombre);
		jugador.setApellido(apellido);
		jugador.setAtaque(ataque);
		jugador.setDefensa(defensa);
		jugador.setArquero(arquero);
		jugador.setPromedio((ataque + defensa)/2.0);
		
		return guardar(jugador);
		
	}
	@Transactional
	public void modificar(String id, Jugador jugador) throws Exception{
		jugadorRepositorio.findById(id)
				.map(jugadorJpa -> {
							Optional.ofNullable(jugador.getNombre()).ifPresent(jugadorJpa::setNombre);
							Optional.ofNullable(jugador.getApellido()).ifPresent(jugadorJpa::setApellido);
							Optional.ofNullable(jugador.getAtaque()).ifPresent(jugadorJpa::setAtaque);
							Optional.ofNullable(jugador.getDefensa()).ifPresent(jugadorJpa::setDefensa);
							jugadorJpa.setPromedio((jugador.getAtaque() + jugador.getDefensa())/2);
							return jugadorRepositorio.save(jugadorJpa);
						}).orElseThrow(()-> new ChangeSetPersister.NotFoundException());
		
	}
	@Transactional
	public void eliminar(String id){
		jugadorRepositorio.deleteById(id);
	}
	
	public Jugador buscar(String id)throws Exception {
		Optional<Jugador> respuesta = jugadorRepositorio.findById(id);
		if ( respuesta.isPresent()) {
			return respuesta.get();
		}else {
			throw new Exception("no se encuentra ningun postulante con ese id");
		}
		
	}
	public List<Jugador> listar(){
		return jugadorRepositorio.findAll();
	}
	
	@Transactional
	public Jugador guardar(Jugador jugador) throws Exception {
		validar(jugador);
		return jugadorRepositorio.save(jugador);
		
	}

	private void validar(Jugador jugador) throws Exception {
		if(jugador.getNombre() == null || jugador.getApellido() == null){
			throw new Exception("NOMBRE Y APELLIDO NO PUEDE ESTAR VACÍO!!!");
		}
		if((jugador.getAtaque() < 1 || jugador.getAtaque() > 5)||(jugador.getDefensa() < 1 || jugador.getDefensa() > 5)){
			throw new Exception("TANTO PARA ATAQUE COMO PARA DEFENSA DEBE SER UN NUMERO DEL 1 AL 5!!!");
		}
	}
	@Transactional
	public Jugador activar(String id) throws Exception {
		Jugador jugador = buscar(id);
		jugador.setActivo(true);
		return jugadorRepositorio.save(jugador);

	}
	@Transactional
	public Jugador desactivar(String id) throws Exception {
		Jugador jugador = buscar(id);
		jugador.setActivo(false);
		return jugadorRepositorio.save(jugador);

	}
}
