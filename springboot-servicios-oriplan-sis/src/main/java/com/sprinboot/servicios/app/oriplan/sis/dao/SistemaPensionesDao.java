package com.sprinboot.servicios.app.oriplan.sis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;
import com.sprinboot.servicios.oriplan.commons.entity.SistemaPensiones;
import com.sprinboot.servicios.oriplan.commons.entity.TablaSalarial;
import com.sprinboot.servicios.oriplan.commons.entity.TareoCab;

public interface SistemaPensionesDao  extends JpaRepository<SistemaPensiones, Integer>{
	
	@Query("Select u From SistemaPensiones u where u.codigo=?1 and  u.enabled=1")
	public SistemaPensiones buscarNombre(String codigo); 

}
