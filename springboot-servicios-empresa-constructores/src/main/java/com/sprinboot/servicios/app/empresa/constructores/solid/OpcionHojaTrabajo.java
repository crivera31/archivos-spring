package com.sprinboot.servicios.app.empresa.constructores.solid;

import com.sprinboot.servicios.app.empresa.constructores.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.constructores.jsons.HojaTrabajoRest;

public interface OpcionHojaTrabajo {
	HojaTrabajoRest proceso(Integer idperiodo, String anio, Integer codmes,Integer idperiodoanio)throws ClaseException;
}
