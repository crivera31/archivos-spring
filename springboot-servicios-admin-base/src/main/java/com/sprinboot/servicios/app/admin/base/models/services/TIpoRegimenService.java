package com.sprinboot.servicios.app.admin.base.models.services;

import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.admin.base.models.dao.ITipoRegimenDao;
import com.sprinboot.servicios.app.commons.services.ObjectService;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoRegimen;

@Service
public class TIpoRegimenService extends ObjectService<TipoRegimen, ITipoRegimenDao > implements ITipoRegimenService{

	
}
