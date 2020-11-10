package com.sprinboot.servicios.app.empresa.villasol.service;

import java.util.List;

import com.sprinboot.servicios.app.empresa.villasol.dto.LEDiarioPorOrigenDao;
import com.sprinboot.servicios.app.empresa.villasol.dto.LEDiarioVoucherDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.LEDiarioVucherDetDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.MsgxDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.RegistroComprasDetDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.RegistroComprasSubDetDto;

public interface IPlesService {

	
	public String generatorFilaPle(RegistroComprasDetDto rs,Integer mes ,String anio,RegistroComprasSubDetDto subdet,String codClasificacion);
	public String generatorFilaLEVentas(RegistroComprasDetDto rs,Integer mes ,String anioFront,RegistroComprasSubDetDto subdet);
	public  List<MsgxDto>  generatorFilaLEDiario(LEDiarioVucherDetDto voucher, Integer mes, String anioFront,LEDiarioVoucherDto asiento,LEDiarioPorOrigenDao origen,String codPlanParametroCompras, String codPlanParametroVentas);
}
