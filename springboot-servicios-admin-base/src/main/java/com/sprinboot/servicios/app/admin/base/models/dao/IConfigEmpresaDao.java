package com.sprinboot.servicios.app.admin.base.models.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.ConfigEmpresa;

public interface IConfigEmpresaDao extends JpaRepository<ConfigEmpresa,Integer>{

	@Query("Select u from ConfigEmpresa u where u.periodoAnio.empresa.codEmpresa=?1 and u.estado =1")
	public Optional<ConfigEmpresa> getNomEmpresa (String nombre);
	
	@Query("Select u from ConfigEmpresa u where u.periodoAnio.anio.nomAnio=?1 and u.estado =1")
	public List<ConfigEmpresa> getListEmpresaPorAnioActual (String anio);
}
