package com.sprinboot.servicios.app.admin.base.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.ParametrosAsis;


public interface IParametrosAsisDao  extends JpaRepository<ParametrosAsis,Integer> {

	@Query("Select u from ParametrosAsis u where u.enabled = 1 and u.origen.id =?1")
	public ParametrosAsis findParametrosAsis(Integer origen);
	
	@Query("Select u from ParametrosAsis u where u.enabled = 1 and u.anioId =?1")
	public List<ParametrosAsis> findAllParametrosbyPeriodo(Integer anio);
	
	@Query("Select u from ParametrosAsis u where u.enabled = 1 and u.anioId =?1 and u.origen.id =?2")
	public ParametrosAsis getParametrosbyPeriodoAndOrigen(Integer periodoAnio,Integer origen);
	
}
