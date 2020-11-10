package com.sprinboot.servicios.app.empresa.oriana.dto;

import java.math.BigDecimal;
import java.util.List;

public class LibroDiarioTotalDto {


	private BigDecimal habertotal;
	private BigDecimal debetotal;
	private List<LibroDiarioDto> lstLibroDiarioDto;
	
	
	public BigDecimal getHabertotal() {
		return habertotal;
	}
	public void setHabertotal(BigDecimal habertotal) {
		this.habertotal = habertotal;
	}
	public BigDecimal getDebetotal() {
		return debetotal;
	}
	public void setDebetotal(BigDecimal debetotal) {
		this.debetotal = debetotal;
	}
	public List<LibroDiarioDto> getLstLibroDiarioDto() {
		return lstLibroDiarioDto;
	}
	public void setLstLibroDiarioDto(List<LibroDiarioDto> lstLibroDiarioDto) {
		this.lstLibroDiarioDto = lstLibroDiarioDto;
	}
	
	
}
