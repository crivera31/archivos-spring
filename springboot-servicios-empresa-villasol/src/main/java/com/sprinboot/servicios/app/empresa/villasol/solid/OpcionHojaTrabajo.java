package com.sprinboot.servicios.app.empresa.villasol.solid;

import com.sprinboot.servicios.app.empresa.villasol.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.villasol.jsons.HojaTrabajoRest;

public interface OpcionHojaTrabajo {
	HojaTrabajoRest proceso(Integer idperiodo, String anio, Integer codmes,Integer idperiodoanio)throws ClaseException;
}
