package com.sprinboot.servicios.app.admin.base.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.ClasificacionBienes;


public interface ClasifiacionBIenesDao  extends JpaRepository<ClasificacionBienes, Integer> {
	@Query("Select u from ClasificacionBienes u where  u.estado =1")
	public List<ClasificacionBienes> listClasfiacion();
	
}
