package com.sprinboot.servicios.app.empresa.villasol.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprinboot.servicios.empresa.commons.entity.ContAllPerson;



public interface IContAllPersonDao extends JpaRepository<ContAllPerson ,Integer> {

	@Query("Select u from ContAllPerson u where u.enabled = 1")
	public Page<ContAllPerson> findByEnabledPaged(Pageable pageable);

	@Query("Select u from ContAllPerson u where u.enabled = 1 and u.codigo =?1")
	public ContAllPerson findPorDocumentoDrt(String codigo);
	
	@Query("Select u from ContAllPerson u  where u.codigo like ?1% and  u.enabled = 1")
	public List<ContAllPerson> filtrarPorCodigo(String cod);
	
	@Query("Select u from ContAllPerson u  where u.rsocial like %:nom% and  u.enabled = 1")
	public List<ContAllPerson> filtrarPorNombre(@Param("nom") String nom);
	
	
}
