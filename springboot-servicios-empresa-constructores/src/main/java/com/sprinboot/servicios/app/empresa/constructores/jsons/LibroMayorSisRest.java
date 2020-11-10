package com.sprinboot.servicios.app.empresa.constructores.jsons;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibroMayorSisRest {

	@JsonProperty("origen")
	private String origen ; 
	
	@JsonProperty("codMes")
	private String codMes;

	@JsonProperty("numVoucher")
	private Integer numVoucher;

	@JsonProperty("cuenta")
	private String cuenta;
	
	@JsonProperty("debito")
	private BigDecimal debito;
	
	@JsonProperty("credito")
	private BigDecimal credito;
	
	@JsonProperty("saldo")
	private BigDecimal saldo;
	
	@JsonProperty("moneda")
	private String moneda;
	
	@JsonProperty("tipoCambio")
	private String tipoCambio;
	
	@JsonProperty("fechaAsiento")
	private String fechaAsiento;
  
	@JsonProperty("concepto")
	private String concepto;
	
	@JsonProperty("codRuc")
	private String codRuc;
	
	@JsonProperty("razonSocial")
	private String razonSocial;
	
	@JsonProperty("codDocumento")
	private String codDocumento;
	
	@JsonProperty("serieNumero")
	private String serieNumero;
	
	@JsonProperty("fechaEmision")
	private String fechaEmision;
	
	@JsonProperty("fechaVencimiento")
	private String fechaVencimiento;

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Integer getNumVoucher() {
		return numVoucher;
	}

	public void setNumVoucher(Integer numVoucher) {
		this.numVoucher = numVoucher;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public BigDecimal getDebito() {
		return debito;
	}

	public void setDebito(BigDecimal debito) {
		this.debito = debito;
	}

	public BigDecimal getCredito() {
		return credito;
	}

	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getFechaAsiento() {
		return fechaAsiento;
	}

	public void setFechaAsiento(String fechaAsiento) {
		this.fechaAsiento = fechaAsiento;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
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

	public String getCodDocumento() {
		return codDocumento;
	}

	public void setCodDocumento(String codDocumento) {
		this.codDocumento = codDocumento;
	}

	public String getSerieNumero() {
		return serieNumero;
	}

	public void setSerieNumero(String serieNumero) {
		this.serieNumero = serieNumero;
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

	public String getCodMes() {
		return codMes;
	}

	public void setCodMes(String codMes) {
		this.codMes = codMes;
	}
	
	
	

}
