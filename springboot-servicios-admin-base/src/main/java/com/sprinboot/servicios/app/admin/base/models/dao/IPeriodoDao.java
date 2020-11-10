package com.sprinboot.servicios.app.admin.base.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.Periodo;

public interface IPeriodoDao extends JpaRepository<Periodo, Integer> {

	@Query("select u from Periodo u where u.periodoAnio.empresa.codEmpresa=?1 and u.periodoAnio.anio.estado=1")
	public List<Periodo> listarPeriodo(String empresa);
}
