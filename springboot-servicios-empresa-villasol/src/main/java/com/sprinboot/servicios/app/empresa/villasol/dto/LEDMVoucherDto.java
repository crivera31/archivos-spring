package com.sprinboot.servicios.app.empresa.villasol.dto;

import java.math.BigDecimal;
import java.util.List;

public class LEDMVoucherDto {

	private Integer numAsiento;
	private String codOrigen;
	private String fechaOperacion;
	private String codCuenta;
	private BigDecimal debe;
	private BigDecimal haber;
	private String glosario;
	private String moneda;
	private Integer idVoucher;

	
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
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getCodCuenta() {
		return codCuenta;
	}
	public void setCodCuenta(String codCuenta) {
		this.codCuenta = codCuenta;
	}
	public BigDecimal getDebe() {
		return debe;
	}
	public void setDebe(BigDecimal debe) {
		this.debe = debe;
	}
	public BigDecimal getHaber() {
		return haber;
	}
	public void setHaber(BigDecimal haber) {
		this.haber = haber;
	}
	public String getGlosario() {
		return glosario;
	}
	public void setGlosario(String glosario) {
		this.glosario = glosario;
	}
	public Integer getIdVoucher() {
		return idVoucher;
	}
	public void setIdVoucher(Integer idVoucher) {
		this.idVoucher = idVoucher;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	
	
	
}
