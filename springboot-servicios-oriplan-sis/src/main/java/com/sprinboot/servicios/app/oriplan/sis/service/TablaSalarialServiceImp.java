package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.oriplan.sis.dao.SctrDao;
import com.sprinboot.servicios.app.oriplan.sis.dao.TablaSalarialDao;
import com.sprinboot.servicios.app.oriplan.sis.dao.TasaPensionesDao;
import com.sprinboot.servicios.oriplan.commons.entity.Sctr;
import com.sprinboot.servicios.oriplan.commons.entity.TablaSalarial;
import com.sprinboot.servicios.oriplan.commons.entity.TasasPensiones;
import com.sprinboot.servicios.oriplan.commons.services.ObjectService;

@Service
public class TablaSalarialServiceImp extends ObjectService<TablaSalarial, TablaSalarialDao > implements TablaSalarialInterface {

	@Autowired
	TablaSalarialDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TablaSalarial> listar() {
		return dao.listar();
	}
	
	@Override
	@Transactional(readOnly = true)
	public TablaSalarial porCategoria(String categoria) {
		return dao.porCategoria(categoria);
	}
	
}
