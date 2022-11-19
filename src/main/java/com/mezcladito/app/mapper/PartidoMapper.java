package com.mezcladito.app.mapper;

import com.mezcladito.app.dto.PartidoDto;
import com.mezcladito.app.entidades.Partido;
import org.mapstruct.Mapper;

@Mapper
public interface PartidoMapper {

    Partido partidoDtoToPartido(PartidoDto partidoDto);
    PartidoDto partidoToPartidoDto(Partido partido);
}
