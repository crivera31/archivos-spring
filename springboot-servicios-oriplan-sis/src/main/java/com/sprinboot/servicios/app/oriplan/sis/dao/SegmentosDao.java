package com.sprinboot.servicios.app.oriplan.sis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;
import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;
import com.sprinboot.servicios.oriplan.commons.entity.TareoCab;

public interface SegmentosDao extends JpaRepository<Segmentos, Integer>{

	@Query("Select u From Segmentos u where u.enabled=1")
	public List<Segmentos> listSegmentos();  
	
	@Query("Select u From Segmentos u where trim(u.segmento)=?1 and u.enabled=1")
	public Segmentos findByNombre(String segmento); 
	
	
}
