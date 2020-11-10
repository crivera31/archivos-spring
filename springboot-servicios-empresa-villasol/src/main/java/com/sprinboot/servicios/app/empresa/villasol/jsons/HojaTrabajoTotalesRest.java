package com.sprinboot.servicios.app.empresa.villasol.jsons;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HojaTrabajoTotalesRest {
	
	@JsonProperty("nombre")
	private String nombre;
	
	@JsonProperty("montoDebito")
	private BigDecimal montoDebito;
	
	@JsonProperty("montoCredito")
	private BigDecimal montoCredito;
	
	@JsonProperty("saldoDeudor")
	private BigDecimal saldoDeudor;
	
	@JsonProperty("saldoAcreedor")
	private BigDecimal saldoAcreedor;
	
	@JsonProperty("inventarioActivo")
	private BigDecimal inventarioActivo;
	
	@JsonProperty("inventarioPasivo")
	private BigDecimal inventarioPasivo;
	
	@JsonProperty("naturalezaPerdida")
	private BigDecimal naturalezaPerdida;
	
	@JsonProperty("naturalezaGanancia")
	private BigDecimal naturalezaGanancia;
	
	@JsonProperty("funcionPerdida")
	private BigDecimal funcionPerdida;
		
	@JsonProperty("funcionGanancia")
	private BigDecimal funcionGanancia;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getMontoDebito() {
		return montoDebito;
	}

	public void setMontoDebito(BigDecimal montoDebito) {
		this.montoDebito = montoDebito;
	}

	public BigDecimal getMontoCredito() {
		return montoCredito;
	}

	public void setMontoCredito(BigDecimal montoCredito) {
		this.montoCredito = montoCredito;
	}

	public BigDecimal getSaldoDeudor() {
		return saldoDeudor;
	}

	public void setSaldoDeudor(BigDecimal saldoDeudor) {
		this.saldoDeudor = saldoDeudor;
	}

	public BigDecimal getSaldoAcreedor() {
		return saldoAcreedor;
	}

	public void setSaldoAcreedor(BigDecimal saldoAcreedor) {
		this.saldoAcreedor = saldoAcreedor;
	}

	public BigDecimal getInventarioActivo() {
		return inventarioActivo;
	}

	public void setInventarioActivo(BigDecimal inventarioActivo) {
		this.inventarioActivo = inventarioActivo;
	}

	public BigDecimal getInventarioPasivo() {
		return inventarioPasivo;
	}

	public void setInventarioPasivo(BigDecimal inventarioPasivo) {
		this.inventarioPasivo = inventarioPasivo;
	}

	public BigDecimal getNaturalezaPerdida() {
		return naturalezaPerdida;
	}

	public void setNaturalezaPerdida(BigDecimal naturalezaPerdida) {
		this.naturalezaPerdida = naturalezaPerdida;
	}

	public BigDecimal getNaturalezaGanancia() {
		return naturalezaGanancia;
	}

	public void setNaturalezaGanancia(BigDecimal naturalezaGanancia) {
		this.naturalezaGanancia = naturalezaGanancia;
	}

	public BigDecimal getFuncionPerdida() {
		return funcionPerdida;
	}

	public void setFuncionPerdida(BigDecimal funcionPerdida) {
		this.funcionPerdida = funcionPerdida;
	}

	public BigDecimal getFuncionGanancia() {
		return funcionGanancia;
	}

	public void setFuncionGanancia(BigDecimal funcionGanancia) {
		this.funcionGanancia = funcionGanancia;
	}
	
	
}
