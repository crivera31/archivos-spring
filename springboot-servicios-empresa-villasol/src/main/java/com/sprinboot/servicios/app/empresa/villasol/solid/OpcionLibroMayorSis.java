package com.sprinboot.servicios.app.empresa.villasol.solid;

import java.util.List;

import com.sprinboot.servicios.app.empresa.villasol.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.villasol.jsons.LibroMayorSisRest;

public interface OpcionLibroMayorSis {
	List<LibroMayorSisRest> proceso(Integer idperiodo, String anio, Integer codmes,Integer idperiodoanio,String codCuenta)throws ClaseException;

}
