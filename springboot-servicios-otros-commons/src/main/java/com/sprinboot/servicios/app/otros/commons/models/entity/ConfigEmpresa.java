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

import com.sprinboot.servicios.usuario.common.models.entity.Role;

@Entity
@Table(name = "config_empresa")
public class ConfigEmpresa  implements Serializable{
	
	private static final long serialVersionUID = 5325096877016366459L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "obligatorio_clasificacion")
	private Integer obligatorioClasificacion;
	
	@Column(name = "estado")
	private Integer estado;
	
	@Column(name = "ruc")
	private String ruc;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToOne()
	@JoinColumn(name = "periodo_anio_id")
	private PeriodoAnio periodoAnio;

	@OneToOne()
	@JoinColumn(name = "tipo_regimen_id")
	private TipoRegimen tipoRegimen;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getObligatorioClasificacion() {
		return obligatorioClasificacion;
	}

	public void setObligatorioClasificacion(Integer obligatorioClasificacion) {
		this.obligatorioClasificacion = obligatorioClasificacion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	

	public PeriodoAnio getPeriodoAnio() {
		return periodoAnio;
	}

	public void setPeriodoAnio(PeriodoAnio periodoAnio) {
		this.periodoAnio = periodoAnio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoRegimen getTipoRegimen() {
		return tipoRegimen;
	}

	public void setTipoRegimen(TipoRegimen tipoRegimen) {
		this.tipoRegimen = tipoRegimen;
	}
	
	
	
}
