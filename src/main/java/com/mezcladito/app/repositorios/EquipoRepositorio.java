package com.mezcladito.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mezcladito.app.entidades.Equipo;

@Repository
public interface EquipoRepositorio extends JpaRepository<Equipo, String>{

}
