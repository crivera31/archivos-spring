package com.sprinboot.servicios.app.empresa.constructores.dto;

import java.math.BigDecimal;
import java.util.List;

public class LEDMCuentaDto {

	private String codCuenta;
	private String nomCuenta;
	private BigDecimal totalDebe;
	private BigDecimal totalHaber;
	private BigDecimal saldoDebe;
	private BigDecimal saldoHaber;
	List<LEDMVoucherDto> lstLEDMVoucherDto;
	
	public String getCodCuenta() {
		return codCuenta;
	}
	public void setCodCuenta(String codCuenta) {
		this.codCuenta = codCuenta;
	}
	public String getNomCuenta() {
		return nomCuenta;
	}
	public void setNomCuenta(String nomCuenta) {
		this.nomCuenta = nomCuenta;
	}
	public BigDecimal getTotalDebe() {
		return totalDebe;
	}
	public void setTotalDebe(BigDecimal totalDebe) {
		this.totalDebe = totalDebe;
	}
	public BigDecimal getTotalHaber() {
		return totalHaber;
	}
	public void setTotalHaber(BigDecimal totalHaber) {
		this.totalHaber = totalHaber;
	}
	public BigDecimal getSaldoDebe() {
		return saldoDebe;
	}
	public void setSaldoDebe(BigDecimal saldoDebe) {
		this.saldoDebe = saldoDebe;
	}
	public BigDecimal getSaldoHaber() {
		return saldoHaber;
	}
	public void setSaldoHaber(BigDecimal saldoHaber) {
		this.saldoHaber = saldoHaber;
	}
	public List<LEDMVoucherDto> getLstLEDMVoucherDto() {
		return lstLEDMVoucherDto;
	}
	public void setLstLEDMVoucherDto(List<LEDMVoucherDto> lstLEDMVoucherDto) {
		this.lstLEDMVoucherDto = lstLEDMVoucherDto;
	}

}
