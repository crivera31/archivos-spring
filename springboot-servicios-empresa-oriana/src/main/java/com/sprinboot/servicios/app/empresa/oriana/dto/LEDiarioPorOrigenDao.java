package com.sprinboot.servicios.app.empresa.oriana.dto;

import java.util.List;

public class LEDiarioPorOrigenDao {

	String codOrigen;
	String nomOrigen;
	List<LEDiarioVoucherDto> listLeDiarioVoucherDto;
	
	public String getCodOrigen() {
		return codOrigen;
	}
	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}
	public String getNomOrigen() {
		return nomOrigen;
	}
	public void setNomOrigen(String nomOrigen) {
		this.nomOrigen = nomOrigen;
	}
	public List<LEDiarioVoucherDto> getListLeDiarioVoucherDto() {
		return listLeDiarioVoucherDto;
	}
	public void setListLeDiarioVoucherDto(List<LEDiarioVoucherDto> listLeDiarioVoucherDto) {
		this.listLeDiarioVoucherDto = listLeDiarioVoucherDto;
	}
	
	
}
