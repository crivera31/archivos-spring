package com.sprinboot.servicios.app.empresa.villasol.solid;


import com.sprinboot.servicios.app.empresa.villasol.jsons.RegistrosCabRest;

public interface OpcionReporte {
	RegistrosCabRest proceso(Integer idperiodo, String anio, Integer codmes,String codlibro,String codPlanParametro);
}
