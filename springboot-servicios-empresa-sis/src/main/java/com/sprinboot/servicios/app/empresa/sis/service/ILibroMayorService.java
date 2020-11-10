package com.sprinboot.servicios.app.empresa.sis.service;

import java.util.List;

import com.sprinboot.servicios.app.empresa.sis.dto.LEDMCuentaDto;
import com.sprinboot.servicios.app.empresa.sis.dto.LEDMVoucherDto;

public interface ILibroMayorService {
	public LEDMCuentaDto generarDetalleLibroMayor(String codcuenta, Integer codmes , String anio,Integer idperiodo);

}
