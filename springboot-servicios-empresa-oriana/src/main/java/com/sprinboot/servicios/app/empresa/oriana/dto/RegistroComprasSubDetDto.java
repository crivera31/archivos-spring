package com.sprinboot.servicios.app.empresa.oriana.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class RegistroComprasSubDetDto {
	
    private BigDecimal baseImpOpe;
	
	private BigDecimal baseImpAdq;
	
	private BigDecimal adqSinDer;
	
	private BigDecimal adqNoGrav;
	
	private BigDecimal isc;
	
	private BigDecimal igvBaseImpOpe;
	
	private BigDecimal igvBaseImpAdq;
	
	private BigDecimal igvBaseAdqSinDer;
	
	private BigDecimal otrosTributos;
	
	private BigDecimal valorExportacion;
	
	private BigDecimal inafecto;

	private BigDecimal exonerado;
	private BigDecimal total;
	
	private String codRuc;
	
	private String razonSocial;

	private String fechaEmision;
	
	private String fechaVencimiento;
	
	private String codTipoDoc;
	
	private String serie;

	private String comprobantePago;
	
	private String numeroRef;
	
	private String comprobanteRef;
	
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern ="yyyy-MM-dd")
	private String fechaRef;
	
	private String dNumeroRef;
	
	private String dFechaRef;
	
	private String numVoucher;
	
	private String tipoCambio;
	
	private String codDocumentoRef;
	
	private Integer id;
	
	private Integer numAsiento;
	
	private String codOrigen;
	
	private String moneda;
		
	private String codDocumento;
	
	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getCodDocumentoRef() {
		return codDocumentoRef;
	}

	public void setCodDocumentoRef(String codDocumentoRef) {
		this.codDocumentoRef = codDocumentoRef;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getNumVoucher() {
		return numVoucher;
	}

	public void setNumVoucher(String numVoucher) {
		this.numVoucher = numVoucher;
	}

	public BigDecimal getBaseImpOpe() {
		return baseImpOpe;
	}

	public void setBaseImpOpe(BigDecimal baseImpOpe) {
		this.baseImpOpe = baseImpOpe;
	}

	public BigDecimal getBaseImpAdq() {
		return baseImpAdq;
	}

	public void setBaseImpAdq(BigDecimal baseImpAdq) {
		this.baseImpAdq = baseImpAdq;
	}

	public BigDecimal getAdqSinDer() {
		return adqSinDer;
	}

	public void setAdqSinDer(BigDecimal adqSinDer) {
		this.adqSinDer = adqSinDer;
	}

	public BigDecimal getAdqNoGrav() {
		return adqNoGrav;
	}

	public void setAdqNoGrav(BigDecimal adqNoGrav) {
		this.adqNoGrav = adqNoGrav;
	}

	public BigDecimal getIsc() {
		return isc;
	}

	public void setIsc(BigDecimal isc) {
		this.isc = isc;
	}

	public BigDecimal getIgvBaseImpOpe() {
		return igvBaseImpOpe;
	}

	public void setIgvBaseImpOpe(BigDecimal igvBaseImpOpe) {
		this.igvBaseImpOpe = igvBaseImpOpe;
	}

	public BigDecimal getIgvBaseImpAdq() {
		return igvBaseImpAdq;
	}

	public void setIgvBaseImpAdq(BigDecimal igvBaseImpAdq) {
		this.igvBaseImpAdq = igvBaseImpAdq;
	}

	public BigDecimal getIgvBaseAdqSinDer() {
		return igvBaseAdqSinDer;
	}

	public void setIgvBaseAdqSinDer(BigDecimal igvBaseAdqSinDer) {
		this.igvBaseAdqSinDer = igvBaseAdqSinDer;
	}

	public BigDecimal getOtrosTributos() {
		return otrosTributos;
	}

	public void setOtrosTributos(BigDecimal otrosTributos) {
		this.otrosTributos = otrosTributos;
	}

	public BigDecimal getValorExportacion() {
		return valorExportacion;
	}

	public void setValorExportacion(BigDecimal valorExportacion) {
		this.valorExportacion = valorExportacion;
	}

	public BigDecimal getExonerado() {
		return exonerado;
	}

	public void setExonerado(BigDecimal exonerado) {
		this.exonerado = exonerado;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getCodRuc() {
		return codRuc;
	}

	public void setCodRuc(String codRuc) {
		this.codRuc = codRuc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public void setFechaRef(String fechaRef) {
		this.fechaRef = fechaRef;
	}

	public String getCodTipoDoc() {
		return codTipoDoc;
	}

	public void setCodTipoDoc(String codTipoDoc) {
		this.codTipoDoc = codTipoDoc;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getComprobantePago() {
		return comprobantePago;
	}

	public void setComprobantePago(String comprobantePago) {
		this.comprobantePago = comprobantePago;
	}

	public String getNumeroRef() {
		return numeroRef;
	}

	public void setNumeroRef(String numeroRef) {
		this.numeroRef = numeroRef;
	}

	public String getComprobanteRef() {
		return comprobanteRef;
	}

	public void setComprobanteRef(String comprobanteRef) {
		this.comprobanteRef = comprobanteRef;
	}

	
	public String getdNumeroRef() {
		return dNumeroRef;
	}

	public void setdNumeroRef(String dNumeroRef) {
		this.dNumeroRef = dNumeroRef;
	}

	
	public String getdFechaRef() {
		return dFechaRef;
	}

	public void setdFechaRef(String dFechaRef) {
		this.dFechaRef = dFechaRef;
	}

	public String getFechaRef() {
		return fechaRef;
	}

	public BigDecimal getInafecto() {
		return inafecto;
	}

	public void setInafecto(BigDecimal inafecto) {
		this.inafecto = inafecto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumAsiento() {
		return numAsiento;
	}

	public void setNumAsiento(Integer numAsiento) {
		this.numAsiento = numAsiento;
	}

	public String getCodOrigen() {
		return codOrigen;
	}

	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}

	public String getCodDocumento() {
		return codDocumento;
	}

	public void setCodDocumento(String codDocumento) {
		this.codDocumento = codDocumento;
	}

	
	
	
	

}
