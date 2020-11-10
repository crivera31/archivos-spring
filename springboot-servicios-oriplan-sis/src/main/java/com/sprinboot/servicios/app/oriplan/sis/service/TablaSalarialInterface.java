package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;

import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.TablaSalarial;
import com.sprinboot.servicios.oriplan.commons.services.IObjectService;

public interface TablaSalarialInterface  extends IObjectService<TablaSalarial>{
	public List<TablaSalarial> listar();
	public TablaSalarial porCategoria(String categoria);
}
