package com.sprinboot.servicios.app.oriplan.sis.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.servicios.app.oriplan.sis.service.DatosGeneralesServiceInterface;
import com.sprinboot.servicios.app.oriplan.sis.service.PeriodoServiceInterface;
import com.sprinboot.servicios.app.oriplan.sis.service.PlanillaServiceInterface;
import com.sprinboot.servicios.app.oriplan.sis.service.SegmentosServiceInterface;
import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.Periodo;
import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;
import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;

@RestController
@RequestMapping("periodo")
public class PeriodoController {
	
	
	@Autowired
	PeriodoServiceInterface service;
	
	@GetMapping(value="/all")
    public ResponseEntity<?> segmentosAll() {
		
	     Map<String, Object> response = new HashMap<>();
		 List<Periodo> lista =  new ArrayList<>();
      try {
    	  lista =  service.listar();
	
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", lista);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value="/agregar")
    public ResponseEntity<?> agregar(@Valid @RequestBody Periodo request) {
		Periodo entidad = new Periodo();		
		Map<String, Object> response = new HashMap<>();
		 
	        if (service.verificarRepetcion(request.getDescripcion())) {
	        	response.put("mensaje","Error en el servidor,ya existe el nombre ingresado");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_MODIFIED); //304
			}
		  
			try {
				entidad =  service.saveCommon(request);
			} catch (DataAccessException e) {
				response.put("mensaje","Error al actualizar en el servidor, al guardar periodo");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				//return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(entidad);
		
	}
	
	@PutMapping(value="/editar")
    public ResponseEntity<?> editarSegmento(@Valid @RequestBody Periodo request) {
		Periodo entidad = new Periodo();		
		Map<String, Object> response = new HashMap<>();
		 
		  
			try {
				entidad =  service.saveCommon(request);
			} catch (DataAccessException e) {
				response.put("mensaje","Error al actualizar en el servidor, al editar segmento");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				//return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return ResponseEntity.status(HttpStatus.OK).body(entidad);
	}
	
	
	
}
