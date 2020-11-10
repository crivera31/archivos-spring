package com.sprinboot.servicios.app.empresa.villasol.dto;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


public class RegistroComprasDto {
	
	//totales 
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
	
	private BigDecimal exonerado;
	
	private BigDecimal inafecto;

	private BigDecimal total;


	private List<RegistroComprasDetDto> listRegistroCompraDet;

	
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

	public BigDecimal getInafecto() {
		return inafecto;
	}

	public void setInafecto(BigDecimal inafecto) {
		this.inafecto = inafecto;
	}

	public List<RegistroComprasDetDto> getListRegistroCompraDet() {
		return listRegistroCompraDet;
	}

	public void setListRegistroCompraDet(List<RegistroComprasDetDto> listRegistroCompraDet) {
		this.listRegistroCompraDet = listRegistroCompraDet;
	}
	
	
	
	
}
