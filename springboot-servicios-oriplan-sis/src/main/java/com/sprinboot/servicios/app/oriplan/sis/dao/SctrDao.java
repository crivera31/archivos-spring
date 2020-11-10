package com.sprinboot.servicios.app.oriplan.sis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.oriplan.commons.entity.Sctr;
import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;


public interface SctrDao extends JpaRepository<Sctr, Integer> {

	@Query("Select u From Sctr u where u.enabled=1")
	public List<Sctr> listar();  
	
	@Query("Select u From Sctr u where u.enabled=1")
	public Sctr buscar();  
	
}
