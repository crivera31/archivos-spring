package com.sprinboot.servicios.app.admin.base.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.servicios.app.admin.base.models.services.IConfigEmpresaService;
import com.sprinboot.servicios.app.admin.base.models.services.ITipoRegimenService;
import com.sprinboot.servicios.app.otros.commons.models.entity.ConfigEmpresa;
import com.sprinboot.servicios.app.otros.commons.models.entity.PeriodoAnio;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoRegimen;
import com.sprinboot.servicios.usuario.common.models.entity.Role;

@RestController
@RequestMapping("/config-empresa")
public class EmpresaController {

	@Autowired
	IConfigEmpresaService configEmpresaService;
	
	@Autowired
	ITipoRegimenService regimenService;
	
	@GetMapping("/listar-empresa/anio/{anio}")
	public List<ConfigEmpresa> getConfgEmpresa(@PathVariable String anio){
		return configEmpresaService.getListEmpresaPorAnioActual(anio);
	}
	

	@GetMapping("/listar-tipo-regimen")
	public List<TipoRegimen> getListTipoRegimen(){
		return (List<TipoRegimen>) regimenService.findAllCommon();
	}
	
	@PostMapping("/save-empresa") 
	public ResponseEntity<?> empresasave(@Valid @RequestBody ConfigEmpresa empresa, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		 ConfigEmpresa empresaEntity = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",empresa);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			empresaEntity = configEmpresaService.saveCommon(empresa);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",empresa);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Periodo Anio fue creado con éxito");
		response.put("configEmpresa",empresaEntity);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
}
