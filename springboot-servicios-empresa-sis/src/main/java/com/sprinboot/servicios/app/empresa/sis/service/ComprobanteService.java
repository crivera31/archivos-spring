package com.sprinboot.servicios.app.empresa.sis.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.empresa.sis.client.DataServiceClient;
import com.sprinboot.servicios.app.empresa.sis.dao.AsientoDao;
import com.sprinboot.servicios.app.empresa.sis.dao.ComprobanteDao;
import com.sprinboot.servicios.app.empresa.sis.dao.VoucherDao;
import com.sprinboot.servicios.app.empresa.sis.dto.PersonaDto;
import com.sprinboot.servicios.app.empresa.sis.funciones.Funciones;
import com.sprinboot.servicios.empresa.commons.entity.Asiento;
import com.sprinboot.servicios.empresa.commons.entity.Comprobante;
import com.sprinboot.servicios.empresa.commons.entity.ComprobanteDet;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;
import com.sprinboot.servicios.empresa.commons.services.ObjectService;

@Service
public class ComprobanteService extends ObjectService<Comprobante, ComprobanteDao > implements ComprobanteServiceInterface {

	@Autowired
	AsientoService asientoService;
	
	@Autowired
	AsientoDao asientoDao;
	
	@Autowired
	VoucherDao voucherDao;
	
	@Autowired
	ComprobanteDao comprobanteDao;
	
	@Autowired
	Funciones funciones;
	
	@Autowired
    DataServiceClient dataServiceClient; 
	
	@Override
	@Transactional
	public Comprobante saveGuardarComprobante(Comprobante request) throws Exception {		
		   List<ComprobanteDet> listadet = new ArrayList<ComprobanteDet>();
		   listadet = request.getLstComprobanteDet();
	       request.setLstComprobanteDet(null);
	       Comprobante nuevo = comprobanteDao.save(request);
	       List<ComprobanteDet> nuevalista = new ArrayList<ComprobanteDet>();
		  if (listadet.size()>0) {
			  for (ComprobanteDet object : listadet) {
				  object.setComprobante(nuevo);
				  nuevalista.add(object);
			 }
		  } else {
			throw new Exception();
		}
		  nuevo.setLstComprobanteDet(nuevalista);
		  nuevo =comprobanteDao.save(nuevo);
      return nuevo;
	}
 
	@Override
	@Transactional(readOnly=true)
	public List<Asiento> comprobantesSinRegistrar(String anio) {		
		  List<Asiento> lstAsiento = new ArrayList<>();
		  List<Asiento> lstSinRegistrar = new ArrayList<>();
		  lstAsiento =  asientoService.comprobantesSinRegistrar(anio);
		  if (lstAsiento.size()>0) {
			  lstAsiento.stream().forEach(object->{		      
		        	     if (comprobanteDao.buscarComprobante(object.getNumComprobante())==null) {
							lstSinRegistrar.add(object);
						}
		        });
		  } 
      return lstSinRegistrar;
	}
 
	@Override
	@Transactional(readOnly=true)
	public List<Asiento> listarAsientosPorNumComprobante(Integer numComprobante , String anio) {
		List<Asiento> lstAsientoEntity  = new ArrayList<>();
	    lstAsientoEntity  = asientoDao.listarAsientosPorNumComprobante(numComprobante, anio);
	    List<Asiento> nuevaLista  = new ArrayList<>();
	     if (lstAsientoEntity.size()>0) {
			lstAsientoEntity.stream().forEach(entidad->{
			    List<Voucher> lstVoucher  = new ArrayList<>();
				Asiento asiento2 = new Asiento();
				asiento2 = entidad;
				lstVoucher = voucherDao.listarVouchersByAsiento(entidad.getId());
				
				asiento2.setLstVoucher(voucherSinAsiento(lstVoucher));
				nuevaLista.add(asiento2);
			});
		}
		return nuevaLista ;
	}
	
	
	public List<Voucher> voucherSinAsiento(List<Voucher> lista){
		  List<Voucher> lstVoucher  = new ArrayList<>();
		  if (lista.size()>0) {
			  lista.stream().forEach(voucher->{
				  Voucher v = new Voucher();
				  v = voucher;
				  v.setAsiento(null);
				  v.setRegistroDocumento(null);
				  v.setVoucherRef(null);
				  v.setRegistroLibro(null);
				  lstVoucher.add(v);
			  });
		  }
		  
		return lstVoucher;
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public BigDecimal sumaAsientos(List<Asiento> lista,String caja , String bancos){
		Voucher suma = new Voucher();
		suma.setHaber(new BigDecimal("0.00"));
		  if (lista.size()>0) {
			  lista.stream().forEach(asiento->{
				 asiento.getLstVoucher().stream().forEach(voucher->{
					 if (funciones.getEsIgual( voucher.getCodPlan().trim() , bancos)) {
						    suma.setHaber(funciones.sumarRedondear(suma.getHaber(), voucher.getAbreNomMoneda().trim().compareTo("S")==0? voucher.getHaber():voucher.getEquivalente() , 6));  
					 }else {
						 if (funciones.getEsIgual(voucher.getCodPlan(), caja)) {
							    suma.setHaber(funciones.sumarRedondear(suma.getHaber(), voucher.getAbreNomMoneda().trim().compareTo("S")==0? voucher.getHaber():voucher.getEquivalente() , 6));  

						}
					 }
				 });
			  });
		  }
		  
		return suma.getHaber();
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public PersonaDto getDatosPersonales(String codigoRuc){
		PersonaDto dto = new PersonaDto();
		dto = dataServiceClient.getPersonaDto(codigoRuc);
		  
		return dto;
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public Comprobante getComprobante(String anio,Integer numcomprobante){
      Comprobante c = new Comprobante();
      c = comprobanteDao.buscarComprobanteAnio(numcomprobante,anio);
		return c;
	
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Comprobante> listarComprobante(String anio){
		List<Comprobante> lista = new ArrayList<Comprobante>();
		List<Comprobante> nuevaLista = new ArrayList<Comprobante>();
		lista = comprobanteDao.listarComprobante(anio);
		if (lista.size()>0) {
			lista.stream().forEach(entidad->{
				entidad.setLstComprobanteDet(null);
				nuevaLista.add(entidad);
			});
		}
      return nuevaLista;
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Comprobante> ultimaFecha(Integer numcomprobante, String anio){
      return comprobanteDao.ultimaFecha(numcomprobante,anio);
		
	}
	
}