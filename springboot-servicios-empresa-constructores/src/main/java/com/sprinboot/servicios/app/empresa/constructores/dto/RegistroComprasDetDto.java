package com.sprinboot.servicios.app.empresa.constructores.dto;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class RegistroComprasDetDto {
	
	
    private String codDocumento;
    
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal baseImpOpe;
	
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal baseImpAdq;
    
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal adqSinDer;
	
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal adqNoGrav;
	
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal isc;
	
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal igvBaseImpOpe;
	
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal igvBaseImpAdq;
	
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal igvBaseAdqSinDer;
	
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal otrosTributos;
	
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal valorExportacion;
	
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal exonerado;
	
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal inafecto;
	
    @NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	private BigDecimal total;
	
	private List<RegistroComprasSubDetDto> listcompra;

	public String getCodDocumento() {
		return codDocumento;
	}

	public void setCodDocumento(String codDocumento) {
		this.codDocumento = codDocumento;
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

	public List<RegistroComprasSubDetDto> getListcompra() {
		return listcompra;
	}

	public void setListcompra(List<RegistroComprasSubDetDto> listcompra) {
		this.listcompra = listcompra;
	}

	public BigDecimal getInafecto() {
		return inafecto;
	}

	public void setInafecto(BigDecimal inafecto) {
		this.inafecto = inafecto;
	}

	
	
	
	


}
