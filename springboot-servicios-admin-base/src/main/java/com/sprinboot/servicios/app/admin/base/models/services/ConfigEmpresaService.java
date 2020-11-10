package com.sprinboot.servicios.app.admin.base.models.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.admin.base.models.dao.IConfigEmpresaDao;
import com.sprinboot.servicios.app.commons.services.ObjectService;
import com.sprinboot.servicios.app.otros.commons.models.entity.ConfigEmpresa;

@Service
public class ConfigEmpresaService extends ObjectService<ConfigEmpresa, IConfigEmpresaDao >  implements IConfigEmpresaService {


	@Autowired
	IConfigEmpresaDao configEmpresaDao;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<ConfigEmpresa> findByNombreEmpresa(String nomempresa) {
		return configEmpresaDao.getNomEmpresa(nomempresa);
	}

	@Override
	public List<ConfigEmpresa> getListEmpresaPorAnioActual(String anio) {
		return configEmpresaDao.getListEmpresaPorAnioActual(anio);
	}

}
