package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.oriplan.sis.dao.TasaPensionesDao;
import com.sprinboot.servicios.app.oriplan.sis.dao.TrabajadoresDao;
import com.sprinboot.servicios.oriplan.commons.entity.TasasPensiones;
import com.sprinboot.servicios.oriplan.commons.entity.Trabajadores;
import com.sprinboot.servicios.oriplan.commons.services.ObjectService;

@Service
public class TrabajadoresServiceImp  extends ObjectService<Trabajadores, TrabajadoresDao >  implements TrabajadoresServiceInterface {

	
	@Autowired
	TrabajadoresDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Trabajadores> listarTrabajadoresActivos() {
		return dao.listarActivos();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Trabajadores> listarTrabajadoresBajas() {
		return dao.listarBajas();
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean buscarSinRepeticion(String dni,Date fechaIngreso) {
		return dao.buscarSinRepeticion(dni,fechaIngreso).size()>0;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Trabajadores> buscar(String dni,Date fechaIngreso) {
		return dao.buscar(dni,fechaIngreso);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean buscarTrabajadorActivo(String dni) {
		return dao.buscarTrabajadorActivo(dni.trim()).size()>0;
	}

	@Override
	public Trabajadores editarTrabajador(Trabajadores trabajadores) {
		return dao.save(trabajadores);
	}

}
