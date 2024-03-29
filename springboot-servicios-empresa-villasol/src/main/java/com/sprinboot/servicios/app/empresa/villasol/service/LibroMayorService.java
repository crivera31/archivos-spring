package com.sprinboot.servicios.app.empresa.villasol.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.empresa.villasol.dao.VoucherDao;
import com.sprinboot.servicios.app.empresa.villasol.dto.LEDMCuentaDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.LEDMVoucherDto;
import com.sprinboot.servicios.app.empresa.villasol.funciones.Funciones;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;

@Service
public class LibroMayorService implements ILibroMayorService {

	@Autowired
	VoucherDao voucherDao;
	
	@Autowired
	Funciones funciones;
	
	@Override
	@Transactional
	public LEDMCuentaDto generarDetalleLibroMayor(String codcuenta, Integer codmes , String anio,Integer idperiodoanio){
		 LEDMCuentaDto mCuentaDto = new LEDMCuentaDto();
		 List<LEDMVoucherDto> lstLEDVoucherDto = new ArrayList<LEDMVoucherDto>();
		 List<Voucher>  listvou = voucherDao.listarParaLibroMayorPorCuenta(codcuenta.trim(), codmes, anio);
		 if (listvou.size()>1) {
			 listvou.forEach(vou->{
					LEDMVoucherDto mVoucherDto = new  LEDMVoucherDto();
					  mVoucherDto.setCodCuenta(vou.getCodPlan());
					  mVoucherDto.setCodOrigen(vou.getAsiento().getCodOrigen());
					  mVoucherDto.setDebe(vou.getDebe());
					  mVoucherDto.setFechaOperacion(funciones.getFechaToString(vou.getAsiento().getFechaAsiento()));
					  mVoucherDto.setGlosario(vou.getGlosario());
					  mVoucherDto.setHaber(vou.getHaber());
					  mVoucherDto.setIdVoucher(vou.getId());
					  mVoucherDto.setMoneda(vou.getAbreNomMoneda());
					  mVoucherDto.setNumAsiento(vou.getAsiento().getNumAsiento());
					  mCuentaDto.setTotalDebe(funciones.sumarRedondear(mCuentaDto.getTotalDebe(), vou.getDebe(), 2));
					  mCuentaDto.setTotalHaber(funciones.sumarRedondear(mCuentaDto.getTotalHaber(), vou.getHaber(), 2));
				       mCuentaDto.setNomCuenta(vou.getNomPlan());
					  lstLEDVoucherDto.add(mVoucherDto);
				  });
				 mCuentaDto.setCodCuenta(codcuenta.trim());
				 mCuentaDto.setLstLEDMVoucherDto(lstLEDVoucherDto); 
				 return calcularSaldos(mCuentaDto);
		}else {
			return null;
		}
		
	}
	
	
	public LEDMCuentaDto calcularSaldos(LEDMCuentaDto mCuentaDto ) {
		 
		if (mCuentaDto.getTotalHaber().compareTo(mCuentaDto.getTotalDebe())==0) {
			//saldos Iguales
			  mCuentaDto.setSaldoDebe(new BigDecimal("0.00"));
			  mCuentaDto.setSaldoHaber(new BigDecimal("0.00"));
		}else {
			if (mCuentaDto.getTotalHaber().compareTo(mCuentaDto.getTotalDebe())==1) {
				//Haber es mayor que el debe
				mCuentaDto.setSaldoHaber(funciones.restarRedondear(mCuentaDto.getTotalHaber(), mCuentaDto.getTotalDebe(), 2));
			    mCuentaDto.setSaldoDebe(new BigDecimal("0.00"));
			}else {
				//Debe es mayor que el debe
			    mCuentaDto.setSaldoHaber(new BigDecimal("0.00"));
				mCuentaDto.setSaldoDebe(funciones.restarRedondear(mCuentaDto.getTotalDebe(), mCuentaDto.getTotalHaber(), 2));
			}
		}
		return mCuentaDto;
	}
}
