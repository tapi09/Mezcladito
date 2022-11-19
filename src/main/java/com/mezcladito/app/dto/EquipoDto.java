package com.mezcladito.app.dto;

import com.mezcladito.app.entidades.Jugador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDto {

    private String id;
    @OneToMany
    private List<JugadorDto> jugadores;
}
