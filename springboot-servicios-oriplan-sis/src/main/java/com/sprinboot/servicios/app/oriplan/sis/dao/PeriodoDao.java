package com.sprinboot.servicios.app.oriplan.sis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.Periodo;
import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;
import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;

public interface PeriodoDao  extends JpaRepository<Periodo, Integer>{


	@Query("Select u From Periodo u where u.enabled=1")
	public List<Periodo> listar();
	
	
	@Query("Select u From Periodo u where trim(u.descripcion)=?1 and u.enabled=1")
	public Periodo findByNombre(String descripcion); 
}
