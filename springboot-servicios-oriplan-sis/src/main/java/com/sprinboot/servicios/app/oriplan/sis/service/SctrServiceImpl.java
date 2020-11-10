package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.oriplan.sis.dao.PlanillaExcelDao;
import com.sprinboot.servicios.app.oriplan.sis.dao.SctrDao;
import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;
import com.sprinboot.servicios.oriplan.commons.entity.Sctr;
import com.sprinboot.servicios.oriplan.commons.entity.TablaSalarial;
import com.sprinboot.servicios.oriplan.commons.services.ObjectService;

@Service
public class SctrServiceImpl extends ObjectService<Sctr, SctrDao > implements SctrServiceInterface {

	@Autowired
	SctrDao dao ; 
	
	@Override
	@Transactional(readOnly = true)
	public List<Sctr> listar() {
		return dao.listar();
	}

	@Override
	public Sctr buscar() {
		// TODO Auto-generated method stub
		return dao.buscar();
	}
}
