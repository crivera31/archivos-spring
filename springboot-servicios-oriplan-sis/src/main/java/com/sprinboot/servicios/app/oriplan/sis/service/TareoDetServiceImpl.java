package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.oriplan.sis.dao.SegmentosDao;
import com.sprinboot.servicios.app.oriplan.sis.dao.TablaSalarialDao;
import com.sprinboot.servicios.app.oriplan.sis.dao.TareoDetDao;
import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;
import com.sprinboot.servicios.oriplan.commons.entity.TablaSalarial;
import com.sprinboot.servicios.oriplan.commons.entity.TareoDet;
import com.sprinboot.servicios.oriplan.commons.services.ObjectService;

@Service
public class TareoDetServiceImpl extends ObjectService<TareoDet, TareoDetDao > implements TareoDetServiceInterface {

	@Autowired
	TareoDetDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TareoDet> listar() {
		return dao.listar();
	}
	
	
	
	

}
