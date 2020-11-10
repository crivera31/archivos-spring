package com.sprinboot.servicios.app.oriplan.sis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.oriplan.commons.entity.TareoCab;

public interface TareoCabDao extends JpaRepository<TareoCab, Integer> {
	@Query("Select u From TareoCab u where u.enabled=1")
	public List<TareoCab> listar();

	@Query("Select u From TareoCab u where u.nomPeriodoSemanal=?1 and u.enabled=1")
	public List<TareoCab> verificarrepepiticion(String periodosemanal);

	@Query("Select u From TareoCab u where u.nomPeriodoSemanal=?1 and  u.idDatosGenerales=?2 and u.enabled=1")
	public TareoCab buscar(String periodosemanal, Integer idobra);
	
	@Query("Select u From TareoCab u where u.nomPeriodoSemanal=?1 and  u.idDatosGenerales=?2 and u.enabled=1")
	public List<TareoCab> lstBuscar(String periodosemanal, Integer idobra);
}
