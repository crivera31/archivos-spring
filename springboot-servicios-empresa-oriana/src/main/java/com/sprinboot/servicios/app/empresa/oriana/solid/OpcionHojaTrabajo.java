package com.sprinboot.servicios.app.empresa.oriana.solid;

import com.sprinboot.servicios.app.empresa.oriana.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.oriana.jsons.HojaTrabajoRest;

public interface OpcionHojaTrabajo {
	HojaTrabajoRest proceso(Integer idperiodo, String anio, Integer codmes,Integer idperiodoanio)throws ClaseException;
}
