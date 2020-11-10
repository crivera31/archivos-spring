package com.sprinboot.servicios.logistica.commons.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cont_libro")
public class Libro implements Serializable{

private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;	
	
	@Column(name="cod_libro")
	private String codLibro;
	
	@Column(name="nom_libro")
	private String nomLibro;

	@Column(name="estado_libro")
	private Integer estadoLibro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getCodLibro() {
		return codLibro;
	}

	public void setCodLibro(String codLibro) {
		this.codLibro = codLibro;
	}

	public String getNomLibro() {
		return nomLibro;
	}

	public void setNomLibro(String nomLibro) {
		this.nomLibro = nomLibro;
	}

	

	public Integer getEstadoLibro() {
		return estadoLibro;
	}

	public void setEstadoLibro(Integer estadoLibro) {
		this.estadoLibro = estadoLibro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
