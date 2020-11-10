package com.sprinboot.servicios.app.empresa.oriana.jsons;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrosDetRest {
	
	@JsonProperty("codMes")
	 private String codMes;
	
	@JsonProperty("codOrigen")
	 private String codOrigen;
	
	@JsonProperty("numAsiento")
	 private Integer numAsiento;

	@JsonProperty("serie")
	 private String serie;

	@JsonProperty("numero")
	 private String numero;

	@JsonProperty("codRuc")
	private String codRuc;
	
	@JsonProperty("razonSocial")
	private String razonSocial;

	@JsonProperty("fechaEmision")
	private String fechaEmision;
	
	@JsonProperty("fechaVencimiento")
	private String fechaVencimiento;
	
	@JsonProperty("codTipoDoc")
	private String codTipoDoc;
	
	@JsonProperty("comprobantePago")
	private String comprobantePago;
	
	@JsonProperty("numeroRef")
	private String numeroRef;
	
	@JsonProperty("comprobanteRef")
	private String comprobanteRef;
	
	@JsonProperty("fechaRef")
	private String fechaRef;
	
	@JsonProperty("dNumeroRef")
	private String dNumeroRef;
	
	@JsonProperty("dFechaRef")
	private String dFechaRef;	
	
	@JsonProperty("tipoCambio")
	private String tipoCambio;
	
	@JsonProperty("tipoCambioRedondeado")
	private String tipoCambioRedondeado;
	
	@JsonProperty("glosario")
	private String glosario;
	
	@JsonProperty("codDocumentoRef")
	private String codDocumentoRef;
	
	@JsonProperty("moneda")
	private String moneda;
		
	//montos
	@JsonProperty("baseImpOpe")
	private BigDecimal baseImpOpe;
	
	@JsonProperty("baseImpAdq")
	private BigDecimal baseImpAdq;
	
	@JsonProperty("adqSinDer")
	private BigDecimal adqSinDer;
	
	@JsonProperty("adqNoGrav")
	private BigDecimal adqNoGrav;
	
	@JsonProperty("isc")
	private BigDecimal isc;
	
	@JsonProperty("igvBaseImpOpe")
	private BigDecimal igvBaseImpOpe;
	
	@JsonProperty("igvBaseImpAdq")
	private BigDecimal igvBaseImpAdq;
	
	@JsonProperty("igvBaseAdqSinDer")
	private BigDecimal igvBaseAdqSinDer;
	
	@JsonProperty("otrosTributos")
	private BigDecimal otrosTributos;
	
	@JsonProperty("valorExportacion")
	private BigDecimal valorExportacion;
	
	@JsonProperty("exonerado")
	private BigDecimal exonerado;
	
	@JsonProperty("inafecto")
	private BigDecimal inafecto;
    
	@JsonProperty("total")
	private BigDecimal total;

	public String getCodOrigen() {
		return codOrigen;
	}

	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}

	public Integer getNumAsiento() {
		return numAsiento;
	}

	public void setNumAsiento(Integer numAsiento) {
		this.numAsiento = numAsiento;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
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

	public String getCodTipoDoc() {
		return codTipoDoc;
	}

	public void setCodTipoDoc(String codTipoDoc) {
		this.codTipoDoc = codTipoDoc;
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

	public String getFechaRef() {
		return fechaRef;
	}

	public void setFechaRef(String fechaRef) {
		this.fechaRef = fechaRef;
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

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getGlosario() {
		return glosario;
	}

	public void setGlosario(String glosario) {
		this.glosario = glosario;
	}

	public String getCodDocumentoRef() {
		return codDocumentoRef;
	}

	public void setCodDocumentoRef(String codDocumentoRef) {
		this.codDocumentoRef = codDocumentoRef;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
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

	public BigDecimal getInafecto() {
		return inafecto;
	}

	public void setInafecto(BigDecimal inafecto) {
		this.inafecto = inafecto;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getCodMes() {
		return codMes;
	}

	public void setCodMes(String codMes) {
		this.codMes = codMes;
	}

	public String getTipoCambioRedondeado() {
		return tipoCambioRedondeado;
	}

	public void setTipoCambioRedondeado(String tipoCambioRedondeado) {
		this.tipoCambioRedondeado = tipoCambioRedondeado;
	}



	
}
