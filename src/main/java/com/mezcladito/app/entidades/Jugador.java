package com.mezcladito.app.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jugador {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String nombre;
	private String apellido;
	private int ataque;
	private int defensa;
	private boolean arquero;
	private double promedio = (this.ataque + this.defensa) / 2;
	private boolean activo;


}
