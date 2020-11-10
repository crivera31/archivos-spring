package com.sprinboot.servicios.app.empresa.villasol.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.servicios.app.empresa.villasol.dto.ConsistenciaDto;
import com.sprinboot.servicios.app.empresa.villasol.service.AsientoServiceInterface;
import com.sprinboot.servicios.empresa.commons.entity.Asiento;
import com.sprinboot.servicios.empresa.commons.entity.RegistroDocumentos;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;

@RestController
@RequestMapping("asiento")
public class AsientoController {

	@Autowired
	AsientoServiceInterface asientoService;
	
	
	@PostMapping(value="/agregar")
    public ResponseEntity<?> agregarAsiento(@Valid @RequestBody Asiento request) {
		Asiento asiento= new Asiento();		
		Map<String, Object> response = new HashMap<>();
		try {
			 asiento = asientoService.findByParametros(request.getCodOrigen(), request.getNumAsiento(), request.getIdPeriodo());
			
			 
		} catch (DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor, al recomprobar si asiento ya fue creado");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(asiento==null) {
			try {
				asiento =  asientoService.save(request);
			} catch (DataAccessException e) {
				response.put("mensaje","Error al actualizar en el servidor, al guarda asiento");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				//return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(asiento);
		}else {
			response.put("mensaje","Este asiento ya fue creado segundos antes sin ser detectado anteriormente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	
	@GetMapping(value="/ultimo/{origen}/{periodo}")
	public ResponseEntity<?> ultimoAsiento(@PathVariable("origen") String origen,@PathVariable("periodo") Integer periodo){
		Asiento asiento = null;
		try {
			asiento = asientoService.findUltimoAsiento(origen,periodo);
			
			
			if(asiento==null) {
	             return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);

			}else {
				 List<Voucher> lstVoucher = new ArrayList<>();
				 if(asiento.getLstVoucher()!=null) {
						for(Voucher voucherResponse : asiento.getLstVoucher()) {
							 voucherResponse.setAsiento(null);
							if(voucherResponse.getVoucherRef()!=null) {
					            voucherResponse.getVoucherRef().setVoucher(null);
							}
							if (voucherResponse.getRegistroDocumento()!=null) {
					            voucherResponse.getRegistroDocumento().setVoucher(null);	
							}
							if (voucherResponse.getRegistroLibro()!=null) {
					            voucherResponse.getRegistroLibro().setVoucher(null);
							}
							
							if(voucherResponse.getEstadoVoucher()==1){
					            lstVoucher.add(voucherResponse);
							}
						}
						asiento.setLstVoucher(lstVoucher);
				 }
			
			}
		} catch (Exception e) {
             return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		return ResponseEntity.status(HttpStatus.OK).body(asiento);
	}
	
	@GetMapping(value="/findbyasiento/{origen}/{asiento}/{periodo}")
	public ResponseEntity<?>  findbyasiento(@PathVariable("origen") String origen,@PathVariable("asiento")  Integer asiento,@PathVariable("periodo")  Integer periodo){
		Asiento asientoEntity = null;
							   
		try {
		     asientoEntity = asientoService.findByParametros(origen, asiento, periodo);
		     if(asientoEntity!=null) {
		    	    List<Voucher> lstVoucher = new ArrayList<>();
		    	    if(asientoEntity.getLstVoucher()!=null) {
		    	    	 for (Voucher voucher : asientoEntity.getLstVoucher()) {
					    	 if(voucher.getEstadoVoucher()==1) {
					    		 voucher.setAsiento(null);
								 if(voucher.getRegistroLibro()!=null) {
									voucher.getRegistroLibro().setVoucher(null);
								 }
								 if(voucher.getRegistroDocumento()!=null) {
									 voucher.getRegistroDocumento().setVoucher(null);
								 }
								 if(voucher.getVoucherRef()!=null) {
									 voucher.getVoucherRef().setVoucher(null);
								 }
								 lstVoucher.add(voucher);
					    	 }
						}
					 asientoEntity.setLstVoucher(lstVoucher);   
		    	    }
		     }else 
		    	 return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			//**************response******************** 
			
		}catch(DataAccessException e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(asientoEntity);
	
	}
	
	
	@GetMapping(value="/consistencias/por-mes/{mes}/{anio}")
    public ResponseEntity<?> getConsistenciaPorMEs(@PathVariable Integer mes, @PathVariable String anio) {
		Map<String, Object> response = new HashMap<>();
		List<ConsistenciaDto> listConsistencia = new ArrayList<ConsistenciaDto>();
		try {
			listConsistencia = asientoService.listarConsistenciasPorMes(mes, anio);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (listConsistencia.size()>0) {
			response.put("mensaje","si hay consistencias");
			response.put("lstConsistencias", listConsistencia);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}else {
			response.put("mensaje","No hay consistencias");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	
	}
	
	@GetMapping(value="/consistencias/acumulado/{mes}/{anio}")
    public ResponseEntity<?> getConsistenciaAcumulado(@PathVariable Integer mes, @PathVariable String anio) {
		Map<String, Object> response = new HashMap<>();
		List<ConsistenciaDto> listConsistencia = new ArrayList<ConsistenciaDto>();
		try {
			listConsistencia = asientoService.listarConsistencias(mes, anio);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (listConsistencia.size()>0) {
			response.put("mensaje","si hay consistencias");
			response.put("lstConsistencias", listConsistencia);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}else {
			response.put("mensaje","No hay consistencias");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	
	}
}
