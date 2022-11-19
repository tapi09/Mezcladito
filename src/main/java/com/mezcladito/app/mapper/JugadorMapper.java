package com.mezcladito.app.mapper;

import com.mezcladito.app.dto.JugadorDto;
import com.mezcladito.app.entidades.Jugador;
import org.mapstruct.Mapper;

@Mapper
public interface JugadorMapper {

    default Jugador jugadorDtoToJugador(JugadorDto jugadorDto) {
        Jugador jugador = new Jugador();
        jugador.setNombre(jugadorDto.getNombre());
        jugador.setApellido(jugadorDto.getApellido());
        jugador.setAtaque(jugadorDto.getAtaque());
        jugador.setDefensa(jugadorDto.getDefensa());
        jugador.setPromedio((double)(jugadorDto.getAtaque()+ jugadorDto.getDefensa())/2);
        jugador.setArquero(jugadorDto.isArquero());
        jugador.setActivo(jugadorDto.isActivo());
        return jugador;
    }

    JugadorDto jugadorToJugadorDto(Jugador jugador);
}
