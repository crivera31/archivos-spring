package com.sprinboot.servicios.app.oriplan.sis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.servicios.app.oriplan.sis.service.DatosGeneralesServiceInterface;
import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;

@RestController
@RequestMapping("datos")
public class DatosGeneralesController {
	
	@Autowired
	DatosGeneralesServiceInterface datosGeneralesServiceInterface;
	
	@GetMapping(value="/activo")
    public ResponseEntity<?> datosGenerales() {
		
	     Map<String, Object> response = new HashMap<>();
		 List<DatosGenerales>listDatos = new ArrayList<>();
		try {

		  listDatos =  datosGeneralesServiceInterface.getEnabled();
		  
		  /*if (!(listDatos.size()>0)) {
			  response.put("mensaje" , "No se encontraron datos generales activos");
			  return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		  }*/
			
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("datos", listDatos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
}
