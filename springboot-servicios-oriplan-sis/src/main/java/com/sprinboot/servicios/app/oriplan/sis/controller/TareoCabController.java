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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.servicios.app.oriplan.sis.service.TareoCabServiceInterface;
import com.sprinboot.servicios.app.oriplan.sis.service.TrabajadoresServiceInterface;
import com.sprinboot.servicios.oriplan.commons.entity.TareoCab;
import com.sprinboot.servicios.oriplan.commons.entity.TareoDet;

@RestController
@RequestMapping("tareo")
public class TareoCabController {

	@Autowired
	TareoCabServiceInterface service;

	@Autowired
	TrabajadoresServiceInterface serviceTrabajador;

	@PutMapping(value = "/editar")
	public ResponseEntity<?> editarTareo(@Valid @RequestBody TareoCab tareoCab) {
		TareoCab entidad = new TareoCab();
		Map<String, Object> response = new HashMap<>();
		try {
			entidad = service.actualizar(tareoCab);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en el servidor, al editar Tareo Cab");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.status(HttpStatus.OK).body(entidad);
	}

	@GetMapping(value = "/listar-tareo/{idDatosGenerales}/{nomSemana}")
	public ResponseEntity<?> listarTareoPorPeriodo(@PathVariable Integer idDatosGenerales,
			@PathVariable String nomSemana) {
		List<TareoCab> lstTareoCab = new ArrayList<>();

		try {
			lstTareoCab = service.getTareoSemObra(nomSemana, idDatosGenerales);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.status(HttpStatus.OK).body(lstTareoCab);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<?> segmentosAll() {

		Map<String, Object> response = new HashMap<>();
		List<TareoCab> lista = new ArrayList<>();
		try {
			lista = service.listar();

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("data", lista);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/agregar")
	public ResponseEntity<?> agregar(@RequestBody TareoCab request) {
		TareoCab entidad = new TareoCab();
		Map<String, Object> response = new HashMap<>();

//	        if (service.verificarRepetcion(request.getNomPeriodoSemanal())) {
//	        	response.put("mensaje","Error en el servidor,ya existe el nombre del periodo semanal ya tiene un tareo asignado en la base de datos");
//				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE); //406
//			}

		String trabajador_sinalta = "";
		List<TareoDet> list = new ArrayList<>();
		for (TareoDet tareoDet : request.getLstTareoDet()) {
			if (!serviceTrabajador.buscarTrabajadorActivo(tareoDet.getDni().trim())) {
				trabajador_sinalta = trabajador_sinalta + " " + tareoDet.getDni() + " : ["
						+ tareoDet.getNombresCompletos() + "]";
			}
		}

		if (trabajador_sinalta.length() > 0) {
			response.put("mensaje",
					"Los siguientes trabajadores del tareo no estan registrados como activos: " + trabajador_sinalta);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
		}

		try {

			entidad = service.guardar(request);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en el servidor, al guardar tarea cab");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			// return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("datos", "Se guardo correctamente el tareo");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
