package com.sprinboot.servicios.app.oriplan.sis.service;

import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.Sctr;
import com.sprinboot.servicios.oriplan.commons.entity.SistemaPensiones;
import com.sprinboot.servicios.oriplan.commons.services.IObjectService;

public interface SistemaPensionesInterface  extends IObjectService<SistemaPensiones> {
	public SistemaPensiones buscarNombre(String codigo);
}
