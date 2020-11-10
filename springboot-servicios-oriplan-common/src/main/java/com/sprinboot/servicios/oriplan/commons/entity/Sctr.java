package com.sprinboot.servicios.oriplan.commons.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "sctr")
public class Sctr implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	
	@Column(length = 500,name = "tipo_contrato")
	private String tipoContrato;
	
	@Column(name = "sctr_essalud")
	private BigDecimal sctrEssalud;
	
	
	@Column(name = "sctr_pensiones")
	private BigDecimal sctrPensiones;


	@Column(name = "enabled")
	private Integer enabled;
	
	
	



	  

		




	public Integer getId() {
		return id;
	}













	public void setId(Integer id) {
		this.id = id;
	}













	public String getTipoContrato() {
		return tipoContrato;
	}













	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}













	public BigDecimal getSctrEssalud() {
		return sctrEssalud;
	}













	public void setSctrEssalud(BigDecimal sctrEssalud) {
		this.sctrEssalud = sctrEssalud;
	}













	public BigDecimal getSctrPensiones() {
		return sctrPensiones;
	}













	public void setSctrPensiones(BigDecimal sctrPensiones) {
		this.sctrPensiones = sctrPensiones;
	}













	public Integer getEnabled() {
		return enabled;
	}













	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}













	public static long getSerialversionuid() {
		return serialVersionUID;
	}













	private static final long serialVersionUID = 1L;

}
