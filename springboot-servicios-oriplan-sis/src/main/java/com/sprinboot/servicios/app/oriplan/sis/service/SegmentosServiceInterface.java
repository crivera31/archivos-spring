package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;

import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;

public interface SegmentosServiceInterface {

	public List<Segmentos> getAllSegmentos();
	public Segmentos save(Segmentos entity);
	public Segmentos findByNombre(String name);
	public Segmentos findById(Integer id);
	public boolean verificarRepetcion(String name);
	
}
