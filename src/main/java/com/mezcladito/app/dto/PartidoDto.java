package com.mezcladito.app.dto;

import com.mezcladito.app.entidades.Equipo;
import com.mezcladito.app.entidades.Jugador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDto {
    private String id;
    @OneToOne
    private EquipoDto equipo1;
    @OneToOne
    private EquipoDto equipo2;
    @OneToMany
    private List<JugadorDto> elegidos;
}
