package com.sprinboot.servicios.app.admin.base.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.UnidadNegocio;


public interface IUnidadNegocio extends JpaRepository<UnidadNegocio, Integer>{

	@Query("Select u from UnidadNegocio u where u.enabled = 1")
	public Page<UnidadNegocio> findByEnabledPaged(Pageable pageable);
	
	@Query("Select u from UnidadNegocio u where u.enabled = 1 and u.codigoUnidadNegocio =?1 and u.periodoAnio.id=?2")
	public UnidadNegocio buscarPorCodigo(String cod,Integer idperiodoanio);
	
	@Query("Select u from UnidadNegocio u  where u.codigoUnidadNegocio like ?1% and u.periodoAnio.id=?2 and u.enabled = 1")
	public List<UnidadNegocio> filtrarPorCodigo(String cod,Integer idperiodoanio);
	
	@Query("Select u from UnidadNegocio u where u.id =?1 AND u.enabled = 1 ")
	public UnidadNegocio buscarPorId(Integer id);
	
	@Query("Select u from UnidadNegocio u where u.enabled = 1 and u.periodoAnio.id=?1 and u.isObra=1")
	public List<UnidadNegocio>  buscarPorCodigo(Integer idperiodoanio);
	
	@Query("Select u from UnidadNegocio u where u.enabled = 1 and u.periodoAnio.id=?1")
	public List<UnidadNegocio>  buscarPorObraAndAdministrativo(Integer idperiodoanio);
	
	//public List<UnidadNegocio> findByCodCuentaStartingWithIgnoreCase(String term);
}
