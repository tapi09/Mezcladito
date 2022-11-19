package com.mezcladito.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JugadorDto {

    private String id;
    private String nombre;
    private String apellido;
    private int ataque;
    private int defensa;
    private double promedio;
    private boolean arquero;
    private boolean activo;


}

