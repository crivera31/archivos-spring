package com.sprinboot.servicios.app.admin.base.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.admin.base.models.dao.IDrtPersJuridicaDao;
import com.sprinboot.servicios.app.admin.base.models.dao.ITipoDocumentoDao;
import com.sprinboot.servicios.app.otros.commons.models.entity.DrtPersJuridica;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoDocumento;
@Service
public class ContAllPersonService implements IContAllPersonServiceImpl {

	private Logger logger = LoggerFactory.getLogger(ContAllPersonService.class);
	
	
	@Autowired
	private IDrtPersJuridicaDao drtPersJuridicaDao;
	
	
	
	@Autowired
	private ITipoDocumentoDao tipoDocumentoDao;
	

	


	@Override
	@Transactional(readOnly=true)
	public List<TipoDocumento> findTipoDocumentoAll() {
		return tipoDocumentoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<DrtPersJuridica> findByDrtPersJuridicaEnabledPaged(Pageable pageable) {
		return drtPersJuridicaDao.findByEnabledPaged(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public List<DrtPersJuridica> findDrtPersJuridicaAll() {
		return drtPersJuridicaDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public DrtPersJuridica findByCodigo(String codigo) {
		return drtPersJuridicaDao.buscarPorPersonaJuridica(codigo.trim());
	}

	@Override
	@Transactional
	public DrtPersJuridica save(DrtPersJuridica drtPersJuridica) {
		return drtPersJuridicaDao.save(drtPersJuridica);
	}

	@Override
	public DrtPersJuridica findByDrtPersJuridicaId(Integer id) {
		return drtPersJuridicaDao.buscarPorPersonaJuridicaId(id);
	}

	

	
}
