package com.mezcladito.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mezcladito.app.entidades.Partido;
@Repository
public interface PartidoRepositorio extends JpaRepository<Partido, String>{

}
