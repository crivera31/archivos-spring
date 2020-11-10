package com.sprinboot.servicios.app.admin.base.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.DrtPersJuridica;

import com.sprinboot.servicios.app.otros.commons.models.entity.TipoDocumento;



public interface IContAllPersonServiceImpl {
	
	
	public List<TipoDocumento> findTipoDocumentoAll();
	
	
	@Query("select u from DrtPersJuridica u where u.enabled = 1")
	public Page<DrtPersJuridica> findByDrtPersJuridicaEnabledPaged(Pageable pageable);
	
	public DrtPersJuridica findByDrtPersJuridicaId(Integer id);
	
	public List<DrtPersJuridica> findDrtPersJuridicaAll();
	
	public DrtPersJuridica findByCodigo(String codigo);
	
	public DrtPersJuridica save(DrtPersJuridica drtPersJuridica);
	
	
}
