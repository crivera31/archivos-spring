package com.sprinboot.servicios.app.admin.base.models.services;

import java.util.List;
import java.util.Optional;



import com.sprinboot.servicios.app.commons.services.IObjectService;
import com.sprinboot.servicios.app.otros.commons.models.entity.ConfigEmpresa;

public interface IConfigEmpresaService extends IObjectService<ConfigEmpresa>{

	
	public Optional<ConfigEmpresa> findByNombreEmpresa(String nomempresa);
	public List<ConfigEmpresa> getListEmpresaPorAnioActual(String anio);
}
