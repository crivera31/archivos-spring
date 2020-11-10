package com.sprinboot.servicios.app.oriplan.sis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.oriplan.commons.entity.TareoDet;

public interface TareoDetDao extends JpaRepository<TareoDet, Integer> {

	@Query("Select u From TareoDet u where u.enabled=1")
	public List<TareoDet> listar();

	@Query("Select u From TareoDet u where u.tareoCab.id = :idTareoCab AND u.enabled=1")
	public List<TareoDet> lsttareoDetPorTareoCab(Integer idTareoCab);
	
	@Query("Select u From TareoDet u where u.tareoCab.id = :idTareoCab AND u.dni = :dni AND u.enabled=1")
	public TareoDet tareoDetPorTareoCabDni(Integer idTareoCab, String dni);
}
