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
import com.sprinboot.servicios.app.oriplan.sis.service.PlanillaServiceInterface;
import com.sprinboot.servicios.app.oriplan.sis.service.SegmentosServiceInterface;
import com.sprinboot.servicios.app.oriplan.sis.service.TablaSalarialInterface;
import com.sprinboot.servicios.app.oriplan.sis.service.TasaPensionesServiceInterface;
import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;
import com.sprinboot.servicios.oriplan.commons.entity.Segmentos;
import com.sprinboot.servicios.oriplan.commons.entity.TablaSalarial;
import com.sprinboot.servicios.oriplan.commons.entity.TasasPensiones;

@RestController
@RequestMapping("tabla-salarial")
public class TablaSalarialController {
	
	
	@Autowired
	TablaSalarialInterface service;
	
	@GetMapping(value="/all")
    public ResponseEntity<?> tablasalarial() {
		
	     Map<String, Object> response = new HashMap<>();
		 List<TablaSalarial> lista =  new ArrayList<>();
      try {
    	  lista =  service.listar();
	
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", lista);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
