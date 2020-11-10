package com.sprinboot.servicios.app.empresa.oriana.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sprinboot.servicios.app.empresa.oriana.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.oriana.jsons.LibroMayorSisRest;
import com.sprinboot.servicios.app.empresa.oriana.solid.OpcionHojaTrabajo;
import com.sprinboot.servicios.app.empresa.oriana.solid.OpcionLibroMayorSis;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;

public interface MayorSistemaServiceInterface {
	public List<LibroMayorSisRest> reporteRegistros(List<OpcionLibroMayorSis> lstOpcion, Integer idperiodo, String anio,Integer codmes, Integer idperiodoanio,String codCuenta) throws ClaseException;
	public List<Voucher> filtroDaoMensual(String codcuenta,Integer idperiodo, String anio,	Integer codmes) throws ClaseException;
	public List<Voucher> filtroDaoAcumulado(String codcuenta,String anio,	Integer codmes) throws ClaseException;
	public List<LibroMayorSisRest> listarRest(List<Voucher> listVoucher,String monedaElegida );
	public BigDecimal obtenerSaldoPorItem(BigDecimal saldo, BigDecimal debito , BigDecimal credito);
}
