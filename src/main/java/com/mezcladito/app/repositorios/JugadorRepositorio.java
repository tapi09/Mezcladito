package com.mezcladito.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mezcladito.app.entidades.Jugador;
@Repository
public interface JugadorRepositorio extends JpaRepository<Jugador, String> {

}
