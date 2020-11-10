package com.sprinboot.servicios.app.empresa.sis.solid;

import com.sprinboot.servicios.app.empresa.sis.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.sis.jsons.HojaTrabajoRest;

public interface OpcionHojaTrabajo {
	HojaTrabajoRest proceso(Integer idperiodo, String anio, Integer codmes,Integer idperiodoanio)throws ClaseException;
}
