package com.sprinboot.servicios.app.empresa.sis.dto;

import java.math.BigDecimal;
import java.util.List;



public class LibroDiarioDto {
	

	
	private BigDecimal subTotalDebe;
	
	private BigDecimal subTotalHaber;
	

	
	private List<LibroDiarioDetDto> lstLibroDiarioDetDto;
	


	

	public BigDecimal getSubTotalDebe() {
		return subTotalDebe;
	}

	public void setSubTotalDebe(BigDecimal subTotalDebe) {
		this.subTotalDebe = subTotalDebe;
	}

	public BigDecimal getSubTotalHaber() {
		return subTotalHaber;
	}

	public void setSubTotalHaber(BigDecimal subTotalHaber) {
		this.subTotalHaber = subTotalHaber;
	}

	public List<LibroDiarioDetDto> getLstLibroDiarioDetDto() {
		return lstLibroDiarioDetDto;
	}

	public void setLstLibroDiarioDetDto(List<LibroDiarioDetDto> lstLibroDiarioDetDto) {
		this.lstLibroDiarioDetDto = lstLibroDiarioDetDto;
	}

	

	
	
	
	
}
