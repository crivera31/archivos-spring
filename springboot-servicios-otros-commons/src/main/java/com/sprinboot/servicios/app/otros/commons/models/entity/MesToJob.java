package com.sprinboot.servicios.app.otros.commons.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mes_to_job")
public class MesToJob implements Serializable {

	private static final long serialVersionUID = -2589396108217670401L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 20, name = "nombre_to_job")
	private String nomMesToJob;

	@Column(length = 5, name = "codigo")
	private Integer codigo;

	@Column(length = 1, name = "estado")
	private Integer estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomMesToJob() {
		return nomMesToJob;
	}

	public void setNomMesToJob(String nomMesToJob) {
		this.nomMesToJob = nomMesToJob;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
