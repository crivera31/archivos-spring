package com.sprinboot.servicios.app.empresa.constructores.solid;


import com.sprinboot.servicios.app.empresa.constructores.jsons.RegistrosCabRest;

public interface OpcionReporte {
	RegistrosCabRest proceso(Integer idperiodo, String anio, Integer codmes,String codlibro,String codPlanParametro);
}
