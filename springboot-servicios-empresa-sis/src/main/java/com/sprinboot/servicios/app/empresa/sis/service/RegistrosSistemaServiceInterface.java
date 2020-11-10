package com.sprinboot.servicios.app.empresa.sis.service;

import java.util.List;

import com.sprinboot.servicios.app.empresa.sis.dao.RegistroLibrosDao;
import com.sprinboot.servicios.app.empresa.sis.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.sis.jsons.RegistrosCabRest;
import com.sprinboot.servicios.app.empresa.sis.jsons.RegistrosDetRest;
import com.sprinboot.servicios.app.empresa.sis.solid.OpcionReporte;
import com.sprinboot.servicios.empresa.commons.entity.RegistroLibros;

public interface RegistrosSistemaServiceInterface {
	public RegistrosCabRest reporteRegistros(List<OpcionReporte> lstOpcion ,Integer idperiodo,String anio,Integer codmes, String codLibro, String codPlanParametro) ;
	 public RegistrosDetRest getSolesToDolares(RegistrosDetRest r,Integer decimales) throws ClaseException ;
	 public RegistrosDetRest getDolaresToSoles(RegistrosDetRest r,Integer decimales) throws ClaseException ;
	 public RegistrosDetRest getDatosLibro(RegistroLibros r,String moneda) throws ClaseException ;

	 public RegistrosCabRest getRegistroCabRest(List<RegistroLibros> lista,String moneda)  ;
	 public RegistroLibrosDao getDao() ;
	 
	 
}
