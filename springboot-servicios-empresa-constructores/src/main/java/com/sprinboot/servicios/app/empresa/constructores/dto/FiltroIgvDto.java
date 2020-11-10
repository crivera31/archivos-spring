package com.sprinboot.servicios.app.empresa.constructores.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FiltroIgvDto {

	private Integer idLibro;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date fecha;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date venc;
	private String doc;
	private String numero;
	private String codRuc;
	
	public Integer getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(Integer idLibro) {
		this.idLibro = idLibro;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	public String getCodRuc() {
		return codRuc;
	}
	public void setCodRuc(String codRuc) {
		this.codRuc = codRuc;
	}
	
	
	
}
