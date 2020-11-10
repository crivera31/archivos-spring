package com.sprinboot.servicios.logistica.commons.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="cont_voucher_ref")
public class VoucherRef implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;	
	
	@Column(name="cod_document")
	private String codDocument;
	
	@Column(name="numero_ref",length=30)
	private String numeroRef;
	
	@Column(name="fechaRef")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date fechaRef;
	
	@Column(name="d_numero_ref",length=30)
	private String dNumeroRef;
	
	
	@Column(name="d_fecha_ref")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date dFechaRef;
	
	@Column(name="estado_ref")
	private Integer estadoRef;
	
	@Column(name="username")
	private String username;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "cont_voucher_id")
	private Voucher voucher;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodDocument() {
		return codDocument;
	}

	public void setCodDocument(String codDocument) {
		this.codDocument = codDocument;
	}

	public String getNumeroRef() {
		return numeroRef;
	}

	public void setNumeroRef(String numeroRef) {
		this.numeroRef = numeroRef;
	}

	public Date getFechaRef() {
		return fechaRef;
	}

	public void setFechaRef(Date fechaRef) {
		this.fechaRef = fechaRef;
	}

	public String getdNumeroRef() {
		return dNumeroRef;
	}

	public void setdNumeroRef(String dNumeroRef) {
		this.dNumeroRef = dNumeroRef;
	}

	public Date getdFechaRef() {
		return dFechaRef;
	}

	public void setdFechaRef(Date dFechaRef) {
		this.dFechaRef = dFechaRef;
	}

	public Integer getEstadoRef() {
		return estadoRef;
	}

	public void setEstadoRef(Integer estadoRef) {
		this.estadoRef = estadoRef;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
