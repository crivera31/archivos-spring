package com.sprinboot.servicios.app.oriplan.sis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.oriplan.sis.dao.SistemaPensionesDao;
import com.sprinboot.servicios.oriplan.commons.entity.SistemaPensiones;
import com.sprinboot.servicios.oriplan.commons.services.ObjectService;

@Service
public class SistemaPensionesServiceImp extends ObjectService<SistemaPensiones, SistemaPensionesDao > implements SistemaPensionesInterface {

	@Autowired
	SistemaPensionesDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public SistemaPensiones buscarNombre(String codigo) {
		return dao.buscarNombre(codigo);
	}
	

}
