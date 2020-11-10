package com.sprinboot.servicios.app.empresa.oriana.jsons;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrosCabRest {
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

	@JsonProperty("lstRegistrosDetRest")
	private List<RegistrosDetRest> lstRegistrosDetRest;

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

	public List<RegistrosDetRest> getLstRegistrosDetRest() {
		return lstRegistrosDetRest;
	}

	public void setLstRegistrosDetRest(List<RegistrosDetRest> lstRegistrosDetRest) {
		this.lstRegistrosDetRest = lstRegistrosDetRest;
	}
	
	
	
}
