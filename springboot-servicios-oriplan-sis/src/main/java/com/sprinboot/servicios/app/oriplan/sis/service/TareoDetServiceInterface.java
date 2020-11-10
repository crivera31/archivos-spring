package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;

import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.TareoDet;
import com.sprinboot.servicios.oriplan.commons.services.IObjectService;

public interface TareoDetServiceInterface  extends IObjectService<TareoDet>{
	public List<TareoDet> listar() ;
}
