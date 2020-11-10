package com.sprinboot.servicios.app.empresa.villasol.solid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.empresa.villasol.dto.SaldoTot;
import com.sprinboot.servicios.app.empresa.villasol.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.villasol.jsons.LibroMayorSisRest;
import com.sprinboot.servicios.app.empresa.villasol.service.MayorSistemaServiceInterface;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;

@Service("MayorSolesMensual")
public class MayorSolesMensual implements OpcionLibroMayorSis {
	
	private MayorSistemaServiceInterface service;
	
	@Autowired
	public MayorSolesMensual(MayorSistemaServiceInterface service) {
		this.service = service;
	}

	@Override
	public List<LibroMayorSisRest> proceso(Integer idperiodo, String anio, Integer codmes, Integer idperiodoanio,
			String codCuenta) throws ClaseException {
		
		List<Voucher> listVoucher = service.filtroDaoMensual(codCuenta, idperiodo, anio, codmes);
		List<LibroMayorSisRest> listaRest = new ArrayList<LibroMayorSisRest>();
		listaRest = service.listarRest(listVoucher, "S");
		SaldoTot total = new SaldoTot();
		total.setSaldoTotal(new BigDecimal("0.00"));
		listaRest.stream().map(det->{
			total.setSaldoTotal(service.obtenerSaldoPorItem(total.getSaldoTotal(), det.getDebito(), det.getCredito()));
			det.setSaldo(total.getSaldoTotal());
			 return det;
		   }
		).collect(Collectors.toList());
		return listaRest;
	}

}
