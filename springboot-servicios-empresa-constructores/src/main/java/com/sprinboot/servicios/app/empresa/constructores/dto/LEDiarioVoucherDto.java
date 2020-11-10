package com.sprinboot.servicios.app.empresa.constructores.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class LEDiarioVoucherDto {

	Integer numAsiento;
	
	//@DateTimeFormat(pattern ="yyyy-MM-dd")
	String fechaAsiento;
	BigDecimal debe;
	BigDecimal haber;
	List<LEDiarioVucherDetDto> lstLeDiarioVucherDetDto;
	
	
	public Integer getNumAsiento() {
		return numAsiento;
	}
	public void setNumAsiento(Integer numAsiento) {
		this.numAsiento = numAsiento;
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
	public List<LEDiarioVucherDetDto> getLstLeDiarioVucherDetDto() {
		return lstLeDiarioVucherDetDto;
	}
	public void setLstLeDiarioVucherDetDto(List<LEDiarioVucherDetDto> lstLeDiarioVucherDetDto) {
		this.lstLeDiarioVucherDetDto = lstLeDiarioVucherDetDto;
	}
	public String getFechaAsiento() {
		return fechaAsiento;
	}
	public void setFechaAsiento(String fechaAsiento) {
		this.fechaAsiento = fechaAsiento;
	}
	
	
	
	
}
