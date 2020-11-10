package com.sprinboot.servicios.app.oriplan.sis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;
import com.sprinboot.servicios.oriplan.commons.entity.TablaSalarial;
import com.sprinboot.servicios.oriplan.commons.entity.TareoCab;

public interface TablaSalarialDao extends JpaRepository<TablaSalarial, Integer>{

	@Query("Select u From TablaSalarial u where u.enabled=1")
	public List<TablaSalarial> listar();  

	@Query("Select u From TablaSalarial u where u.categoria=?1 and  u.enabled=1")
	public TablaSalarial porCategoria(String categoria);  
}
