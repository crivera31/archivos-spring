package com.sprinboot.servicios.app.empresa.constructores.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.empresa.constructores.dao.RegistroLibrosDao;
import com.sprinboot.servicios.app.empresa.constructores.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.constructores.funciones.Funciones;
import com.sprinboot.servicios.app.empresa.constructores.jsons.RegistrosCabRest;
import com.sprinboot.servicios.app.empresa.constructores.jsons.RegistrosDetRest;
import com.sprinboot.servicios.app.empresa.constructores.solid.OpcionReporte;
import com.sprinboot.servicios.empresa.commons.entity.RegistroLibros;

@Service
public class RegistrosSistemaService implements RegistrosSistemaServiceInterface {
	Logger logger = LoggerFactory.getLogger(RegistrosSistemaService.class);
	
	@Autowired
	RegistroLibrosDao registroLibroDao;
	
	@Autowired
	Funciones funciones;
	
	@Override
	public RegistroLibrosDao getDao() {
		return registroLibroDao;
	}
	
	@Override
	@Transactional
	public RegistrosCabRest reporteRegistros(List<OpcionReporte> lstOpcion , Integer idperiodo,String anio,Integer codmes,String codlibro, String codPlanParametro) {
	   
		RegistrosCabRest registrosCabRest = new RegistrosCabRest(); 
		List<RegistrosDetRest> listDet= new ArrayList<RegistrosDetRest>();
		registrosCabRest.setLstRegistrosDetRest(listDet);
		for (OpcionReporte opcionReporte : lstOpcion) {
			    registrosCabRest =   opcionReporte.proceso(idperiodo, anio, codmes,codlibro,codPlanParametro);
		  }
		return calcularTotales(registrosCabRest,2);
	}
	
	
	@Override
	 public RegistrosDetRest getDatosLibro(RegistroLibros r,String moneda) throws ClaseException {
		 RegistrosDetRest registrosDetRest = new RegistrosDetRest();   
		 registrosDetRest.setCodMes(funciones.getFomartoMes(r.getVoucher().getAsiento().getCodMes()));
		 registrosDetRest.setMoneda(r.getVoucher().getAbreNomMoneda());
		 registrosDetRest.setCodOrigen(r.getVoucher().getAsiento().getCodOrigen());
		 registrosDetRest.setNumAsiento(r.getVoucher().getAsiento().getNumAsiento());
			registrosDetRest.setAdqNoGrav(r.getAdqNoGrav());
			registrosDetRest.setAdqSinDer(r.getAdqSinDer());
			registrosDetRest.setBaseImpAdq(r.getBaseImpAdq());
			registrosDetRest.setBaseImpOpe(r.getBaseImpOpe());
			registrosDetRest.setExonerado(r.getExonerado());
			registrosDetRest.setIgvBaseAdqSinDer(r.getIgvBaseAdqSinDer());
			registrosDetRest.setIgvBaseImpAdq(r.getIgvBaseImpAdq());
			registrosDetRest.setIgvBaseImpOpe(r.getIgvBaseImpOpe());
			registrosDetRest.setIsc(r.getIsc());
			registrosDetRest.setInafecto(r.getInafecto());
			registrosDetRest.setOtrosTributos(r.getOtrosTributos());
			registrosDetRest.setTotal(r.getTotalMonto());
			registrosDetRest.setValorExportacion(r.getValorExportacion());
			registrosDetRest.setCodRuc(r.getVoucher().getCodRuc());
			registrosDetRest.setCodTipoDoc(r.getVoucher().getCodTipoDoc());
			registrosDetRest.setFechaEmision(funciones.getFechaToString(r.getVoucher().getFechaEmision()));
			registrosDetRest.setFechaVencimiento(funciones.getFechaToString(r.getVoucher().getFechaVencimiento()));
			registrosDetRest.setRazonSocial(funciones.getTrim(r.getVoucher().getRazonSocial()));
			registrosDetRest.setSerie(funciones.getSerie(r.getVoucher().getSerieNumero()));
			registrosDetRest.setNumero(funciones.getNumero(r.getVoucher().getSerieNumero()));
			registrosDetRest.setComprobantePago(r.getVoucher().getCodDocumento());
			registrosDetRest.setGlosario(r.getVoucher().getGlosario());
			
			registrosDetRest.setTipoCambio(r.getVoucher().getAbreNomMoneda().compareTo(moneda)==0 ? "1.000" :r.getVoucher().getTipoCambio());
			registrosDetRest.setTipoCambioRedondeado(r.getVoucher().getAbreNomMoneda().compareTo(moneda)==0 ? "1.000" :""+funciones.sumarRedondear(new BigDecimal(r.getVoucher().getTipoCambio()), null, 3));
			//registrosDetRest.setNumVoucher(r.getLibro().getCodLibro() + " - " + r.getVoucher().getAsiento().getNumAsiento());
			if(r.getVoucher().getVoucherRef()!=null) {
					if (r.getVoucher().getVoucherRef().getEstadoRef()==1) {
						if(r.getVoucher().getVoucherRef().getdFechaRef()!=null) {
							registrosDetRest.setdFechaRef(funciones.getFechaToString(r.getVoucher().getVoucherRef().getdFechaRef()));
						}
						if(r.getVoucher().getVoucherRef().getFechaRef()!=null) {
							registrosDetRest.setFechaRef(funciones.getFechaToString(r.getVoucher().getVoucherRef().getFechaRef()));
						}
						if(r.getVoucher().getVoucherRef().getNumeroRef()!=null) {
							registrosDetRest.setNumeroRef(funciones.getSerie(r.getVoucher().getVoucherRef().getNumeroRef()));
							registrosDetRest.setComprobanteRef(funciones.getNumero(r.getVoucher().getVoucherRef().getNumeroRef()));
						}
						registrosDetRest.setdNumeroRef(r.getVoucher().getVoucherRef().getdNumeroRef());
						registrosDetRest.setCodDocumentoRef(r.getVoucher().getVoucherRef().getCodDocument());
					}
			}
			return registrosDetRest;
	}
	
	

	
	@Override
	 public RegistrosCabRest getRegistroCabRest(List<RegistroLibros> lista,String moneda) {
		List<RegistrosDetRest> lsdet = new ArrayList<RegistrosDetRest>(); 
		lista.stream().map(r ->  {
			            try {
							lsdet.add(getDatosLibro(r,moneda));
						} catch (ClaseException e) {
							e.printStackTrace();
							logger.info("ERROR EN CREAR REST JASON REGISTROCABREST - "+e.getMessage());
						} 
			               return r;
		  }).collect(Collectors.toList());
		   
	     RegistrosCabRest cab = new RegistrosCabRest();  	
		 cab.setLstRegistrosDetRest(lsdet); 
	     return cab ;
	}
	
	@Override
	 public RegistrosDetRest getDolaresToSoles(RegistrosDetRest r,Integer decimales) throws ClaseException {
		   //return listDet.stream().map(r ->  {
			     r.setAdqNoGrav(funciones.multiRedondear(r.getAdqNoGrav(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setAdqSinDer(funciones.multiRedondear(r.getAdqSinDer(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setBaseImpAdq(funciones.multiRedondear(r.getBaseImpAdq(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setBaseImpOpe(funciones.multiRedondear(r.getBaseImpOpe(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setExonerado(funciones.multiRedondear(r.getExonerado(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setIgvBaseAdqSinDer(funciones.multiRedondear(r.getIgvBaseAdqSinDer(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setIgvBaseImpAdq(funciones.multiRedondear(r.getIgvBaseImpAdq(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setIgvBaseImpOpe(funciones.multiRedondear(r.getIgvBaseImpOpe(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setInafecto(funciones.multiRedondear(r.getInafecto(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setIsc(funciones.multiRedondear(r.getIsc(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setOtrosTributos(funciones.multiRedondear(r.getOtrosTributos(),new BigDecimal(r.getTipoCambio()) ,decimales));
			     r.setValorExportacion(funciones.multiRedondear(r.getValorExportacion(),new BigDecimal(r.getTipoCambio()) ,decimales));
			     r.setTotal(funciones.sumarRedondearTotalLibro(r.getExonerado(),r.getIgvBaseAdqSinDer(),r.getIgvBaseImpAdq(),r.getIgvBaseImpOpe(),r.getInafecto(),r.getIsc(),r.getOtrosTributos(),r.getValorExportacion(),r.getAdqNoGrav(),r.getAdqSinDer(),r.getBaseImpAdq(),r.getBaseImpOpe() ,decimales));
				 r.setTipoCambio(""+funciones.sumarRedondear(new BigDecimal(r.getTipoCambio()), null, 3));

			     return r;
		  //}).collect(Collectors.toList());
	}
	
	@Override
	 public RegistrosDetRest getSolesToDolares(RegistrosDetRest r,Integer decimales) throws ClaseException {
		   //return listDet.stream().map(r ->  {
			     r.setAdqNoGrav(funciones.dividirRedondear(r.getAdqNoGrav(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setAdqSinDer(funciones.dividirRedondear(r.getAdqSinDer(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setBaseImpAdq(funciones.dividirRedondear(r.getBaseImpAdq(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setBaseImpOpe(funciones.dividirRedondear(r.getBaseImpOpe(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setExonerado(funciones.dividirRedondear(r.getExonerado(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setIgvBaseAdqSinDer(funciones.dividirRedondear(r.getIgvBaseAdqSinDer(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setIgvBaseImpAdq(funciones.dividirRedondear(r.getIgvBaseImpAdq(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setIgvBaseImpOpe(funciones.dividirRedondear(r.getIgvBaseImpOpe(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setInafecto(funciones.dividirRedondear(r.getInafecto(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setIsc(funciones.dividirRedondear(r.getIsc(),new BigDecimal(r.getTipoCambio()) , decimales));
			     r.setOtrosTributos(funciones.dividirRedondear(r.getOtrosTributos(),new BigDecimal(r.getTipoCambio()) ,decimales));
			     r.setValorExportacion(funciones.dividirRedondear(r.getValorExportacion(),new BigDecimal(r.getTipoCambio()) ,decimales));
			     r.setTotal(funciones.sumarRedondearTotalLibro(r.getExonerado(),r.getIgvBaseAdqSinDer(),r.getIgvBaseImpAdq(),r.getIgvBaseImpOpe(),r.getInafecto(),r.getIsc(),r.getOtrosTributos(),r.getValorExportacion(),r.getAdqNoGrav(),r.getAdqSinDer(),r.getBaseImpAdq(),r.getBaseImpOpe() ,decimales));
				 r.setTipoCambio(""+funciones.sumarRedondear(new BigDecimal(r.getTipoCambio()), null, 3));
			     return r;
		  //}).collect(Collectors.toList());
	}
	 
	 
	public RegistrosCabRest calcularTotales(RegistrosCabRest cab,Integer decimales) {
			if(cab.getLstRegistrosDetRest().size()> 0) {
				 System.out.println("total no ES NULL");
				 for (RegistrosDetRest det : cab.getLstRegistrosDetRest()) {				
					 cab.setAdqNoGrav(funciones.sumarRedondear(cab.getAdqNoGrav(),det.getAdqNoGrav() , decimales));
					 cab.setAdqSinDer(funciones.sumarRedondear(cab.getAdqSinDer(), det.getAdqSinDer(), decimales));
					 cab.setBaseImpAdq(funciones.sumarRedondear(cab.getBaseImpAdq(), det.getBaseImpAdq(), decimales));
					 cab.setBaseImpOpe(funciones.sumarRedondear(cab.getBaseImpOpe(), det.getBaseImpOpe(), decimales));
					 cab.setExonerado(funciones.sumarRedondear(cab.getExonerado(), det.getExonerado(), decimales));
					 cab.setIgvBaseAdqSinDer(funciones.sumarRedondear(cab.getIgvBaseAdqSinDer(), det.getIgvBaseAdqSinDer(), decimales));
					 cab.setIgvBaseImpAdq(funciones.sumarRedondear(cab.getIgvBaseImpAdq(), det.getIgvBaseImpAdq(), decimales));
					 cab.setIgvBaseImpOpe(funciones.sumarRedondear(cab.getIgvBaseImpOpe(), det.getIgvBaseImpOpe(), decimales));
					 cab.setInafecto(funciones.sumarRedondear(cab.getInafecto(), det.getInafecto(), decimales));
					 cab.setIsc(funciones.sumarRedondear(cab.getIsc(), det.getIsc(), decimales));
					 cab.setOtrosTributos(funciones.sumarRedondear(cab.getOtrosTributos(), det.getOtrosTributos(), decimales));
					 cab.setValorExportacion(funciones.sumarRedondear(cab.getValorExportacion(), det.getValorExportacion(), decimales));
					 cab.setTotal(funciones.sumarRedondear(cab.getTotal(), det.getTotal(), decimales));
				 }	 	
					return cab;
			}else {
				   System.out.println("totales ES NULL");
				   return cab;
			}
		} 

}
