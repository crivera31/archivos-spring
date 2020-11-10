package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.oriplan.sis.dao.SegmentosDao;
import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;
import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;

@Service
public class SegmentosServiceImp implements SegmentosServiceInterface {

	@Autowired
	SegmentosDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Segmentos> getAllSegmentos() {
		return dao.listSegmentos();
	}
	
	@Override
	@Transactional
	public Segmentos save(Segmentos entity) {
		return dao.save(entity);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Segmentos findByNombre(String name) {
		return dao.findByNombre(name.trim());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Segmentos findById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public boolean verificarRepetcion(String name) {
		Segmentos entidad = null;
		entidad = dao.findByNombre(name.trim());
		return entidad!=null  ;
	}
	

	
}
