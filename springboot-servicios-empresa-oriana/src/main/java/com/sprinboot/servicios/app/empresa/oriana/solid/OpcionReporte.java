package com.sprinboot.servicios.app.empresa.oriana.solid;


import com.sprinboot.servicios.app.empresa.oriana.jsons.RegistrosCabRest;

public interface OpcionReporte {
	RegistrosCabRest proceso(Integer idperiodo, String anio, Integer codmes,String codlibro,String codPlanParametro);
}
