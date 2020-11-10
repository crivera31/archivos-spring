package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;

import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.Periodo;
import com.sprinboot.servicios.oriplan.commons.services.IObjectService;

public interface PeriodoServiceInterface  extends IObjectService<Periodo>{
	public List<Periodo> listar() ;
	
	public boolean verificarRepetcion(String name);
}
