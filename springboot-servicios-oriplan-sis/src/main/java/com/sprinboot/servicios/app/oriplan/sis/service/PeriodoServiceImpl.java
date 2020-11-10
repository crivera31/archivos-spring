package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.oriplan.sis.dao.DatosGeneralesDao;
import com.sprinboot.servicios.app.oriplan.sis.dao.PeriodoDao;
import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.Periodo;
import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;
import com.sprinboot.servicios.oriplan.commons.services.ObjectService;

@Service
public class PeriodoServiceImpl  extends ObjectService<Periodo, PeriodoDao > implements PeriodoServiceInterface {
	@Autowired
	private PeriodoDao dao;
	
	@Override
	public List<Periodo> listar() {
		return dao.listar();
	}

	@Override
	public boolean verificarRepetcion(String name) {
		Periodo entidad = null;
		entidad = dao.findByNombre(name.trim());
		return entidad!=null  ;
	}
	
	
}
