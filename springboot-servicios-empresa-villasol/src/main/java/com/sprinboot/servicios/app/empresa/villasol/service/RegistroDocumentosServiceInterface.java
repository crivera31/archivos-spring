package com.sprinboot.servicios.app.empresa.villasol.service;

import java.util.List;

import com.sprinboot.servicios.app.empresa.villasol.dto.CancelacionDocDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.FiltroIgvDto;
import com.sprinboot.servicios.empresa.commons.entity.RegistroDocumentos;
import com.sprinboot.servicios.empresa.commons.entity.RegistroLibros;

public interface RegistroDocumentosServiceInterface {

	public RegistroDocumentos save(RegistroDocumentos registroDocumentos);
	public RegistroDocumentos saveManual(RegistroDocumentos request);
	public List<CancelacionDocDto> listarByPersona (String codigo,String codOrigen,String anio);
	public List<CancelacionDocDto> obtenerCancelacion (CancelacionDocDto dto);
	public Boolean guardarCancelacion (List<CancelacionDocDto> listdto);
	public Boolean guardarCancelacionRemove (List<CancelacionDocDto> listdto) ;
	public List<CancelacionDocDto> obtenerCancelacionFull (CancelacionDocDto dto);
	public CancelacionDocDto  cancelacionDtoPorId (Integer id) ;
	public Boolean calculoPrevio (String codigo,String codPlan,String anio);
}
