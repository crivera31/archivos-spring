package com.sprinboot.servicios.app.empresa.constructores.service;

import java.util.List;

import com.sprinboot.servicios.app.empresa.constructores.dto.LEDiarioPorOrigenDao;
import com.sprinboot.servicios.app.empresa.constructores.dto.LEDiarioVoucherDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.LEDiarioVucherDetDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.MsgxDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.RegistroComprasDetDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.RegistroComprasSubDetDto;

public interface IPlesService {

	
	public String generatorFilaPle(RegistroComprasDetDto rs,Integer mes ,String anio,RegistroComprasSubDetDto subdet,String codClasificacion);
	public String generatorFilaLEVentas(RegistroComprasDetDto rs,Integer mes ,String anioFront,RegistroComprasSubDetDto subdet);
	public  List<MsgxDto>  generatorFilaLEDiario(LEDiarioVucherDetDto voucher, Integer mes, String anioFront,LEDiarioVoucherDto asiento,LEDiarioPorOrigenDao origen,String codPlanParametroCompras, String codPlanParametroVentas);
}
