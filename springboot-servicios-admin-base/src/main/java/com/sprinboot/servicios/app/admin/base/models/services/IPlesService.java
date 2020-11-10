package com.sprinboot.servicios.app.admin.base.models.services;

import java.util.List;

import com.sprinboot.servicios.app.admin.base.dto.MsgxDto;
import com.sprinboot.servicios.app.otros.commons.models.entity.PlanCuenta;

public interface IPlesService {

	public List<MsgxDto>  generatorFilaLEDiarioPlanContable(PlanCuenta plan,String anio, Integer mes) ;
}
