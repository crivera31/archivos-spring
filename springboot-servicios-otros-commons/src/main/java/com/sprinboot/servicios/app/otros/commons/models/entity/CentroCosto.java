package com.sprinboot.servicios.app.otros.commons.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "centro_costo")
public class CentroCosto implements Serializable {

	private static final long serialVersionUID = 5325096877016366459L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 19, name = "nombre")
	private String nombre;

	@Column(length = 1, name = "nombre_abreviatura")
	private String nomAbrev;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNomAbrev() {
		return nomAbrev;
	}

	public void setNomAbrev(String nomAbrev) {
		this.nomAbrev = nomAbrev;
	}

}
