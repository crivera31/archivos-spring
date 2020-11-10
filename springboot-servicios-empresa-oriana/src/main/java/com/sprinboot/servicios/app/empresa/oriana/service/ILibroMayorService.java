package com.sprinboot.servicios.app.empresa.oriana.service;

import java.util.List;

import com.sprinboot.servicios.app.empresa.oriana.dto.LEDMCuentaDto;
import com.sprinboot.servicios.app.empresa.oriana.dto.LEDMVoucherDto;

public interface ILibroMayorService {
	public LEDMCuentaDto generarDetalleLibroMayor(String codcuenta, Integer codmes , String anio,Integer idperiodo);

}
