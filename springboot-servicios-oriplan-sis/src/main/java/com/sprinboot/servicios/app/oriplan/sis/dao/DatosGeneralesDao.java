package com.sprinboot.servicios.app.oriplan.sis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;

public interface DatosGeneralesDao  extends JpaRepository<DatosGenerales, Integer>{

	@Query("Select u From DatosGenerales u where u.enabled=1")
	public List<DatosGenerales> findAllEnabeld();  
}
