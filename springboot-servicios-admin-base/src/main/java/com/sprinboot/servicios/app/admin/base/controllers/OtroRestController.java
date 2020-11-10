package com.sprinboot.servicios.app.admin.base.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.servicios.app.otros.commons.models.entity.Anio;
import com.sprinboot.servicios.app.otros.commons.models.entity.CentroCosto;
import com.sprinboot.servicios.app.otros.commons.models.entity.ConfigEmpresa;
import com.sprinboot.servicios.app.otros.commons.models.entity.Documento;
import com.sprinboot.servicios.app.otros.commons.models.entity.Empresa;
import com.sprinboot.servicios.app.otros.commons.models.entity.MedioDePago;
import com.sprinboot.servicios.app.otros.commons.models.entity.MesToJob;
import com.sprinboot.servicios.app.otros.commons.models.entity.Origen;
import com.sprinboot.servicios.app.otros.commons.models.entity.Parametros;
import com.sprinboot.servicios.app.otros.commons.models.entity.ParametrosAsis;
import com.sprinboot.servicios.app.otros.commons.models.entity.Periodo;
import com.sprinboot.servicios.app.otros.commons.models.entity.PeriodoAnio;
import com.sprinboot.servicios.app.otros.commons.models.entity.PlanCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoCambio;
import com.sprinboot.servicios.app.otros.commons.models.entity.UnidadNegocio;
import com.sprinboot.servicios.usuario.common.models.entity.Role;

import io.micrometer.core.instrument.MeterRegistry.Config;

import com.sprinboot.servicios.app.admin.base.models.services.IConfigEmpresaService;
import com.sprinboot.servicios.app.admin.base.models.services.IOtrosServiceImpl;

@RestController
@RequestMapping("/otro")
public class OtroRestController {

	@Autowired
	private IOtrosServiceImpl otrosService;
	
	@Autowired
	private IConfigEmpresaService configEmpresaService;

	@PutMapping("/editar-mes-to-job/{id}")
	public ResponseEntity<?> updateMesToJob(@Valid @RequestBody MesToJob mesToJob, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		MesToJob mesToJob_obj = otrosService.findByMesToJobId(id);
		if(mesToJob_obj == null) {
			response.put("mensaje","Error: no se pudo editar, el Mes de Trabajo con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		MesToJob update = null;
		try {
			update = otrosService.saveMesToJob(mesToJob);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Mes de Trabajo fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/mes-to-job")
	public List<MesToJob> mesToJob(){
		return otrosService.findAllMesToJob();
	}	
	
	
	

	@GetMapping("/roles")
	public List<Empresa> roles(){
		return otrosService.findAllRoles();
	}	
	
	
	@GetMapping("/periodo-anio/list/{empresa}")
	public List<PeriodoAnio> periodoAnio(@PathVariable(name="empresa") String empresa){
		return otrosService.findAllPeriodoAnio(empresa);
	}	
	
	@GetMapping("/anio")
	public List<Anio> anio(){
		return otrosService.findAllAnio();
	}	

	@GetMapping("/periodo-anio/{id}")
	public ResponseEntity<?> periodoAnio(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		PeriodoAnio periodoAnio = null;
		try {
			periodoAnio = otrosService.findByPeriodoAnioId(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(periodoAnio == null) {
			response.put("mensaje","El Id ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PeriodoAnio>(periodoAnio, HttpStatus.OK);
	}

	@PostMapping("/agregar-eriodo-anio") 
	public ResponseEntity<?> createPeriodoAnio(@Valid @RequestBody PeriodoAnio periodoAnio, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		PeriodoAnio periodoAnio_obj = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",periodoAnio);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			periodoAnio_obj = otrosService.savePeriodoAnio(periodoAnio);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",periodoAnio);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Periodo Anio fue creado con éxito");
		response.put("documento",periodoAnio);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/editar-periodo-anio/{id}")
	public ResponseEntity<?> updatePeriodoAnio(@Valid @RequestBody PeriodoAnio periodoAnio, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		PeriodoAnio periodo_obj = otrosService.findByPeriodoAnioId(id);
		if(periodo_obj == null) {
			response.put("mensaje","Error: no se pudo editar, el Periodo Anio con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		PeriodoAnio update = null;
		try {
			update = otrosService.savePeriodoAnio(periodoAnio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Periodo Anio fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

	@GetMapping("/listperiodo/{empresa}")
	public List<Periodo> periodo(@PathVariable(name="empresa") String empresa){
		return otrosService.findAllPeriodo(empresa);
	}	

	@PostMapping("/agregar-periodo") 
	public ResponseEntity<?> createPeriodo(@Valid @RequestBody Periodo periodo, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		Periodo periodo_obj = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",periodo);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			periodo_obj = otrosService.savePeriodo(periodo);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",periodo);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Periodo fue creado con éxito");
		response.put("documento",periodo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/editar-periodo/{id}")
	public ResponseEntity<?> updatePeriodo(@Valid @RequestBody Periodo periodo, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Periodo periodo_obj = otrosService.findByPeriodoId(id);
		if(periodo_obj == null) {
			response.put("mensaje","Error: no se pudo editar, el Periodo con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Periodo update = null;
		try {
			update = otrosService.savePeriodo(periodo);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Periodo fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@GetMapping("/periodo/{id}")
	public ResponseEntity<?> showPeriodo(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		Periodo periodo = null;
		try {
			periodo = otrosService.findByPeriodoId(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(periodo == null) {
			response.put("mensaje","El Periodo con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Periodo>(periodo, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/origen")
	public List<Origen> origen(){
		return otrosService.findAll();
	}
	@GetMapping("/origen/{id}")
	public ResponseEntity<?> showOrigen(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		Origen origen = null;
		try {
			origen = otrosService.findOrigenById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(origen == null) {
			response.put("mensaje","El Origen con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Origen>(origen, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	@GetMapping("/centro-costo")
	public List<CentroCosto> centroCosto(){
		return otrosService.findAllCentroCosto();
	}
	
	@GetMapping("/parametros-asis/{anio}")
	public List<ParametrosAsis> paramatrosAsis(@PathVariable Integer anio){
		return otrosService.findAllParametrosAsis(anio);
	}

	@PostMapping("/agregar-parametros-asis") 
	public ResponseEntity<?> createParametrosAsis(@Valid @RequestBody ParametrosAsis parametrosAsis, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		ParametrosAsis parametros_asis = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",parametrosAsis);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			parametros_asis = otrosService.saveParametrosAsis(parametrosAsis);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",parametrosAsis);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("parametrosAsis",parametrosAsis);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/editar-parametros-asis/{id}")
	public ResponseEntity<?> updateParametrosAsistencia(@Valid @RequestBody ParametrosAsis parametrosAsis, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		ParametrosAsis parametrosAsis_ob = otrosService.findByParametrosAsisId(id);
		if(parametrosAsis_ob == null) {
			response.put("mensaje","Error: no se pudo editar, el Parametro Asistente con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		ParametrosAsis update = null;
		try {
			update = otrosService.saveParametrosAsis(parametrosAsis);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//response.put("mensaje","El Parametro Asistente fue actualizado con éxito");
		response.put("parametrosAsis",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//@Value("${myIdPeriodo}")
	//private Integer myIdPeriodo;
	
	
	@GetMapping("/parametro/{periodoAnio}")
	public ResponseEntity<?> show(@PathVariable Integer periodoAnio){
		Map<String, Object> response = new HashMap<>();
		Parametros parametros = null;
		try {
			parametros = otrosService.findByParametroByAnioPeriodo(periodoAnio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(parametros == null) {
			response.put("mensaje","El parametro con el ID ".concat(periodoAnio.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Parametros>(parametros, HttpStatus.OK);
	}
	
	@PostMapping("/agregar-parametros") 
	public ResponseEntity<?> createParametros(@Valid @RequestBody Parametros parametros, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		Parametros parametros_obj = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",parametros);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			parametros_obj = otrosService.saveParametros(parametros);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",parametros);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Parametro fue creado con éxito");
		response.put("parametros",parametros_obj);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}	
	
	@PutMapping("/editar-parametros/{id}")
	public ResponseEntity<?> updateParametros(@Valid @RequestBody Parametros parametros, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Parametros parametros_ob = otrosService.findByParametrosId(id);
		if(parametros_ob == null) {
			response.put("mensaje","Error: no se pudo editar, el Parametro con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Parametros update = null;
		try {
			update = otrosService.saveParametros(parametros);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Parametro fue actualizado con éxito");
		response.put("parametros",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/lista-documento/page/{page}")
	public Page<Documento> documento(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 15);
		return otrosService.findDocumentoAll(pageable);
	}

	@GetMapping("/lista-unidad-negocio/page/{page}")
	public Page<UnidadNegocio> unidadNegocio(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 15);
		return otrosService.findAllUnidadNegocio(pageable);
	}
	
	@PostMapping("/agregar-unidad-negocio") 
	public ResponseEntity<?> createUnidadNegocio(@Valid @RequestBody UnidadNegocio unidadNegocio, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		UnidadNegocio unidad_negocio_obj = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",unidadNegocio);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			unidad_negocio_obj = otrosService.saveUnidadNegocio(unidadNegocio);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",unidadNegocio);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","La Unidad de Negocio fue creado con éxito");
		response.put("documento",unidadNegocio);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}	

	@PutMapping("/editar-unidad-negocio/{id}")
	public ResponseEntity<?> updateUnidadNegocio(@Valid @RequestBody UnidadNegocio unidadNegocio, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		UnidadNegocio unidad_negocio_ob = otrosService.findByUnidadNegocioId(id);
		if(unidad_negocio_ob == null) {
			response.put("mensaje","Error: no se pudo editar, el Parametro con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		UnidadNegocio update = null;
		try {
			update = otrosService.saveUnidadNegocio(unidadNegocio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","La unidad de negocio fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/lista-medio-de-pago/page/{page}")
	public Page<MedioDePago> medioDePago(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 15);
		return otrosService.findAll(pageable);
	}
	
	@GetMapping("/lista-tipo-de-cambio/page/{page}")
	public Page<TipoCambio> tipoCambio(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 1000);
		return otrosService.findTipoCambioAll(pageable);
	}

	

	@PostMapping("/agregar-documento") 
	public ResponseEntity<?> createDocumento(@Valid @RequestBody Documento documentoVar, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		Documento documentoNew = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",documentoVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			documentoNew = otrosService.save(documentoVar);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",documentoVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Documento fue creado con éxito");
		response.put("documento",documentoVar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/editar-documento/{id}")
	public ResponseEntity<?> updateDocumento(@Valid @RequestBody Documento documentoVar, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Documento documentoActual = otrosService.findDocumentooById(id);
		if(documentoActual == null) {
			response.put("mensaje","Error: no se pudo editar, el Plan de Cuenta con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Documento update = null;
		try {
			update = otrosService.save(documentoVar);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Documento fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/agregar-medio-de-pago") 
	public ResponseEntity<?> createMedioDePago(@Valid @RequestBody MedioDePago medioDePagoVar, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		MedioDePago medioDepagoNew = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",medioDePagoVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			medioDepagoNew = otrosService.save(medioDePagoVar);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",medioDePagoVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Medio de Pago fue creado con éxito");
		response.put("medio_de_pago",medioDePagoVar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/editar-medio-de-pago/{id}")
	public ResponseEntity<?> updateMedioDePago(@Valid @RequestBody MedioDePago medioDePagoVar, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		MedioDePago medioDePagoActual = otrosService.findMedioDePagoById(id);
		if(medioDePagoActual == null) {
			response.put("mensaje","Error: no se pudo editar, el Plan de Cuenta con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		MedioDePago update = null;
		try {
			update = otrosService.save(medioDePagoVar);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Medio de Pago fue actualizado con éxito");
		response.put("medio_de_pago",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/agregar-tipo-de-cambio") 
	public ResponseEntity<?> createTipoDeCambio(@Valid @RequestBody TipoCambio tipoCambioVar, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		TipoCambio tipoCambioNew = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",tipoCambioVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			 tipoCambioNew  = otrosService.buscarTipoDeCambioPorFecha(tipoCambioVar.getFecha());
			 
		} catch (DataAccessException d) {
			// TODO: handle exception
			response.put("mensaje","Error en el Servidor 500, Buscando Fecha si ya esta asignada");
			response.put("error",d.getMessage().concat(": ").concat(d.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",tipoCambioVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(tipoCambioNew!=null) {
			response.put("mensaje","Error en el Servidor: Fecha ya tiene asignado un tipo de cambio ");
			response.put("respuesta Servidor",tipoCambioVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FOUND);
			
		}else {
			try {
				tipoCambioNew = otrosService.save(tipoCambioVar);
			}catch(DataAccessException e) {
				response.put("mensaje","Error en el Servidor al Guardar");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				response.put("respuesta Servidor",tipoCambioVar);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensaje","El Tipo de Cambio fue creado con éxito");
			response.put("tipo_de_cambio",tipoCambioVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
		
		
	}

	@PutMapping("/editar-tipo-de-cambio/{id}")
	public ResponseEntity<?> updateTipoDeCambio(@Valid @RequestBody TipoCambio tipoCambioVar, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		TipoCambio tipoCambioActual = otrosService.findTipoCambioById(id);
		if(tipoCambioActual == null) {
			response.put("mensaje","Error: no se pudo editar, el Plan de Cuenta con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		TipoCambio update = null;
		try {
			update = otrosService.save(tipoCambioVar);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Tipo de Cambio fue actualizado con éxito");
		response.put("tipo_de_cambio",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/find-tipo-cambio/{fecha}")
	@ResponseStatus(HttpStatus.OK)
	public TipoCambio buscarTipoDeCambioPorFecha(@PathVariable String fecha) throws Exception {
		System.out.println("La fecha ESS = "+fecha);
		Date fechaDate = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		System.out.println("LA FECHA DATE ES "+fechaDate);
		return otrosService.buscarTipoDeCambioPorFecha(fechaDate);
	}
	
	
	@GetMapping("/lista-full-documentos")
	public List<Documento> lstdocumento(){
		return otrosService.findFullDocumentoAll();
	}
	
	@GetMapping("/lista-empresas")
	public List<Empresa> listaempresas(){
		return otrosService.listarEmpresa();
	}  
	
	@GetMapping("/unidad-negocio/{cod}/{idperiodoanio}")
	public ResponseEntity<?> showUnidadNegocio(@PathVariable String cod,@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		UnidadNegocio unidadNegocioEntity = null;
		//Moneda moneda = null; 
		try {
			unidadNegocioEntity = otrosService.buscarPorCodigo(cod,idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(unidadNegocioEntity == null) {
			response.put("mensaje","El planCuenta con el ID ".concat(cod.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UnidadNegocio>(unidadNegocioEntity, HttpStatus.OK);
	}
	

	@GetMapping("/list-unidad-negocio/{cod}/{idperiodoAnio}") 
	public List<UnidadNegocio> listUnidadNegocio(@PathVariable String cod,@PathVariable Integer idperiodoAnio){
		return otrosService.filtrarPorCodigo(cod,idperiodoAnio);
	}	
	
	@GetMapping("/parametro-asis/{periodoAnio}/{origen}")
	public ResponseEntity<?> showParametroAs(@PathVariable Integer periodoAnio,@PathVariable Integer origen){
		Map<String, Object> response = new HashMap<>();
		ParametrosAsis parametrosAsisEntity = null;
		try {
			parametrosAsisEntity = otrosService.getParametrosbyPeriodoAndOrigen(periodoAnio, origen);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(parametrosAsisEntity == null) {
			response.put("mensaje","El origen con el ID ".concat(origen.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ParametrosAsis>(parametrosAsisEntity, HttpStatus.OK);
	}
	
	@GetMapping("/anio/buscar/{anioname}")
	public ResponseEntity<?> showAnio(@PathVariable String anioname){
		Map<String, Object> response = new HashMap<>();
		Anio anioEntity = null;
		try {
			anioEntity = otrosService.findAnio(anioname);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(anioEntity == null) {
			response.put("mensaje","El origen con el ID ".concat(anioname.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Anio>(anioEntity, HttpStatus.OK);
	}
	
	

	
	@GetMapping("/filtrar-codigo/{term}/{idperiodoanio}")
	@ResponseStatus(HttpStatus.OK)
	public List<PlanCuenta> filtrarProductos(@PathVariable String term,@PathVariable Integer idperiodoanio){
		return otrosService.findPlanCuentaByCodCuenta(term,idperiodoanio);
	}
	
	/*@GetMapping("/filtrar-codigo-cod/{plan}")
	@ResponseStatus(HttpStatus.OK)
	public PlanCuenta filtrarProductosCodigo(@PathVariable String plan){
		return otrosService.findByPlanCuentaCodCuenta(plan);
	}*/
	
	@GetMapping("/filtrar-codigo-cod/{plan}/{idperiodoanio}")
	public ResponseEntity<?> buscarCodigoPlanCuenta(@PathVariable String plan,@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		List<PlanCuenta> listPlanCuenta =  new ArrayList<>();
		try {
			listPlanCuenta = otrosService.buscarPlanCuentaPorCodigo(plan,idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(listPlanCuenta.size()<1) {
			response.put("mensaje","El plan con el id ".concat(plan.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			if(listPlanCuenta.size()>1) {
				response.put("mensaje","El id del plan  ".concat(plan.toString().concat("esta duplicado")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<PlanCuenta>(listPlanCuenta.get(0), HttpStatus.OK);

			}
		}
	}
	
	@GetMapping("/filtrar-plan-id/{idplan}/{idperiodoanio}")
	public ResponseEntity<?> buscarPorIdPlanCuenta(@PathVariable Integer idplan,@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		List<PlanCuenta> listPlanCuenta =  new ArrayList<>();
		try {
			listPlanCuenta = otrosService.buscarPlanCuentaPorId(idplan,idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(listPlanCuenta.size()<1) {
			response.put("mensaje","El plan con el id ".concat(idplan.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			if(listPlanCuenta.size()>1) {
				response.put("mensaje","El id del plan  ".concat(idplan.toString().concat(" esta duplicado")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<PlanCuenta>(listPlanCuenta.get(0), HttpStatus.OK);

			}
		}
	}
	
	@GetMapping("/filtrar-plan-codigo/{plan}/{idperiodoanio}")
	public ResponseEntity<?> buscarPlanPorCodigo(@PathVariable String plan,@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		List<PlanCuenta> listPlanCuenta =  new ArrayList<>();
		try {
			listPlanCuenta = otrosService.buscarPlanCuentaPorCodigo(plan,idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(listPlanCuenta.size()<1) {
			response.put("mensaje","El plan con el id ".concat(plan.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			if(listPlanCuenta.size()>1) {
				response.put("mensaje","El id del plan  ".concat(plan.toString().concat("esta duplicado")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<PlanCuenta>(listPlanCuenta.get(0), HttpStatus.OK);

			}
		}
	}
	
	
	@GetMapping("/buscar-igv-parametros/{planigv}")
	public ResponseEntity<?> buscarCuentaIgvEnParametros(@PathVariable String planigv){
		Map<String, Object> response = new HashMap<>();
		Parametros parametro =  null;
		try {
			parametro = otrosService.buscarCuentaIgvEnParametros(planigv);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(parametro==null) {
			response.put("mensaje","El codigo no estan en el parametro de cuentas ");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {  
			    response.put("mensaje", "el codigo se encontro en el parametro de cuenta como igv");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
	}
	
	
	
	@GetMapping("/empresas-anio")
	public List<PeriodoAnio> listarPeriodoConAnios(){
		return otrosService.listarPeriodoConAnio();
	}	
	
	
	@GetMapping("/find-centro-costo-byid/{idunidadnegocio}")
	public ResponseEntity<?> findCentroCostoById(@PathVariable Integer idunidadnegocio){
		Map<String, Object> response = new HashMap<>();
		UnidadNegocio unidadNegocioEntity = null; 
		//Moneda moneda = null; 
		try {
			unidadNegocioEntity = otrosService.findCentroCostoById(idunidadnegocio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(unidadNegocioEntity == null) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		}
		return new ResponseEntity<String>(unidadNegocioEntity.getNombUnidad(), HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/empresa-limite-uit/{nombremodulo}")
	public ResponseEntity<?> findBuscarSiLaEmpresaPasoLos1500Uit(@PathVariable String nombremodulo){
		Map<String, Object> response = new HashMap<>();
		  
		Optional<ConfigEmpresa> configEmpresa = null;
		try {
			configEmpresa = configEmpresaService.findByNombreEmpresa(nombremodulo);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(configEmpresa.isPresent()) {
			
			if (configEmpresa.get().getObligatorioClasificacion()!=null) {
				if (configEmpresa.get().getRuc()!=null) {
					response.put("mensaje","Consulta exitosa , si es 1: paso los 1500 uit, 0:no paso ");
					response.put("obligatorio",configEmpresa.get().getObligatorioClasificacion());
					response.put("ruc", configEmpresa.get().getRuc());
					return new ResponseEntity<Map<String, Object>>( response, HttpStatus.OK);
				}else {
					response.put("mensaje","Error en el servidor");
					response.put("error","El modulo "+nombremodulo + " no tiene asignado un numero de ruc en la Base de datos");
					return new ResponseEntity<Map<String, Object>>( response, HttpStatus.NOT_ACCEPTABLE);
				}
				
			}else {
				response.put("mensaje","Error en el servidor");
				response.put("error","El modulo "+nombremodulo + " no tiene asignado 0 o 1 en el campo Obligatorio en la db");
				return new ResponseEntity<Map<String, Object>>( response, HttpStatus.NOT_ACCEPTABLE);
			}
		
		}else {
			response.put("mensaje","Empresa no encontrado en configuracion");
			response.put("error","El nombre del modulo  "+nombremodulo + " no tiene asignado configuracion");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/listar-obras/{idperiodoanio}")
	public ResponseEntity<?> listarObras(@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		List<UnidadNegocio> lista = new ArrayList<UnidadNegocio>();
		//Moneda moneda = null; 
		try {
			lista = otrosService.listarObras(idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<UnidadNegocio>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/listar-obras-administrativo/{idperiodoanio}")
	public ResponseEntity<?> listarObrasYAdministrativo(@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		List<UnidadNegocio> lista = new ArrayList<UnidadNegocio>();
		//Moneda moneda = null; 
		try {
			lista = otrosService.listarObrasAdmin(idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<UnidadNegocio>>(lista, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/get-periodo-anio/{nomModuloEmpresa}")
	public ResponseEntity<?> buscarPeriodoAnioPorNomModulo(@PathVariable String nomModuloEmpresa){
		Map<String, Object> response = new HashMap<>();
		PeriodoAnio periodoAnio = null; 
		//Moneda moneda = null; 
		try {
			periodoAnio = otrosService.buscarPeriodoAnioPorNomModulo(nomModuloEmpresa);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(periodoAnio == null) {
			return new ResponseEntity<String>("Nose encontro", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PeriodoAnio>(periodoAnio, HttpStatus.OK);
	}
	
	
	
}