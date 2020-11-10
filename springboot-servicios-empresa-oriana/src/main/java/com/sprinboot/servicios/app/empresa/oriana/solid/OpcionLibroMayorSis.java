package com.sprinboot.servicios.app.empresa.oriana.solid;

import java.util.List;

import com.sprinboot.servicios.app.empresa.oriana.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.oriana.jsons.LibroMayorSisRest;

public interface OpcionLibroMayorSis {
	List<LibroMayorSisRest> proceso(Integer idperiodo, String anio, Integer codmes,Integer idperiodoanio,String codCuenta)throws ClaseException;

}
