package com.sprinboot.servicios.app.empresa.constructores.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CancelacionDocDto {

	private Integer mes;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date venc;
	private String doc;
	private String numero;
	private BigDecimal monto;
	private BigDecimal monto_inicial;
	private String abrevMoneda;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date fechaDoc;
	private Integer idRegDoc;
	private String codOrigen; 
	private Integer idVoucher;
	private Integer duplicado;
	
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Date getVenc() {
		return venc;
	}
	public void setVenc(Date venc) {
		this.venc = venc;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getAbrevMoneda() {
		return abrevMoneda;
	}
	public void setAbrevMoneda(String abrevMoneda) {
		this.abrevMoneda = abrevMoneda;
	}
	public Date getFechaDoc() {
		return fechaDoc;
	}
	public void setFechaDoc(Date fechaDoc) {
		this.fechaDoc = fechaDoc;
	}
	public Integer getIdRegDoc() {
		return idRegDoc;
	}
	public void setIdRegDoc(Integer idRegDoc) {
		this.idRegDoc = idRegDoc;
	}
	public String getCodOrigen() {
		return codOrigen;
	}
	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}
	public BigDecimal getMonto_inicial() {
		return monto_inicial;
	}
	public void setMonto_inicial(BigDecimal monto_inicial) {
		this.monto_inicial = monto_inicial;
	}
	public Integer getIdVoucher() {
		return idVoucher;
	}
	public void setIdVoucher(Integer idVoucher) {
		this.idVoucher = idVoucher;
	}
	public Integer getDuplicado() {
		return duplicado;
	}
	public void setDuplicado(Integer duplicado) {
		this.duplicado = duplicado;
	}
	
	
	
}
