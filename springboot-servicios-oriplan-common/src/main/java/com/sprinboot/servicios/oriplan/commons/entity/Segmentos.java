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
@Table(name = "segmentos")
public class Segmentos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 500,name = "segmento")
	private String segmento;
	
	@Column(name = "codigo")
	private String codigo;
	

	@Column(name = "enabled")
	private Integer enabled;
	
	




	private static final long serialVersionUID = 1L;






	public Integer getEnabled() {
		return enabled;
	}






	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}






	public String getSegmento() {
		return segmento;
	}






	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}






	public String getCodigo() {
		return codigo;
	}






	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}






	public Integer getId() {
		return id;
	}






	public void setId(Integer id) {
		this.id = id;
	}








	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
