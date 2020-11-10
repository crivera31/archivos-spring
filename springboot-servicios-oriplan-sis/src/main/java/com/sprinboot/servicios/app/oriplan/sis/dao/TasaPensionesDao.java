package com.sprinboot.servicios.app.oriplan.sis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;
import com.sprinboot.servicios.oriplan.commons.entity.TareoCab;
import com.sprinboot.servicios.oriplan.commons.entity.TasasPensiones;

public interface TasaPensionesDao extends JpaRepository<TasasPensiones, Integer>{

	@Query("Select u From TasasPensiones u where u.enabled=1")
	public List<TasasPensiones> listarTasasPensiones();  

	@Query("Select u From TasasPensiones u where u.sistemaPensiones.codigo=?1 and u.enabled=1")
	public TasasPensiones buscar(String codigo);  
	
	@Query("Select u From TasasPensiones u where u.sistemaPensiones.nombre=?1 and u.enabled=1")
	public TasasPensiones buscarNombre(String nombre);  
	
}
