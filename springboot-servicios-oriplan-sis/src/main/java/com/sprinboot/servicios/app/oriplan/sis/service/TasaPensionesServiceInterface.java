package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;

import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.TasasPensiones;
import com.sprinboot.servicios.oriplan.commons.services.IObjectService;

public interface TasaPensionesServiceInterface  extends IObjectService<TasasPensiones>{

	public List<TasasPensiones> listarTasasPensiones();
	public TasasPensiones buscar(String codigo);
	public TasasPensiones buscarNombre(String codigo);
}
