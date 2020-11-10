package com.sprinboot.servicios.app.empresa.oriana.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.empresa.oriana.dao.VoucherDao;
import com.sprinboot.servicios.app.empresa.oriana.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.oriana.funciones.Funciones;
import com.sprinboot.servicios.app.empresa.oriana.jsons.HojaTrabajoDetRest;
import com.sprinboot.servicios.app.empresa.oriana.jsons.HojaTrabajoRest;
import com.sprinboot.servicios.app.empresa.oriana.jsons.LibroMayorSisRest;
import com.sprinboot.servicios.app.empresa.oriana.solid.OpcionHojaTrabajo;
import com.sprinboot.servicios.app.empresa.oriana.solid.OpcionLibroMayorSis;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;

@Service
public class MayorSistemaService implements MayorSistemaServiceInterface {
	private static final Logger LOGGER =LoggerFactory.getLogger(LibroMayorSisRest.class);

	@Autowired
	Funciones funciones;
	
	@Autowired
	VoucherDao voucherDao;
	
	@Override
	@Transactional
	public List<LibroMayorSisRest> reporteRegistros(List<OpcionLibroMayorSis> lstOpcion, Integer idperiodo, String anio,Integer codmes, Integer idperiodoanio,String codCuenta) throws ClaseException{
		List<LibroMayorSisRest> libroMayorSisRest = new ArrayList<LibroMayorSisRest>();
		for (OpcionLibroMayorSis opcion : lstOpcion) {
			libroMayorSisRest =   opcion.proceso(idperiodo, anio, codmes,idperiodoanio,codCuenta);
		  }
		return libroMayorSisRest;
	} 
	
	@Override
	@Transactional(readOnly=true)
	public List<Voucher> filtroDaoMensual(String codcuenta,Integer idperiodo, String anio,	Integer codmes) throws ClaseException{
		return voucherDao.listarParaLibroMayorSisMensual(codcuenta.trim(), codmes, anio, idperiodo);
	} 
	
	@Override
	@Transactional(readOnly=true)
	public List<Voucher> filtroDaoAcumulado(String codcuenta,String anio,	Integer codmes) throws ClaseException{
		return voucherDao.listarParaLibroMayorSisAcumulado(codcuenta.trim(), codmes, anio);
	} 
	
	private BigDecimal montoSegunMoneda(Voucher v, String moneda,BigDecimal monto) {
		  if(moneda.compareTo(v.getAbreNomMoneda().trim())==0) {
			  return monto;
		  }else {
			  if (monto!=null) {
			    return monto.compareTo(new BigDecimal("0"))==0 ? new BigDecimal("0.00") : v.getEquivalente();	
			 }else {
				return new BigDecimal("0.00");
			}
		}
	}
	

   
	@Override
	public List<LibroMayorSisRest> listarRest(List<Voucher> listVoucher,String monedaElegida ){
		List<LibroMayorSisRest> lista = new ArrayList<LibroMayorSisRest>();
		listVoucher.stream().map(v->{
			 LibroMayorSisRest rest = new LibroMayorSisRest();
			 rest.setCodDocumento(v.getCodDocumento());
			 rest.setCodMes(v.getAsiento().getCodMes()<10 ? "0" + v.getAsiento().getCodMes(): v.getAsiento().getCodMes()+"");
			 rest.setCodRuc(v.getCodRuc());
			 rest.setConcepto(v.getGlosario());
			 rest.setCredito(montoSegunMoneda(v,monedaElegida,v.getHaber()));
			 rest.setCuenta(v.getCodPlan());
			 rest.setDebito(montoSegunMoneda(v,monedaElegida,v.getDebe()));
			 rest.setFechaAsiento(funciones.getFechaToString(v.getAsiento().getFechaAsiento()));
			 rest.setFechaEmision(funciones.getFechaToString(v.getFechaEmision()));
			 rest.setFechaVencimiento(funciones.getFechaToString(v.getFechaVencimiento()));
			 rest.setMoneda(v.getAbreNomMoneda());
			 rest.setNumVoucher(v.getAsiento().getNumAsiento());
			 rest.setOrigen(v.getAsiento().getCodOrigen());
			 rest.setRazonSocial(v.getRazonSocial());
			 rest.setSaldo(new BigDecimal("0.0"));
			 rest.setSerieNumero(v.getSerieNumero());
			 rest.setTipoCambio(v.getTipoCambio());
			 lista.add(rest);
			 return v;
		   }
		).collect(Collectors.toList());
		return lista;
	}
	
	@Override
	public BigDecimal obtenerSaldoPorItem(BigDecimal saldo, BigDecimal debito , BigDecimal credito) {
		//ECUACION    SALDO = SALDO + (DEBITO - CREDITO)
		return funciones.sumarRedondear(saldo,funciones.restarRedondear(debito,credito , 5) , 2);
	}
}
