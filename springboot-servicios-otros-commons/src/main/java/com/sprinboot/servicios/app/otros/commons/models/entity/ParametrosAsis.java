package com.sprinboot.servicios.app.otros.commons.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.sprinboot.servicios.app.otros.commons.models.entity.Origen;
@Entity
@Table(name = "parametros_asis")
public class ParametrosAsis implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 10, name = "soles")
	private String soles;

	@Column(length = 10, name = "dolares")
	private String dolares;

	@Column(length = 30, name = "descripcion")
	private String descripcion;

	@Column(name = "anio_id")
	private Integer anioId;
	
	@OneToOne()
	@JoinColumn(name = "origen_id")
	private Origen origen;

	private Boolean enabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSoles() {
		return soles;
	}

	public void setSoles(String soles) {
		this.soles = soles;
	}

	public String getDolares() {
		return dolares;
	}

	public void setDolares(String dolares) {
		this.dolares = dolares;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Origen getOrigen() {
		return origen;
	}

	public void setOrigen(Origen origen) {
		this.origen = origen;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
	


	public Integer getAnioId() {
		return anioId;
	}

	public void setAnioId(Integer anioId) {
		this.anioId = anioId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	private static final long serialVersionUID = 1L;

}
