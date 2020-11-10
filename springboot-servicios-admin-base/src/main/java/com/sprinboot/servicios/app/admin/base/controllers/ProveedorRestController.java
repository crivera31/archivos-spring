package com.sprinboot.servicios.app.admin.base.controllers;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sprinboot.servicios.app.otros.commons.models.entity.DrtPersJuridica;
import com.sprinboot.servicios.app.otros.commons.models.entity.PeriodoAnio;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoDocumento;

import zipkin2.internal.gson.stream.JsonReader;

import com.sprinboot.servicios.app.admin.base.dto.PersonaDto;
import com.sprinboot.servicios.app.admin.base.dto.Prueba;
import com.sprinboot.servicios.app.admin.base.dto.SunatRuc;
import com.sprinboot.servicios.app.admin.base.models.services.IContAllPersonServiceImpl;

@RestController
@RequestMapping("/proveedor")
public class ProveedorRestController {

	@Autowired
	private IContAllPersonServiceImpl contAllPerson;

	

	@GetMapping("/lista-clientes/page/{page}")
	public Page<DrtPersJuridica> cliente(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 15);
		return contAllPerson.findByDrtPersJuridicaEnabledPaged(pageable);
	}

	

	@GetMapping("/tipo-documento")
	public List<TipoDocumento> tipoDocumentoList() {
		return contAllPerson.findTipoDocumentoAll();
	}

	@GetMapping("/ver-dato-persona/{codigo}")
	public ResponseEntity<?> show(@PathVariable String codigo) {
		Map<String, Object> response = new HashMap<>();
		DrtPersJuridica drtPersJuridica = null;
		try {
			drtPersJuridica = contAllPerson.findByCodigo(codigo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en el Servidor");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("error_data_1", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (drtPersJuridica == null) {
			response.put("mensaje", "El Código ".concat(codigo.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DrtPersJuridica>(drtPersJuridica, HttpStatus.OK);
	}


	
	@GetMapping("/ver-dato-persona-dto/{codigo}")
	public ResponseEntity<?> showDto(@PathVariable String codigo) {
		Map<String, Object> response = new HashMap<>();
		DrtPersJuridica drtPersJuridica = null;
		PersonaDto dto = new PersonaDto();
		try {
			drtPersJuridica  = contAllPerson.findByCodigo(codigo);
		
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en el Servidor");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("error_data_1", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (drtPersJuridica == null) {
			response.put("mensaje", "El Código ".concat(codigo.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			dto.setRuc(drtPersJuridica.getCodigo());
			dto.setRazonSocial(drtPersJuridica.getRsocial());
			dto.setDireccion(drtPersJuridica.getDireccion());
		}
		return new ResponseEntity<PersonaDto>(dto, HttpStatus.OK);
	}


	
	@PostMapping("/agregar-clientes")
	public ResponseEntity<?> agregarClientes(@Valid @RequestBody DrtPersJuridica drtPersJuridicaVar,
			BindingResult result) {
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		DrtPersJuridica drtPersJuridicaNew = null;
		try {
			drtPersJuridicaNew = contAllPerson.save(drtPersJuridicaVar);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en el Servidor");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Cliente fue creado con éxito");
		response.put("proveedores", drtPersJuridicaVar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/editar-clientes/{id}")
	public ResponseEntity<?> actualizarClientes(@Valid @RequestBody DrtPersJuridica drtPersJuridicaVar,
			BindingResult result, @PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo'" + err.getField() + "' :" + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		DrtPersJuridica drtPersJuridicaActual = contAllPerson.findByDrtPersJuridicaId(id);
		if (drtPersJuridicaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el Cliente con el ID "
					.concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		DrtPersJuridica update = null;
		try {
			update = contAllPerson.save(drtPersJuridicaVar);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en el servidor");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Proveedor fue actualizado con éxito");
		response.put("cliente", update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/consultaruc-apisunat")
	public ResponseEntity<?> sunat(){
		Map<String, Object> response = new HashMap<>();
		Map<String, Object> pathvariables = new HashMap<>();
		//pathvariables.put("ruc", ruc);
		///SunatRuc resultado = new SunatRuc();
		//try {
		       //resultado =  (SunatRuc) parsingService.parse("https://api.sunat.cloud/ruc/10768072626");
				RestTemplate plantilla = new RestTemplate();
				//Prueba resultado = new Prueba();
				//resultado = plantilla.getForObject("https://api.sunat.cloud/ruc/10768072626", SunatRuc.class);
				// resultado = plantilla.getForObject("https://api.sunat.cloud/ruc/{ruc}", SunatRuc.class, pathvariables);
				String resultado = plantilla.getForObject("https://api.sunat.cloud/ruc/10768072626", String.class);
                
				
		//}
		//catch(Exception e) {
		//	response.put("mensaje","Error en el Servidor");
		//	response.put("error",e.getMessage().concat(": ").concat(e.getMessage()));		
		//	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		//}
		return new ResponseEntity<String>(resultado, HttpStatus.OK);
	}

	
	
	
}
