package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;

import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.Sctr;
import com.sprinboot.servicios.oriplan.commons.services.IObjectService;

public interface SctrServiceInterface  extends IObjectService<Sctr>{
	public List<Sctr> listar();
	public Sctr buscar();
}
