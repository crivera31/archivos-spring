package com.sprinboot.servicios.app.empresa.villasol.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.empresa.villasol.dao.AsientoDao;
import com.sprinboot.servicios.app.empresa.villasol.dao.VoucherDao;
import com.sprinboot.servicios.app.empresa.villasol.dto.ConsistenciaDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.ValidarLibroSPDao;
import com.sprinboot.servicios.app.empresa.villasol.funciones.Funciones;
import com.sprinboot.servicios.app.empresa.villasol.solid.DescuadradoDolares;
import com.sprinboot.servicios.app.empresa.villasol.solid.DescuadradoDolaresSoles;
import com.sprinboot.servicios.app.empresa.villasol.solid.DescuadradoSoles;
import com.sprinboot.servicios.app.empresa.villasol.solid.FilaConsistencia;
import com.sprinboot.servicios.empresa.commons.entity.Asiento;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;
import com.sprinboot.servicios.empresa.commons.services.ObjectService;

@Service
public class AsientoService extends ObjectService<Asiento, AsientoDao > implements AsientoServiceInterface{
	
	@Autowired
	AsientoDao asientoDao;
	
	@Autowired
	VoucherDao voucherDao;
	
    @Autowired
    @Qualifier(value = "DescuadradoDolaresSoles")
    FilaConsistencia filaConsistencia;
	
	@Override
	@Transactional
	public Asiento save(Asiento request) {
		 Asiento asiento = new Asiento();
		 asiento.setCreateAt(new Date());
		 asiento.setEstadoAsiento(1);
		 asiento.setCodOrigen(request.getCodOrigen());
		 asiento.setIdPeriodo(request.getIdPeriodo());
		 asiento.setFechaAsiento(request.getFechaAsiento());
		 asiento.setNumAsiento(request.getNumAsiento());
		 asiento.setConsistenciaDolares(request.getConsistenciaDolares());
		 asiento.setConsistenciaSoles(request.getConsistenciaSoles());
		 asiento.setTotalDebeDolares(request.getTotalDebeDolares());
		 asiento.setTotalDebeSoles(request.getTotalDebeSoles());
		 asiento.setTotalHaberDolares(request.getTotalHaberDolares());
		 asiento.setTotalHaberSoles(request.getTotalHaberSoles());
		 asiento.setAnio(request.getAnio());
		 asiento.setCodMes(request.getCodMes());
		 asiento.setUsername(request.getUsername());
		 
		return asientoDao.save(asiento);
	}
	
	@Override
	@Transactional
	public Asiento asientoFullVoucher(Asiento request) {
		
		for (Voucher voucherRequest : request.getLstVoucher()) {
			  voucherRequest.setAsiento(request);
			//if (voucherRequest.getVoucherRef().getEstadoRef()!=0 && voucherRequest.getVoucherRef().getEstadoRef()!=null) {
			if (voucherRequest.getVoucherRef()!=null) {
				voucherRequest.getVoucherRef().setVoucher(voucherRequest);
				voucherRequest.setVoucherRef(voucherRequest.getVoucherRef());
			}else {
				voucherRequest.setVoucherRef(null);
			}
			
			//if (voucherRequest.getRegistroDocumento().getEstadoRegDoc()!=0 && voucherRequest.getRegistroDocumento().getEstadoRegDoc()!=null) {
			if (voucherRequest.getRegistroDocumento()!=null) {
               	voucherRequest.getRegistroDocumento().setVoucher(voucherRequest);
				voucherRequest.setRegistroDocumento(voucherRequest.getRegistroDocumento());
			}else {
				voucherRequest.setRegistroDocumento(null);
			}

			//}
			//if (voucherRequest.getRegistroLibro().getEstadoRegLib()!=0 && voucherRequest.getRegistroLibro().getEstadoRegLib()!=null) {
		    if (voucherRequest.getRegistroLibro()!=null) { 
			    voucherRequest.getRegistroLibro().setVoucher(voucherRequest);
				voucherRequest.setRegistroLibro(voucherRequest.getRegistroLibro());
		    }else {
				voucherRequest.setRegistroLibro(null);
			}

			//}
		}
		request.setLstVoucher(request.getLstVoucher());
		Asiento response = asientoDao.save(request);
		List<Voucher> lstVoucher  = new ArrayList<>();
		lstVoucher = voucherDao.listarVouchersByAsiento(response.getId());
		response.setLstVoucher(lstVoucher);
		return response;
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public Asiento findUltimoAsiento(String origen , Integer periodo) {
		List<Asiento> lstAsientoEntity  = new ArrayList<>();
		lstAsientoEntity =  asientoDao.findUltimoAsiento(origen, periodo);
		Asiento asientoEntity = new Asiento();
		if(lstAsientoEntity.size()>0) {
			asientoEntity = lstAsientoEntity.get(lstAsientoEntity.size()-1);
		}else{
			asientoEntity = null;
		};
		return asientoEntity;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Asiento findByParametros(String origen, Integer asiento, Integer periodo) {
		Asiento asiento2 = null;
		 asiento2  = asientoDao.findByParametros(origen, asiento, periodo);
		 List<Voucher> lstVoucher  = new ArrayList<>();
		 if(asiento2!=null) {
			 lstVoucher = voucherDao.listarVouchersByAsiento(asiento2.getId());
			 
			 asiento2.setLstVoucher(lstVoucher);
		 }
		
		return asiento2;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Asiento findByAsiento(Integer idasiento) {
		 Asiento asiento = null;
		 asiento  = asientoDao.findOneEnabled(idasiento)	;
		return asiento;
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<ConsistenciaDto>  listarConsistenciasPorMes(Integer mes,String anio) {
		 List<Asiento> listAsiento =  asientoDao.listaDeConsistenciasPorMes(mes, anio);
		return setListConsistencia(listAsiento,getInicializandoListFilaConsistencia());
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<ConsistenciaDto>  listarConsistencias(Integer mes,String anio) {
		 List<Asiento> listAsiento =  asientoDao.listaDeConsistencias(mes, anio);
		 
		return setListConsistencia(listAsiento,getInicializandoListFilaConsistencia());
	}
	
	
	
	
	public  List<ConsistenciaDto> setListConsistencia(List<Asiento> listAsiento,List<FilaConsistencia> listFilaConsistencia){
		List<ConsistenciaDto> listConsistencia = new ArrayList<ConsistenciaDto>();
		 if (listAsiento.size()>0) {
			   for (Asiento asiento : listAsiento) {
				   listConsistencia=  evaluarProcesos(asiento, listConsistencia, listFilaConsistencia);
			   }
			return listConsistencia;
		 }else {
	    	return listConsistencia ;
		}
	}
	
	
	public List<ConsistenciaDto>  evaluarProcesos(Asiento asiento,List<ConsistenciaDto> listConsistencia,List<FilaConsistencia> listFilaConsistencia) {
		  for(FilaConsistencia fila : listFilaConsistencia) {
			    listConsistencia =  fila.proceso(listConsistencia, asiento);
		   }
		return listConsistencia; 
	}
	
	
	public List<FilaConsistencia> getInicializandoListFilaConsistencia(){
		List<FilaConsistencia> listFilaConsistencia = new ArrayList<FilaConsistencia>();
		listFilaConsistencia.add(new DescuadradoSoles());
		listFilaConsistencia.add(new DescuadradoDolares());
		listFilaConsistencia.add(new DescuadradoDolaresSoles());		
		return listFilaConsistencia;
	}
	
	@Override
	public List<ValidarLibroSPDao> getValidacionSP() {
		 // System.out.println("VALIDANDO EN SERVICE SP");
          
		  List<Object[]> results  = asientoDao.validarSP();
		  List<ValidarLibroSPDao> lstSp =  new ArrayList<>();
		  if (results.size()>0) {
			   results.stream().forEach(object->{
		        	  ValidarLibroSPDao dao = new ValidarLibroSPDao();  
		        	  dao.setNum_asiento(Integer.parseInt(object[0].toString()));
		        	  dao.setCod_mes(Integer.parseInt(object[1].toString()));
		        	  dao.setCod_origen(object[2].toString());
		        	  dao.setAnio(object[3].toString());
		        	  lstSp.add(dao);
		        });
		  }
       
         
          return lstSp;
	}
	

}
























