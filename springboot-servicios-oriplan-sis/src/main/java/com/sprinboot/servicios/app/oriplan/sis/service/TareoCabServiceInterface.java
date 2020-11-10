package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.List;

import com.sprinboot.servicios.oriplan.commons.entity.TareoCab;
import com.sprinboot.servicios.oriplan.commons.services.IObjectService;

public interface TareoCabServiceInterface extends IObjectService<TareoCab> {

	public List<TareoCab> listar();

	public boolean verificarRepetcion(String name);

	public TareoCab guardar(TareoCab x);

	public TareoCab buscar(String name, Integer idobra);
	
	public List<TareoCab> getTareoSemObra(String semana, Integer obra);
	
	public TareoCab actualizar(TareoCab tareoCab);

}
