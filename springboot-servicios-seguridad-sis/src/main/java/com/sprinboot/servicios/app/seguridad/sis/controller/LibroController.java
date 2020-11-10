package com.sprinboot.servicios.app.seguridad.sis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.servicios.app.seguridad.sis.service.LibroServiceInterface;
import com.sprinboot.servicios.empresa.commons.entity.Libro;

@RestController
@RequestMapping("librillo")
public class LibroController {
	
	@Autowired
	LibroServiceInterface libroServiceInterface;
	
	@GetMapping(value="/lstlibros")
    public ResponseEntity<?> lstLibros() {
		 List<Libro>lstLibros = new ArrayList<>();
		try {
		  lstLibros =  libroServiceInterface.getLibros();
			
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(lstLibros);
	}
}
