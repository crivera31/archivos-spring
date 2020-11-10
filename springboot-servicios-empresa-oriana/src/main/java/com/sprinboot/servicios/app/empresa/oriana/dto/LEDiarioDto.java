package com.sprinboot.servicios.app.empresa.oriana.dto;

import java.math.BigDecimal;
import java.util.List;

public class LEDiarioDto {
	
	BigDecimal totalDebe;
	BigDecimal totalHaber;
	List<LEDiarioPorOrigenDao> listDiarioPorOrigenDao;
	
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
	public List<LEDiarioPorOrigenDao> getListDiarioPorOrigenDao() {
		return listDiarioPorOrigenDao;
	}
	public void setListDiarioPorOrigenDao(List<LEDiarioPorOrigenDao> listDiarioPorOrigenDao) {
		this.listDiarioPorOrigenDao = listDiarioPorOrigenDao;
	}
	
	
	
}
