package com.sprinboot.servicios.app.empresa.villasol.service;

import java.util.List;

import com.sprinboot.servicios.app.empresa.villasol.dto.ConsistenciaDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.ValidarLibroSPDao;
import com.sprinboot.servicios.empresa.commons.entity.Asiento;
import com.sprinboot.servicios.empresa.commons.services.IObjectService;

public interface AsientoServiceInterface extends IObjectService<Asiento> {

	public Asiento save(Asiento asiento); 
	public Asiento asientoFullVoucher(Asiento asiento);
	public Asiento findUltimoAsiento(String origen , Integer periodo) ;
	public Asiento findByParametros(String origen, Integer asiento, Integer periodo) ;
	public Asiento findByAsiento(Integer asiento);
	public List<ConsistenciaDto>  listarConsistencias(Integer mes,String anio);
	public List<ConsistenciaDto>  listarConsistenciasPorMes(Integer mes,String anio);
	public List<ValidarLibroSPDao> getValidacionSP() ;
}
