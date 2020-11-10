package com.sprinboot.servicios.app.empresa.sis.solid;


import com.sprinboot.servicios.app.empresa.sis.jsons.RegistrosCabRest;

public interface OpcionReporte {
	RegistrosCabRest proceso(Integer idperiodo, String anio, Integer codmes,String codlibro,String codPlanParametro);
}
