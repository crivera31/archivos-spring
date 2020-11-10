package com.sprinboot.servicios.app.admin.base.controllers;

import java.util.ArrayList;
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

import com.sprinboot.servicios.app.otros.commons.models.entity.ClasificacionBienes;
import com.sprinboot.servicios.app.otros.commons.models.entity.Empresa;
import com.sprinboot.servicios.app.otros.commons.models.entity.Moneda;
import com.sprinboot.servicios.app.otros.commons.models.entity.NivelCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.OpcionPlanCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.Origen;
import com.sprinboot.servicios.app.otros.commons.models.entity.ParametrosAsis;
import com.sprinboot.servicios.app.otros.commons.models.entity.PlanCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoAnalisis;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoCuenta;
import com.sprinboot.servicios.usuario.common.models.entity.Role;
import com.sprinboot.servicios.app.admin.base.dto.PlanContableDto;
import com.sprinboot.servicios.app.admin.base.models.services.IOtrosServiceImpl;
import com.sprinboot.servicios.app.admin.base.models.services.IPlanCuentaServiceImpl;
import com.sprinboot.servicios.app.admin.base.models.services.PlanCuentaService;
import com.sprinboot.servicios.app.admin.base.dto.CuentaDetalladaDto;

@RestController
@RequestMapping("/plan-cuenta")
public class PlanCuentaRestController {

	@Autowired
	private IPlanCuentaServiceImpl planCuenta;

	@Autowired
	private IOtrosServiceImpl otrosServiceImpl;
	
	
	@GetMapping("/lista")
	public List<PlanCuenta> index() {
		return planCuenta.findByEnabledPaged();
	}

	@GetMapping("/lista/page/{idperiodoanio}/{idempresa}/{page}")
	public Page<PlanCuenta> index(@PathVariable Integer idperiodoanio, @PathVariable Long idempresa ,@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 3000);
		return planCuenta.findByEnabledPagedPorEmpresa(idperiodoanio,idempresa,pageable);
	}

	@GetMapping("/nivel-cuenta")
	public List<NivelCuenta> nivelCuenta() {
		return planCuenta.findNivelCuentaAll();
	}

	@GetMapping("/moneda")
	public List<Moneda> moneda() {
		return planCuenta.findMonedaAll();
	}

	@GetMapping("/opcion-cuenta")
	public List<OpcionPlanCuenta> opcionCuenta() {
		return planCuenta.findOpcionPlanCuentaAll();
	}

	@GetMapping("/tipo-analisis")
	public List<TipoAnalisis> tipoAnalisis() {
		return planCuenta.findTipoAnalisisAll();
	}

	@GetMapping("/tipo-cuenta")
	public List<TipoCuenta> tipoCuenta() {
		return planCuenta.findTipoCuentaAll();
	}

	@PostMapping("/agregar")
	public ResponseEntity<?> create(@Valid @RequestBody PlanCuenta planCuentaVar, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		PlanCuenta planCuentaNew = null;
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			response.put("respuesta_error", planCuentaVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		planCuentaNew = planCuenta.buscarPlanCuentaPorCod(planCuentaVar.getCodCuenta(), planCuentaVar.getPeriodoAnio().getId());
		if (!(planCuentaNew==null)) {
			response.put("mensaje", "Esta cuenta ya fue creada para esta empresa");
			response.put("respuesta Servidor", planCuentaVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			planCuentaNew = planCuenta.save(planCuentaVar);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error en el Servidor");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor", planCuentaVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Plan de cuenta fue creado con éxito");
		response.put("cliente", planCuentaVar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody PlanCuenta planCuentaVar, BindingResult result,
			@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' :" + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		PlanCuenta planCuentaActual = planCuenta.findById(id);
		if (planCuentaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el Plan de Cuenta con el ID "
					.concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		PlanCuenta update = null;
		try {
			update = planCuenta.save(planCuentaVar);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en el servidor");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente fue actualizado con éxito");
		response.put("cliente", update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/moneda/{id}")
	public ResponseEntity<?> showMoneda(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		Moneda moneda = null;
		try {
			moneda = planCuenta.findMonedaById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(moneda == null) {
			response.put("mensaje","La MONEDA con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Moneda>(moneda, HttpStatus.OK);
	}
	
	
	@GetMapping("/plan-cuenta/{id}")
	public ResponseEntity<?> showPlanCuenta(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		PlanCuenta planCuentaEntity = null;
		//Moneda moneda = null;
		try {
			planCuentaEntity = planCuenta.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(planCuentaEntity == null) {
			response.put("mensaje","El planCuenta con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PlanCuenta>(planCuentaEntity, HttpStatus.OK);
	}
	
	

	
	@GetMapping("/plan-cuenta/find/{cod}/{idperiodoanio}")
	public ResponseEntity<?> buscarPlanCuentaPorCod(@PathVariable String cod,@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		PlanCuenta planCuentaEntity = null;
		try {
			planCuentaEntity = planCuenta.buscarPlanCuentaPorCod(cod,idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(planCuentaEntity == null) {
			response.put("mensaje","El planCuenta con el ID ".concat(cod.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PlanCuenta>(planCuentaEntity, HttpStatus.OK);
	}
	
	
	@GetMapping("/plan-cuenta/parametros-asis/{origen}/{moneda}/{anio}/{idperiodoanio}")
	public ResponseEntity<?> buscarPlanCuentaPorCod(@PathVariable Integer origen,@PathVariable Integer moneda,@PathVariable Integer anio,@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		  ParametrosAsis parametrosAsis = null;
		  PlanCuenta planCuentaEntity = null;
		try {
			parametrosAsis = otrosServiceImpl.getParametrosbyPeriodoAndOrigen(anio,origen);
			
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor con paraasis");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(parametrosAsis == null) {
			response.put("mensaje","El origen con el ID ".concat(origen.toString().concat("no existe en la base de datos asociado algun parametrio")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			try {
				switch (moneda) {
				case 1:	planCuentaEntity = planCuenta.buscarPlanCuentaPorCod(parametrosAsis.getSoles(),idperiodoanio);	break;
				case 2:	planCuentaEntity = planCuenta.buscarPlanCuentaPorCod(parametrosAsis.getDolares(),idperiodoanio);	break;
				default: 
					response.put("mensaje","El planCuenta con el ID ".concat(origen.toString().concat(" origen no existe en la base de datos asociado algun parametrio")));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
				}
				
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor con Plan");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
			
		return new ResponseEntity<PlanCuenta>(planCuentaEntity, HttpStatus.OK);
	}
	

	@GetMapping("/listar-empresas")
	public List<Empresa> empresas() {
		return otrosServiceImpl.listarEmpresa();
	}
	
	@GetMapping("/listar-porcod-repet/{plan}/{periodoAnioid}")
	public ResponseEntity<?> listarPorCod(@PathVariable String plan,@PathVariable Integer periodoAnioid){
		Map<String, Object> response = new HashMap<>();
		List<PlanCuenta> listPlanCuenta =  new ArrayList<>();
		try {
			listPlanCuenta = planCuenta.buscarPlanCuentaPorCodigo(plan,periodoAnioid);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(listPlanCuenta.size()<1) {
			response.put("mensaje","El plan con el cod ".concat(plan.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {

			return new ResponseEntity<List<PlanCuenta>>(listPlanCuenta, HttpStatus.OK);
		}		
	}
	
	@GetMapping("/filtrar-cuenta/{plan}/empresa/{idperiodoanio}")
	public ResponseEntity<?> buscarPlanPorEmpresa(@PathVariable String plan,@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		List<PlanCuenta> listPlanCuenta =  new ArrayList<>();
		try {
			listPlanCuenta = planCuenta.buscarPlanCuentaPorCodigoEmpresa(plan,idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor al intentar obtener plan");
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
	
	@GetMapping("/listar-filtro-cuenta/{plan}/empresa/{idperiodoanio}")
	public ResponseEntity<?> listarPlanPorEmpresa(@PathVariable String plan,@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		List<PlanCuenta> listPlanCuenta =  new ArrayList<>();
		try {
			listPlanCuenta = planCuenta.filtrarPorCodigoLikeEmpresa(plan,idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor al intentar obtener plan");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(listPlanCuenta.size()<1) {
			response.put("mensaje","planes con el codigo ".concat(plan.toString().concat(" no existen en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<PlanCuenta>>(listPlanCuenta, HttpStatus.OK);
		}
	}
	
	

	@GetMapping("/listar-clasificacion-bienes")
	public List<ClasificacionBienes> listarClasificacion() {
		return planCuenta.listarClasificacionBienes();
	}
	
	@GetMapping("/plan-contable-ordenado/{anio}/{idperiodoanio}")
	public ResponseEntity<?> listarPlanContableOrdenado(@PathVariable String anio,@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		List<PlanContableDto> lstDto = new ArrayList<>();
		try {
			lstDto = planCuenta.listarPlanContablePorEmpresaDto(anio, idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor al intentar obtener plan");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(lstDto.size()<1) {
			response.put("mensaje","La lista esta vacia");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<PlanContableDto>>(lstDto, HttpStatus.OK);
		}
	}
	
	
	
	@GetMapping("/filtro-planes/detallado-por-empresa/{anio}/{idperiodoanio}/{nivelcuenta}")
	public ResponseEntity<?> listarPlanContableDetalladoOrdenado(@PathVariable String anio,@PathVariable Integer idperiodoanio,@PathVariable String nivelcuenta){
		Map<String, Object> response = new HashMap<>();
		List<CuentaDetalladaDto> lstDto = new ArrayList<>();
		try {
			lstDto = planCuenta.listarPlanContableDetalladoOrdenado(anio, idperiodoanio,nivelcuenta);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor al intentar obtener plan");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(lstDto.size()<1) {
			response.put("mensaje","La lista esta vacia");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<CuentaDetalladaDto>>(lstDto, HttpStatus.OK);
		}
		
	}
	/*@GetMapping("/ples/plan-cuenta/{id}")
	public ResponseEntity<?> buscarPlanCuentaForPles(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		PlanCuenta planCuentaEntity = null;
		//Moneda moneda = null;
		try {
			planCuentaEntity = planCuenta.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(planCuentaEntity == null) {
			response.put("mensaje","El planCuenta con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("codCuenta",planCuentaEntity.getCodCuenta());
		response.put("nomCuenta",planCuentaEntity.getNombreCuenta());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/ples/filtrar-cuenta/{plan}/empresa/{idperiodoanio}")
	public ResponseEntity<?> buscarPlanPorEmpresaForPles(@PathVariable String plan,@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		List<PlanCuenta> listPlanCuenta =  new ArrayList<>();
		try {
			listPlanCuenta = planCuenta.buscarPlanCuentaPorCodigoEmpresa(plan,idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor al intentar obtener plan");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(listPlanCuenta.size()<1) {
			response.put("mensaje","El plan con el id ".concat(plan.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			if(listPlanCuenta.size()>1) {
				response.put("mensaje","El id del plan  ".concat(plan.toString().concat("esta duplicado")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
			}else {
				response.put("codCuenta",listPlanCuenta.get(0).getCodCuenta());
				response.put("nomCuenta",listPlanCuenta.get(0).getNombreCuenta());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		}
	}*/
	
	
	
	
	
	
	/*METODO DE PRUEBA NO USAR*/
	@GetMapping("/ordenar/{idperiodoanio}")
	public ResponseEntity<?> ordenar(@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		
		try {
			planCuenta.planOriginal(idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor al intentar obtener plan");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/ordenar-amarres/{idperiodoanio}")
	public ResponseEntity<?> ordenarAmarres(@PathVariable Integer idperiodoanio){
		Map<String, Object> response = new HashMap<>();
		
		try {
			planCuenta.planPorEmpresa(idperiodoanio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor al intentar obtener plan");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	
}
