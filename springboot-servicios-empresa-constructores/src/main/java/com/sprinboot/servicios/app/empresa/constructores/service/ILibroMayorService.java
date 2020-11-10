package com.sprinboot.servicios.app.empresa.constructores.service;

import java.util.List;

import com.sprinboot.servicios.app.empresa.constructores.dto.LEDMCuentaDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.LEDMVoucherDto;

public interface ILibroMayorService {
	public LEDMCuentaDto generarDetalleLibroMayor(String codcuenta, Integer codmes , String anio,Integer idperiodo);

}
