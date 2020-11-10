package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.oriplan.sis.dao.DatosGeneralesDao;
import com.sprinboot.servicios.app.oriplan.sis.dao.PlanillaExcelDao;

import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;
import com.sprinboot.servicios.oriplan.commons.services.ObjectService;

@Service
public class DatosGeneralesServiceImpl extends ObjectService<DatosGenerales, DatosGeneralesDao > implements DatosGeneralesServiceInterface {

	
	@Autowired
	private DatosGeneralesDao dao;
	
	@Override
	public List<DatosGenerales> getEnabled() {
		return dao.findAllEnabeld();
	}

}
