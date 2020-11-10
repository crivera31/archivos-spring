package com.sprinboot.servicios.app.empresa.sis.service;

import java.util.List;

import com.sprinboot.servicios.app.empresa.sis.dto.LEDiarioDto;
import com.sprinboot.servicios.app.empresa.sis.dto.LEDiarioPorOrigenDao;
import com.sprinboot.servicios.app.empresa.sis.dto.LibroDiarioDto;
import com.sprinboot.servicios.app.empresa.sis.dto.LibroDiarioTotalDto;

public interface ReporteLibroDiarioServiceInterface {

	//vipublic  List<LibroDiarioDto>  libroDiario(String moneda, Integer mes , String anio , String codOrigen , Integer periodo); 
	
	public LibroDiarioTotalDto libroDiarioMensual(Integer periodo,String moneda);
	public LibroDiarioTotalDto libroDiarioMensualConOrigen(Integer periodo,String moneda,String codOrigen);
	public LibroDiarioTotalDto libroDiarioAcumulado(String moneda,String anio,Integer mes);
	public LibroDiarioTotalDto libroDiarioAcumuladoConOrigen (String moneda,String anio,Integer mes,String codOrigen);
	public LEDiarioPorOrigenDao libroDiarioSunat(String codOrigen, Integer codmes, String anio);	
	public LEDiarioDto getCalcularMontosTotalesSunat(LEDiarioDto diario, Integer decimales) ;
}
