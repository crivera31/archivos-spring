package com.sprinboot.servicios.app.oriplan.sis.controller;

import java.text.ParseException;
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

import com.sprinboot.servicios.app.oriplan.sis.dto.BajasDto;
import com.sprinboot.servicios.app.oriplan.sis.dto.TrabajadorDto;
import com.sprinboot.servicios.app.oriplan.sis.funciones.Funciones;
import com.sprinboot.servicios.app.oriplan.sis.service.TrabajadoresServiceInterface;
import com.sprinboot.servicios.oriplan.commons.entity.Trabajadores;

@RestController
@RequestMapping("trabajador")
public class TrabajadorController {

	@Autowired
	TrabajadoresServiceInterface service;

	@Autowired
	Funciones funciones;

	@GetMapping(value = "/activos")
	public ResponseEntity<?> activos() {

		Map<String, Object> response = new HashMap<>();
		List<Trabajadores> lista = new ArrayList<>();
		try {
			lista = service.listarTrabajadoresActivos();

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("datos", lista);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/bajas")
	public ResponseEntity<?> bajas() {

		Map<String, Object> response = new HashMap<>();
		List<Trabajadores> lista = new ArrayList<>();
		try {
			lista = service.listarTrabajadoresActivos();

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("datos", lista);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/*@PutMapping(value = "/editar")
	public ResponseEntity<?> editar(@RequestBody Trabajadores trabajadores) throws ParseException {
		Map<String, Object> response = new HashMap<>();
		String error = "";
		try {
			service.editarTrabajador(trabajadores);
		}catch (DataAccessException e){
			response.put("mensaje", "Error al actualizar en el servidor, al editar trabajador");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}*/

	@PostMapping(value = "/agregar-altas")
	public ResponseEntity<?> agregar(@RequestBody List<TrabajadorDto> requestDto) throws ParseException {

		Map<String, Object> response = new HashMap<>();
		String error = "";
		List<Trabajadores> request = new ArrayList<Trabajadores>();
		for (TrabajadorDto d : requestDto) {
			Trabajadores x = new Trabajadores();
			x.setNombresCompletos(d.getNombresCompletos());
			x.setActivo(d.getActivo());
			x.setCargoOcupacion(d.getCargoOcupacion());
			x.setCarrera(d.getCarrera());
			x.setCci(d.getCci());
			x.setCelular(d.getCelular());
			x.setCorreo(d.getCorreo());
			x.setDireccion(d.getDireccion());
			x.setDni(d.getDni());
			x.setEntidadFinanciera(d.getEntidadFinanciera());
			x.setEstadoCivil(d.getEstadoCivil());
			x.setSexo(d.getSexo());
			x.setSindicalizado(d.getSindicalizado());
			x.setSistemaPensiones(d.getSistemaPensiones());
			x.setNumCuentaBancaria(d.getNumCuentaBancaria());
			x.setNombreUni(d.getNombreUni());
			x.setUsername(d.getUsername());
			x.setFechaNacimiento(funciones.convertir(d.getFechaNacimiento()));
			x.setFechaIngreso(funciones.convertir(d.getFechaIngreso()));
			x.setEnabled(d.getEnabled());
			x.setActivo(d.getActivo());
			x.setPlanilla(d.getPlanilla());
			x.setCussp(d.getCussp());
			x.setNumhijos(d.getNumhijos());
			x.setTipoComision(d.getTipoComision());
			x.setSueldo(d.getSueldo());
			x.setCondicionTrabajo(d.getCondicionTrabajo());
			request.add(x);

		}

		for (Trabajadores trabajadores : request) {
			if (service.buscarSinRepeticion(trabajadores.getDni().trim(), trabajadores.getFechaIngreso())) {
				error = error + trabajadores.getDni() + " - " + trabajadores.getFechaIngreso() + "    /";
			}
		}

		if (error.length() > 1) {
			response.put("mensaje", "Se encontraron dni con fecha de ingreso en la base de datos :   " + error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE); // 406
		}

		try {

			for (Trabajadores trabajadores : request) {
				service.saveCommon(trabajadores);
			}

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en el servidor, al guardar trabajadores");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			// return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("datos", "Se guardo correctamente las altas");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@PutMapping(value = "/editar")
	public ResponseEntity<?> editarSegmento(@Valid @RequestBody Trabajadores request) {
		Trabajadores entidad = new Trabajadores();
		Map<String, Object> response = new HashMap<>();

		try {
			entidad = service.saveCommon(request);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en el servidor, al editar trabajador");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			// return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.status(HttpStatus.OK).body(entidad);
	}

	@PostMapping(value = "/agregar-bajas")
	public ResponseEntity<?> bajas(@RequestBody List<BajasDto> listBajasDto) throws ParseException {
		BajasDto bajasDto = new BajasDto();
		Map<String, Object> response = new HashMap<>();
		String error = "";
		String noencontrado = "";
		String data = "";

		try {

			for (BajasDto bajasDtos : listBajasDto) {
				System.out.println("AAAAAAAAAAAA");
				data = data + bajasDtos.getDni() + "   -  " + service
						.buscar(bajasDtos.getDni().trim(), funciones.convertir(bajasDtos.getFechaIngreso())).size()
						+ "   /";

				if (service.buscar(bajasDtos.getDni().trim(), funciones.convertir(bajasDtos.getFechaIngreso()))
						.size() > 1) {
					error = error + bajasDtos.getDni() + " - " + funciones.convertir(bajasDtos.getFechaIngreso())
							+ "    /";
				} else {
					if (service.buscar(bajasDtos.getDni().trim(), funciones.convertir(bajasDtos.getFechaIngreso()))
							.size() < 1) {
						noencontrado = noencontrado + bajasDtos.getDni() + " - "
								+ funciones.convertir(bajasDtos.getFechaIngreso()) + "    /";
					}
				}
			}

			if (error.length() > 1) {
				response.put("mensaje",
						"Datos no se guardaron ya que se encontraron algunos dni con fecha de ingreso en la base de datos repetidas :   "
								+ error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE); // 406
			} else {
				if (noencontrado.length() > 1) {
					response.put("mensaje",
							"No se guardo, debido a que no se encontraron los siguientes dni con fecha de ingreso en la db :   "
									+ noencontrado);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE); // 406
				} else {
					for (BajasDto bajasDtoaa : listBajasDto) {
						Trabajadores t = new Trabajadores();
						t = service
								.buscar(bajasDtoaa.getDni().trim(), funciones.convertir(bajasDtoaa.getFechaIngreso()))
								.get(0);
						t.setFecha_egreso(funciones.convertir(bajasDtoaa.getFechaEgreso()));
						t.setActivo(0);
						service.saveCommon(t);
					}
				}

			}

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en el servidor, al guardar las bajas de trabajadores");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			// return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("datos", data);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
