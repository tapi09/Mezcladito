package com.mezcladito.app.mapper;

import com.mezcladito.app.dto.EquipoDto;
import com.mezcladito.app.entidades.Equipo;
import org.mapstruct.Mapper;

@Mapper
public interface EquipoMapper {

    Equipo equipoDtoToEquipo(EquipoDto equipoDto);
    EquipoDto equipoToEquipoDto(Equipo equipo);
}
