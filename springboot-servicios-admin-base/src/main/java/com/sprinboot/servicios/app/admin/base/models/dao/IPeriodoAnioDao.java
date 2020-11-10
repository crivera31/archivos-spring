package com.sprinboot.servicios.app.admin.base.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.PeriodoAnio;

public interface IPeriodoAnioDao extends JpaRepository<PeriodoAnio, Integer> {


	@Query("Select u from PeriodoAnio u where u.empresa.codEmpresa=?1 and u.estado=1 and u.anio.estado=1")
	public List<PeriodoAnio> allPeriodoAnioByEmpresa(String empresa);
	
	@Query("Select u from PeriodoAnio u where u.estado=1 and u.anio.estado=1")
	public List<PeriodoAnio> allEmpresasConAnio();
	
	@Query("Select u from PeriodoAnio u where u.empresa.codEmpresa=?1 and u.anio.estado=1 and u.estado=1 ")
	public PeriodoAnio busquedaDePeriodoAnioPorModuloEmppresa(String nomModulo);
}
