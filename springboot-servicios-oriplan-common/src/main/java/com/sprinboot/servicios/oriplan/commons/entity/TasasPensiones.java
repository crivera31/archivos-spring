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
@Table(name = "tasas_pensiones")
public class TasasPensiones implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "comision")
	private BigDecimal comision;
	
	@Column(name = "prima")
	private BigDecimal prima;
	
	@Column(name = "aporte")
	private BigDecimal aporte;
	
	@Column(name = "aporte_adicional_afp")
	private BigDecimal aporteAdicionalAfp;
	
	@Column(name = "comisionMixta")
	private BigDecimal comisionMixta;
	
	@Column(name = "comisionAnualSaldo")
	private BigDecimal comisionAnualSaldo;
	
	@Column(name = "total")
	private BigDecimal total;
	
	@Column(name = "tope")
	private BigDecimal tope;
	
	@Column(name = "enabled")
	private Integer enabled;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "sistema_pensiones_id")
	//@ManyToOne(optional=false)
	private SistemaPensiones sistemaPensiones;
	
	private static final long serialVersionUID = 1L;


	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	public BigDecimal getPrima() {
		return prima;
	}

	public void setPrima(BigDecimal prima) {
		this.prima = prima;
	}

	public BigDecimal getAporte() {
		return aporte;
	}

	public void setAporte(BigDecimal aporte) {
		this.aporte = aporte;
	}

	public BigDecimal getAporteAdicionalAfp() {
		return aporteAdicionalAfp;
	}

	public void setAporteAdicionalAfp(BigDecimal aporteAdicionalAfp) {
		this.aporteAdicionalAfp = aporteAdicionalAfp;
	}

	public BigDecimal getComisionMixta() {
		return comisionMixta;
	}

	public void setComisionMixta(BigDecimal comisionMixta) {
		this.comisionMixta = comisionMixta;
	}

	public BigDecimal getComisionAnualSaldo() {
		return comisionAnualSaldo;
	}

	public void setComisionAnualSaldo(BigDecimal comisionAnualSaldo) {
		this.comisionAnualSaldo = comisionAnualSaldo;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTope() {
		return tope;
	}

	public void setTope(BigDecimal tope) {
		this.tope = tope;
	}

	public SistemaPensiones getSistemaPensiones() {
		return sistemaPensiones;
	}

	public void setSistemaPensiones(SistemaPensiones sistemaPensiones) {
		this.sistemaPensiones = sistemaPensiones;
	}

	public Integer getEnabled() {
		return enabled;
	}

	
	





}
