package com.sprinboot.servicios.app.empresa.sis.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.empresa.sis.dao.IContAllPersonDao;
import com.sprinboot.servicios.app.empresa.sis.dao.ITipoAllPersonaDao;
import com.sprinboot.servicios.app.empresa.sis.funciones.Funciones;
import com.sprinboot.servicios.empresa.commons.entity.ContAllPerson;
import com.sprinboot.servicios.empresa.commons.entity.TipoAllPersona;


@Service
public class ContAllPersonService implements IContAllPersonServiceImpl {

	private Logger logger = LoggerFactory.getLogger(ContAllPersonService.class);
	
	@Autowired
	private IContAllPersonDao contAllPersonaDao;
	
	
	@Autowired
	private ITipoAllPersonaDao tipoAllPersonaDao;
	
	private Funciones funciones; 

	@Override
	@Transactional(readOnly=true)
	public Page<ContAllPerson> findByEnabledPaged(Pageable pageable) {
		return contAllPersonaDao.findByEnabledPaged(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public ContAllPerson findById(Integer id) {
		return contAllPersonaDao.findById(id).orElse(null);
	}

	@Override
	public ContAllPerson save(ContAllPerson contAllPerson) {
		return contAllPersonaDao.save(contAllPerson);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoAllPersona> findTipoAllPersonaAll() {
		return tipoAllPersonaDao.findAll();
	}


	
	@Override
	public ContAllPerson findPorDocumentoDrt(String codigo) {
		return contAllPersonaDao.findPorDocumentoDrt(codigo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ContAllPerson> filtrarPorCodigo(String cod) {
          String cad = cod.charAt(0)+"";   
		  System.out.println("LA CADENA ES = "+cad);
		  try {
				Integer.parseInt(cad);
				 
		         return contAllPersonaDao.filtrarPorCodigo(cod);
				
				 
			} catch (NumberFormatException e) {
				 return contAllPersonaDao.filtrarPorNombre(cod); 

			}
		  
		
		  
	}
	

	
}
