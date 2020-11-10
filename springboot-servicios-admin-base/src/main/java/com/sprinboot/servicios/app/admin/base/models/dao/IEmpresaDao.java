package com.sprinboot.servicios.app.admin.base.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.Empresa;

public interface IEmpresaDao extends JpaRepository<Empresa, Long>{
	@Query("select u from Empresa u where u.codEmpresa != 'ADMIN' ")
	public List<Empresa> listarEmpresa();
	
	@Query("select u from Empresa u where u.codEmpresa = ?1")
	public Empresa obtenerEmpresaPorNom(String empresa);
}
