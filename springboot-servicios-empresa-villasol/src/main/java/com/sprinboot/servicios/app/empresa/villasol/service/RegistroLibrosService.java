package com.sprinboot.servicios.app.empresa.villasol.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.empresa.villasol.dao.LibroDao;
import com.sprinboot.servicios.app.empresa.villasol.dao.RegistroLibrosDao;
import com.sprinboot.servicios.app.empresa.villasol.dto.FiltroIgvDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.RegistroComprasDetDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.RegistroComprasDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.RegistroComprasSubDetDto;
import com.sprinboot.servicios.app.empresa.villasol.funciones.Funciones;
import com.sprinboot.servicios.empresa.commons.entity.Libro;
import com.sprinboot.servicios.empresa.commons.entity.RegistroLibros;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;

@Service
public class RegistroLibrosService implements RegistroLibrosServiceInterface{

	@Autowired
	RegistroLibrosDao registroLibrosDao;
	
	@Autowired
	LibroDao libroDao;
	
	@Autowired
	Funciones funciones;
	
	@Override
	@Transactional
	public RegistroLibros save (RegistroLibros registroLibros) {
		return registroLibrosDao.save(registroLibros);
	}; 
	
	@Override
	@Transactional
	public RegistroLibros saveManual (RegistroLibros request) {
		return registroLibrosDao.save(request);
	}

	@Override
	public List<Libro> findByAllEnabled() {
		return libroDao.findAllEnabeld();
	}; 
		
	@Override
	public List<RegistroLibros> listarRegistroLibrosPorFiltro(FiltroIgvDto fitrodto) {
	return registroLibrosDao.listarRegistroLibrosPorFiltro(fitrodto.getFecha(),fitrodto.getVenc(),fitrodto.getDoc(),fitrodto.getNumero(),fitrodto.getCodRuc());
	}; 
	
	@Override
	public RegistroComprasDetDto listarRegistroDeCompras(String doc, Integer idperiodo, Integer codmes, String anio,Integer decimales ,String codLibro,String codParametro) {
	List<RegistroLibros> listreglib = registroLibrosDao.listarRegistroDeCompras(doc, idperiodo, codmes, anio, codLibro,codParametro);	
	System.out.println("OBTENIENDO LA LISTA "+listreglib);
	//RegistroComprasDto registroComprasDto= new RegistroComprasDto();   
	List<RegistroComprasSubDetDto> lstsubdet = new ArrayList<RegistroComprasSubDetDto>();
	RegistroComprasDetDto det = new RegistroComprasDetDto();
 
	if(listreglib.size()>0) {
		 System.out.println("no ES NULL");
		 for (RegistroLibros registroLibros : listreglib) {
				RegistroComprasSubDetDto subdet = new RegistroComprasSubDetDto();   
				subdet = this.ingresoDatosToDto(registroLibros);
				lstsubdet.add(subdet);
					System.out.println("INGRESO A LA LISTA");
				det.setAdqNoGrav(funciones.sumarRedondear(subdet.getAdqNoGrav(),det.getAdqNoGrav() , decimales));
				det.setAdqSinDer(funciones.sumarRedondear(subdet.getAdqSinDer(), det.getAdqSinDer(), decimales));
				det.setBaseImpAdq(funciones.sumarRedondear(subdet.getBaseImpAdq(), det.getBaseImpAdq(), decimales));
				det.setBaseImpOpe(funciones.sumarRedondear(subdet.getBaseImpOpe(), det.getBaseImpOpe(), decimales));
				det.setExonerado(funciones.sumarRedondear(subdet.getExonerado(), det.getExonerado(), decimales));
				det.setIgvBaseAdqSinDer(funciones.sumarRedondear(subdet.getIgvBaseAdqSinDer(), det.getIgvBaseAdqSinDer(), decimales));
				det.setIgvBaseImpAdq(funciones.sumarRedondear(subdet.getIgvBaseImpAdq(), det.getIgvBaseImpAdq(), decimales));
				det.setIgvBaseImpOpe(funciones.sumarRedondear(subdet.getIgvBaseImpOpe(), det.getIgvBaseImpOpe(), decimales));
				det.setInafecto(funciones.sumarRedondear(subdet.getInafecto(), det.getInafecto(), decimales));
				det.setIsc(funciones.sumarRedondear(subdet.getIsc(), det.getIsc(), decimales));
				det.setOtrosTributos(funciones.sumarRedondear(subdet.getOtrosTributos(), det.getOtrosTributos(), decimales));
				det.setValorExportacion(funciones.sumarRedondear(subdet.getValorExportacion(), det.getValorExportacion(), decimales));
				det.setTotal(funciones.sumarRedondear(subdet.getTotal(), det.getTotal(), decimales));
				det.setCodDocumento(registroLibros.getVoucher().getCodDocumento());
				
		 }
		 	det.setListcompra(lstsubdet);
			return det;
		
	}else {
		   System.out.println("ES NULL");
		   return null;
	}
	 
	};
	
	public RegistroComprasSubDetDto ingresoDatosToDto(RegistroLibros r) {
		
		
		RegistroComprasSubDetDto registroComprassubdetDto = new RegistroComprasSubDetDto();   
		if(r.getVoucher().getAbreNomMoneda().equalsIgnoreCase("D")) {
			r.setAdqNoGrav(funciones.multiRedondear(r.getAdqNoGrav(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setAdqSinDer(funciones.multiRedondear(r.getAdqSinDer(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setBaseImpAdq(funciones.multiRedondear(r.getBaseImpAdq(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setBaseImpOpe(funciones.multiRedondear(r.getBaseImpOpe(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setExonerado(funciones.multiRedondear(r.getExonerado(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setIgvBaseAdqSinDer(funciones.multiRedondear(r.getIgvBaseAdqSinDer(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setIgvBaseImpAdq(funciones.multiRedondear(r.getIgvBaseImpAdq(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setIgvBaseImpOpe(funciones.multiRedondear(r.getIgvBaseImpOpe(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setIsc(funciones.multiRedondear(r.getIsc(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setInafecto(funciones.multiRedondear(r.getInafecto(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setOtrosTributos(funciones.multiRedondear(r.getOtrosTributos(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setValorExportacion(funciones.multiRedondear(r.getValorExportacion(),new BigDecimal(r.getVoucher().getTipoCambio()) ,2));
			r.setTotalMonto(funciones.sumarRedondearTotalLibro(r.getExonerado(),r.getIgvBaseAdqSinDer(),r.getIgvBaseImpAdq(),r.getIgvBaseImpOpe(),r.getInafecto(),r.getIsc(),r.getOtrosTributos(),r.getValorExportacion(),r.getAdqNoGrav(),r.getAdqSinDer(),r.getBaseImpAdq(),r.getBaseImpOpe() ,2));
			r.getVoucher().setTipoCambio(""+funciones.sumarRedondear(new BigDecimal(r.getVoucher().getTipoCambio()), null, 3));
			registroComprassubdetDto.setTipoCambio(r.getVoucher().getTipoCambio());

		}else {
			registroComprassubdetDto.setTipoCambio("1.000");

		}
		
		registroComprassubdetDto.setMoneda(r.getVoucher().getAbreNomMoneda());
	    registroComprassubdetDto.setCodOrigen(r.getVoucher().getAsiento().getCodOrigen());
		registroComprassubdetDto.setNumAsiento(r.getVoucher().getAsiento().getNumAsiento());
		registroComprassubdetDto.setId(r.getId());
		registroComprassubdetDto.setAdqNoGrav(r.getAdqNoGrav());
		registroComprassubdetDto.setAdqSinDer(r.getAdqSinDer());
		registroComprassubdetDto.setBaseImpAdq(r.getBaseImpAdq());
		registroComprassubdetDto.setBaseImpOpe(r.getBaseImpOpe());
		registroComprassubdetDto.setExonerado(r.getExonerado());
		registroComprassubdetDto.setIgvBaseAdqSinDer(r.getIgvBaseAdqSinDer());
		registroComprassubdetDto.setIgvBaseImpAdq(r.getIgvBaseImpAdq());
		registroComprassubdetDto.setIgvBaseImpOpe(r.getIgvBaseImpOpe());
		registroComprassubdetDto.setIsc(r.getIsc());
		registroComprassubdetDto.setInafecto(r.getInafecto());
		registroComprassubdetDto.setOtrosTributos(r.getOtrosTributos());
		registroComprassubdetDto.setTotal(r.getTotalMonto());
		registroComprassubdetDto.setValorExportacion(r.getValorExportacion());
		
		registroComprassubdetDto.setCodRuc(r.getVoucher().getCodRuc());
		registroComprassubdetDto.setCodTipoDoc(r.getVoucher().getCodTipoDoc());
		
		registroComprassubdetDto.setFechaEmision(funciones.getFechaToString(r.getVoucher().getFechaEmision()));
		registroComprassubdetDto.setFechaVencimiento(funciones.getFechaToString(r.getVoucher().getFechaVencimiento()));
		registroComprassubdetDto.setRazonSocial(funciones.getTrim(r.getVoucher().getRazonSocial()));
		registroComprassubdetDto.setSerie(funciones.getSerie(r.getVoucher().getSerieNumero()));
		registroComprassubdetDto.setComprobantePago(funciones.getNumero(r.getVoucher().getSerieNumero()));
		registroComprassubdetDto.setNumVoucher(r.getLibro().getCodLibro() + " - " + r.getVoucher().getAsiento().getNumAsiento());
		
		registroComprassubdetDto.setCodDocumento(r.getVoucher().getCodDocumento());
		
		if(r.getVoucher().getVoucherRef()!=null) {
				if (r.getVoucher().getVoucherRef().getEstadoRef()==1) {
					if(r.getVoucher().getVoucherRef().getdFechaRef()!=null) {
						registroComprassubdetDto.setdFechaRef(funciones.getFechaToString(r.getVoucher().getVoucherRef().getdFechaRef()));

					}
					if(r.getVoucher().getVoucherRef().getFechaRef()!=null) {
						registroComprassubdetDto.setFechaRef(funciones.getFechaToString(r.getVoucher().getVoucherRef().getFechaRef()));
					}
					if(r.getVoucher().getVoucherRef().getNumeroRef()!=null) {
						registroComprassubdetDto.setNumeroRef(funciones.getSerie(r.getVoucher().getVoucherRef().getNumeroRef()));
						registroComprassubdetDto.setComprobanteRef(funciones.getNumero(r.getVoucher().getVoucherRef().getNumeroRef()));

					}
				registroComprassubdetDto.setdNumeroRef(r.getVoucher().getVoucherRef().getdNumeroRef());
				registroComprassubdetDto.setCodDocumentoRef(r.getVoucher().getVoucherRef().getCodDocument());
				}
		}
		
		return registroComprassubdetDto;
	}
	
	
	@Override
	public RegistroComprasDto calcularTotales(RegistroComprasDto det,Integer decimales) {
		if(det.getListRegistroCompraDet()!=null) {
			 System.out.println("total no ES NULL");
			 for (RegistroComprasDetDto subdet : det.getListRegistroCompraDet()) {				
					det.setAdqNoGrav(funciones.sumarRedondear(subdet.getAdqNoGrav(),det.getAdqNoGrav() , decimales));
					det.setAdqSinDer(funciones.sumarRedondear(subdet.getAdqSinDer(), det.getAdqSinDer(), decimales));
					det.setBaseImpAdq(funciones.sumarRedondear(subdet.getBaseImpAdq(), det.getBaseImpAdq(), decimales));
					det.setBaseImpOpe(funciones.sumarRedondear(subdet.getBaseImpOpe(), det.getBaseImpOpe(), decimales));
					det.setExonerado(funciones.sumarRedondear(subdet.getExonerado(), det.getExonerado(), decimales));
					det.setIgvBaseAdqSinDer(funciones.sumarRedondear(subdet.getIgvBaseAdqSinDer(), det.getIgvBaseAdqSinDer(), decimales));
					det.setIgvBaseImpAdq(funciones.sumarRedondear(subdet.getIgvBaseImpAdq(), det.getIgvBaseImpAdq(), decimales));
					det.setIgvBaseImpOpe(funciones.sumarRedondear(subdet.getIgvBaseImpOpe(), det.getIgvBaseImpOpe(), decimales));
					det.setInafecto(funciones.sumarRedondear(subdet.getInafecto(), det.getInafecto(), decimales));
					det.setIsc(funciones.sumarRedondear(subdet.getIsc(), det.getIsc(), decimales));
					det.setOtrosTributos(funciones.sumarRedondear(subdet.getOtrosTributos(), det.getOtrosTributos(), decimales));
					det.setValorExportacion(funciones.sumarRedondear(subdet.getValorExportacion(), det.getValorExportacion(), decimales));
					det.setTotal(funciones.sumarRedondear(subdet.getTotal(), det.getTotal(), decimales));
			 }	 	
				return det;
		}else {
			   System.out.println("totales ES NULL");
			   return det;
		}
	}
		
	@Override
	@Transactional
	public List<RegistroLibros> buscarVoucher(String serie,String ruc,String codDocumento) {
		return registroLibrosDao.buscarFactura(serie, ruc, codDocumento);
	}
	
	
	
	@Override
	public RegistroComprasDetDto listarRegistroAuxiliares (String doc, Integer idperiodo, Integer codmes, String anio,Integer decimales ,String codLibro,String codParametro, String codLibroAuxiliar, String codObra) {
	List<RegistroLibros> listreglib = registroLibrosDao.listarRegistrosLibrosAuxiliares(doc, idperiodo, codmes, anio, codLibro,codParametro,codLibroAuxiliar,codObra);	
	System.out.println("OBTENIENDO LA LISTA "+listreglib);
	//RegistroComprasDto registroComprasDto= new RegistroComprasDto();   
	List<RegistroComprasSubDetDto> lstsubdet = new ArrayList<RegistroComprasSubDetDto>();
	RegistroComprasDetDto det = new RegistroComprasDetDto();
 
	if(listreglib.size()>0) {
		 System.out.println("no ES NULL");
		 for (RegistroLibros registroLibros : listreglib) {
				RegistroComprasSubDetDto subdet = new RegistroComprasSubDetDto();   
				subdet = this.ingresoDatosToDto(registroLibros);
				lstsubdet.add(subdet);
					System.out.println("INGRESO A LA LISTA");
				det.setAdqNoGrav(funciones.sumarRedondear(subdet.getAdqNoGrav(),det.getAdqNoGrav() , decimales));
				det.setAdqSinDer(funciones.sumarRedondear(subdet.getAdqSinDer(), det.getAdqSinDer(), decimales));
				det.setBaseImpAdq(funciones.sumarRedondear(subdet.getBaseImpAdq(), det.getBaseImpAdq(), decimales));
				det.setBaseImpOpe(funciones.sumarRedondear(subdet.getBaseImpOpe(), det.getBaseImpOpe(), decimales));
				det.setExonerado(funciones.sumarRedondear(subdet.getExonerado(), det.getExonerado(), decimales));
				det.setIgvBaseAdqSinDer(funciones.sumarRedondear(subdet.getIgvBaseAdqSinDer(), det.getIgvBaseAdqSinDer(), decimales));
				det.setIgvBaseImpAdq(funciones.sumarRedondear(subdet.getIgvBaseImpAdq(), det.getIgvBaseImpAdq(), decimales));
				det.setIgvBaseImpOpe(funciones.sumarRedondear(subdet.getIgvBaseImpOpe(), det.getIgvBaseImpOpe(), decimales));
				det.setInafecto(funciones.sumarRedondear(subdet.getInafecto(), det.getInafecto(), decimales));
				det.setIsc(funciones.sumarRedondear(subdet.getIsc(), det.getIsc(), decimales));
				det.setOtrosTributos(funciones.sumarRedondear(subdet.getOtrosTributos(), det.getOtrosTributos(), decimales));
				det.setValorExportacion(funciones.sumarRedondear(subdet.getValorExportacion(), det.getValorExportacion(), decimales));
				det.setTotal(funciones.sumarRedondear(subdet.getTotal(), det.getTotal(), decimales));
				det.setCodDocumento(registroLibros.getVoucher().getCodDocumento());
				
		 }
		 	det.setListcompra(lstsubdet);
			return det;
		
	}else {
		   System.out.println("ES NULL");
		   return null;
	}
	 
	};

}
