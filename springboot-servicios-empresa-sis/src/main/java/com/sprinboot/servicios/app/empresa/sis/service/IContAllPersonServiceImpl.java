package com.sprinboot.servicios.app.empresa.sis.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.empresa.commons.entity.ContAllPerson;
import com.sprinboot.servicios.empresa.commons.entity.TipoAllPersona;





public interface IContAllPersonServiceImpl {
	
	@Query("select u from ContAllPerson u where u.enabled = 1")
	public Page<ContAllPerson> findByEnabledPaged(Pageable pageable);
	
	public ContAllPerson findById(Integer id);
	
	public ContAllPerson save(ContAllPerson contAllPerson);
	
	
	public List<TipoAllPersona> findTipoAllPersonaAll();
		
	
	public ContAllPerson findPorDocumentoDrt(String codigo);
	
	public List<ContAllPerson> filtrarPorCodigo(String cod);
	

	
}
