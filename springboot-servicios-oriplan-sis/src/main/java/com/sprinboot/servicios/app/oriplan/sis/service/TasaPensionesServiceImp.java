package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.oriplan.sis.dao.TablaSalarialDao;
import com.sprinboot.servicios.app.oriplan.sis.dao.TasaPensionesDao;
import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;
import com.sprinboot.servicios.oriplan.commons.entity.TablaSalarial;
import com.sprinboot.servicios.oriplan.commons.entity.TasasPensiones;
import com.sprinboot.servicios.oriplan.commons.services.ObjectService;

@Service
public class TasaPensionesServiceImp extends ObjectService<TasasPensiones, TasaPensionesDao > implements TasaPensionesServiceInterface {

	@Autowired
	TasaPensionesDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TasasPensiones> listarTasasPensiones() {
		return dao.listarTasasPensiones();
	}
	
	@Override
	@Transactional(readOnly = true)
	public TasasPensiones buscar(String codigo) {
		return dao.buscar(codigo);
	}
	
	@Override
	@Transactional(readOnly = true)
	public TasasPensiones buscarNombre(String codigo) {
		return dao.buscarNombre(codigo);
	}
	
	
}
