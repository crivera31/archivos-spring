package com.sprinboot.servicios.app.empresa.constructores.jsons;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComprobanteRest {

	@JsonProperty("id")
    private Integer id;	
	
	@JsonProperty("numComprobante")
	private Integer numComprobante;
	
	
	@JsonProperty("ruc")
	private String ruc;
	
	@JsonProperty("son")
	private String son;
	
	@JsonProperty("documento")
	private String documento;
	
	@JsonProperty("serieNumero")
	private String serieNumero;
	
	@JsonProperty("detalle")
	private String detalle;
	
	@JsonProperty("fechaDoc")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date fechaDoc;
	
	@JsonProperty("fechaComprobante")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date fechaComprobante;
	
	@JsonProperty("total")
	private BigDecimal total;
	
	@JsonProperty("descuento")
	private BigDecimal descuento;
	
	@JsonProperty("netoPagar")
	private BigDecimal netoPagar;
	
	@JsonProperty("formaPago")
	private String formaPago;

	@JsonProperty("anio")
	private String anio;
	
	@JsonProperty("unidadNegocioId")
	private Integer unidadNegocioId;
	
	@JsonProperty("numCheque")
	private String numCheque;
	
	@JsonProperty("cuentaBanco")
	private String cuentaBanco;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("estadoComprobante")
	private Integer estadoComprobante;
	
	@JsonProperty("tipo_comprobante")
	private String tipo_comprobante;
	
	@JsonProperty("lstComprobanteDetRest")
	private List<ComprobanteDetRest> lstComprobanteDetRest;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumComprobante() {
		return numComprobante;
	}

	public void setNumComprobante(Integer numComprobante) {
		this.numComprobante = numComprobante;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getSon() {
		return son;
	}

	public void setSon(String son) {
		this.son = son;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getSerieNumero() {
		return serieNumero;
	}

	public void setSerieNumero(String serieNumero) {
		this.serieNumero = serieNumero;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFechaDoc() {
		return fechaDoc;
	}

	public void setFechaDoc(Date fechaDoc) {
		this.fechaDoc = fechaDoc;
	}

	public Date getFechaComprobante() {
		return fechaComprobante;
	}

	public void setFechaComprobante(Date fechaComprobante) {
		this.fechaComprobante = fechaComprobante;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getNetoPagar() {
		return netoPagar;
	}

	public void setNetoPagar(BigDecimal netoPagar) {
		this.netoPagar = netoPagar;
	}


	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public Integer getUnidadNegocioId() {
		return unidadNegocioId;
	}

	public void setUnidadNegocioId(Integer unidadNegocioId) {
		this.unidadNegocioId = unidadNegocioId;
	}

	public String getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}

	public String getCuentaBanco() {
		return cuentaBanco;
	}

	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getEstadoComprobante() {
		return estadoComprobante;
	}

	public void setEstadoComprobante(Integer estadoComprobante) {
		this.estadoComprobante = estadoComprobante;
	}

	public String getTipo_comprobante() {
		return tipo_comprobante;
	}

	public void setTipo_comprobante(String tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}

	public List<ComprobanteDetRest> getLstComprobanteDetRest() {
		return lstComprobanteDetRest;
	}

	public void setLstComprobanteDetRest(List<ComprobanteDetRest> lstComprobanteDetRest) {
		this.lstComprobanteDetRest = lstComprobanteDetRest;
	}


	
	
	
	
	
}
