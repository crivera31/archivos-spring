package com.sprinboot.servicios.app.otros.commons.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_analisis")
public class TipoAnalisis implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 20, name = "nom_tipo_analisis")
	private String nomTipoAnalisis;

	@Column(length = 1, name = "abre_nom_tipo_analisis")
	private String abreNomTipoAnalisis;

	public String getAbreNomTipoAnalisis() {
		return abreNomTipoAnalisis;
	}

	public void setAbreNomTipoAnalisis(String abreNomTipoAnalisis) {
		this.abreNomTipoAnalisis = abreNomTipoAnalisis;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomTipoAnalisis() {
		return nomTipoAnalisis;
	}

	public void setNomTipoAnalisis(String nomTipoAnalisis) {
		this.nomTipoAnalisis = nomTipoAnalisis;
	}

	private static final long serialVersionUID = 1L;

}
