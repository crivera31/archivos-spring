package com.sprinboot.servicios.app.otros.commons.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nivel_cuenta")
public class NivelCuenta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 11, name = "nom_nivel_cuenta")
	private String nomNivelCuenta;

	@Column(length = 1, name = "abre_nom_nivel_cuenta")
	private String abreNomNivelCuenta;

	public String getAbreNomNivelCuenta() {
		return abreNomNivelCuenta;
	}

	public void setAbreNomNivelCuenta(String abreNomNivelCuenta) {
		this.abreNomNivelCuenta = abreNomNivelCuenta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomNivelCuenta() {
		return nomNivelCuenta;
	}

	public void setNomNivelCuenta(String nomNivelCuenta) {
		this.nomNivelCuenta = nomNivelCuenta;
	}

	private static final long serialVersionUID = 1L;

}
