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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "sistema_pensiones")
public class SistemaPensiones implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100,name = "nombre")
	private String nombre;
	
	@Column(length = 100,name = "codigo")
	private String codigo;
	
	
	@Column(name = "enabled")
	private Integer enabled;
	
	@OneToMany(mappedBy="sistemaPensiones",fetch = FetchType.EAGER,cascade = CascadeType.ALL )
	private List<TasasPensiones> lstTasasPensiones;
	

	
	private static final long serialVersionUID = 1L;

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

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<TasasPensiones> getLstTasasPensiones() {
		return lstTasasPensiones;
	}

	public void setLstTasasPensiones(List<TasasPensiones> lstTasasPensiones) {
		this.lstTasasPensiones = lstTasasPensiones;
	}






}
