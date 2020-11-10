package com.sprinboot.servicios.app.empresa.villasol.service;

import java.util.List;

import com.sprinboot.servicios.app.empresa.villasol.dto.LEDMCuentaDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.LEDMVoucherDto;

public interface ILibroMayorService {
	public LEDMCuentaDto generarDetalleLibroMayor(String codcuenta, Integer codmes , String anio,Integer idperiodo);

}
