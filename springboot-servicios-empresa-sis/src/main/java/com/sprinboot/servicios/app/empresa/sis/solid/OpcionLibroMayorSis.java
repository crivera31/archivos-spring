package com.sprinboot.servicios.app.empresa.sis.solid;

import java.util.List;

import com.sprinboot.servicios.app.empresa.sis.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.sis.jsons.LibroMayorSisRest;

public interface OpcionLibroMayorSis {
	List<LibroMayorSisRest> proceso(Integer idperiodo, String anio, Integer codmes,Integer idperiodoanio,String codCuenta)throws ClaseException;

}
