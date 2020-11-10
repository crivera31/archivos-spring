package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;


import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;
import com.sprinboot.servicios.oriplan.commons.services.IObjectService;

public interface DatosGeneralesServiceInterface  extends IObjectService<DatosGenerales>{

	public List<DatosGenerales> getEnabled();
}
