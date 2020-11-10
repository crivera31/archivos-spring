package com.sprinboot.servicios.app.admin.base.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.Anio;

public interface IAnioDao  extends JpaRepository<Anio, Integer> {
	
	@Query("Select u from Anio u where  u.estado =1")
	public List<Anio> findAllAnio();
	
	@Query("Select u from Anio u where u.nomAnio =?1 and u.estado =1")
	public Anio findAnio(String anio);
}
