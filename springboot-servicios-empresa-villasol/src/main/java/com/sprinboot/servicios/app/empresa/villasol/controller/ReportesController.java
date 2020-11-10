package com.sprinboot.servicios.app.empresa.villasol.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.servicios.app.empresa.villasol.dto.CancelacionDocDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.LibroDiarioTotalDto;
import com.sprinboot.servicios.app.empresa.villasol.dto.OptionsLibDiarioDto;
import com.sprinboot.servicios.app.empresa.villasol.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.villasol.jsons.ClaseResponse;
import com.sprinboot.servicios.app.empresa.villasol.jsons.HojaTrabajoRest;
import com.sprinboot.servicios.app.empresa.villasol.jsons.LibroMayorSisRest;
import com.sprinboot.servicios.app.empresa.villasol.jsons.RegistrosCabRest;
import com.sprinboot.servicios.app.empresa.villasol.service.AsientoServiceInterface;
import com.sprinboot.servicios.app.empresa.villasol.service.HojaTrabajoServiceInterface;
import com.sprinboot.servicios.app.empresa.villasol.service.MayorSistemaServiceInterface;
import com.sprinboot.servicios.app.empresa.villasol.service.RegistrosSistemaServiceInterface;
import com.sprinboot.servicios.app.empresa.villasol.service.ReporteLibroDiarioServiceInterface;
import com.sprinboot.servicios.app.empresa.villasol.solid.MayorSolesAcumulado;
import com.sprinboot.servicios.app.empresa.villasol.solid.MayorSolesMensual;
import com.sprinboot.servicios.app.empresa.villasol.solid.OpcionHojaTrabajo;
import com.sprinboot.servicios.app.empresa.villasol.solid.OpcionLibroMayorSis;
import com.sprinboot.servicios.app.empresa.villasol.solid.OpcionReporte;
import com.sprinboot.servicios.app.empresa.villasol.solid.RegistroSolesAcumulado;
import com.sprinboot.servicios.app.empresa.villasol.solid.RegistroSolesMensual;
import com.sprinboot.servicios.app.empresa.villasol.solid.SolesAcumuladoFecha;
import com.sprinboot.servicios.app.empresa.villasol.solid.SolesMensualFecha;

@RestController
@RequestMapping("reportes")
public class ReportesController {

	@Autowired
	AsientoServiceInterface asientoServiceInterface;
	
	@Autowired
	ReporteLibroDiarioServiceInterface reporteLibroDiarioServiceInterface;
	
	@Autowired
	RegistrosSistemaServiceInterface registrosSistemaServiceInterface;
	
	@Autowired
	HojaTrabajoServiceInterface hojaTrabajoService;
	
	@Autowired
	MayorSistemaServiceInterface mayorSistemaService;
	
	@PostMapping("/librodiario") 
	public ResponseEntity<?> showLibroDiario(@RequestBody OptionsLibDiarioDto op){
	     Map<String, Object> response = new HashMap<>();
	     LibroDiarioTotalDto libroDiarioTotalDto =null;
	     
	     // CodOrigen != null es porque tien ela opcion por origen
			if (op.getCodOrigen()!=null) {
				//periodo!=null es porque es mensual
				 if(op.getPeriodo()!=null) {
					
					try {
					    libroDiarioTotalDto = reporteLibroDiarioServiceInterface.libroDiarioMensualConOrigen(op.getPeriodo(), op.getMoneda(), op.getCodOrigen());
					} catch (DataAccessException e) {
						response.put("mensaje","Error en el Servidor");
						response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					 
					 response.put("libroDiarioDto", libroDiarioTotalDto);
					 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
					 //return this.getListLibroDiario(voucherDao.libroDiarioMensualConOrigen(periodo, codOrigen));
				}else {
					// es acumulado
					//condicional para asegurar que exista
					if (op.getMes()!=null && op.getAnio()!=null) {
						
						try {
						    libroDiarioTotalDto =  reporteLibroDiarioServiceInterface.libroDiarioAcumuladoConOrigen(op.getMoneda(), op.getAnio(), op.getMes(), op.getCodOrigen());
						} catch (DataAccessException e) {
							response.put("mensaje","Error en el Servidor");
							response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
							return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
						}
						 
						response.put("libroDiarioDto",libroDiarioTotalDto);
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
					
					}else {
						response.put("mensaje","Error en el Servidor");
						response.put("error","Opcion con origen activado, pero el mes o anio es null");
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
			}else {
				//es analitico o por resumen
				//es mensual
				if (op.getPeriodo()!=null) {
					try {
					    libroDiarioTotalDto =  reporteLibroDiarioServiceInterface.libroDiarioMensual(op.getPeriodo(), op.getMoneda());

					} catch (DataAccessException e) {
						response.put("mensaje","Error en el Servidor");
						response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					response.put("libroDiarioDto",libroDiarioTotalDto);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	
				}else {
					//es acumulado
					if (op.getMes()!=null && op.getAnio()!=null) {
						try {
						    libroDiarioTotalDto =  reporteLibroDiarioServiceInterface.libroDiarioAcumulado(op.getMoneda(),  op.getAnio(),op.getMes());
						} catch (DataAccessException e) {
							response.put("mensaje","Error en el Servidor");
							response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
							return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
						}
						response.put("libroDiarioDto", libroDiarioTotalDto);
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
					}else {
						response.put("mensaje","Error en el Servidor");
						response.put("error","Opcion analitco o resuemn, pero el mes o anio es null");
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
			}
			
		
	}
	
	
	@GetMapping(value="/registro-compras/soles-mensual-fecha/{codmes}/{anio}/{idperiodo}/{codplanparametro}/{codlibro}")
    public ResponseEntity<?> listRegistroDoc(@PathVariable Integer codmes,@PathVariable String anio,@PathVariable Integer idperiodo, @PathVariable String codplanparametro,@PathVariable String codlibro) throws ClaseException{
		Map<String, Object> response = new HashMap<>();
		RegistrosCabRest cab = new RegistrosCabRest();
		   
		try {
			    List<OpcionReporte> lstOpcion = new ArrayList<OpcionReporte>();
			    lstOpcion.add(new SolesMensualFecha(registrosSistemaServiceInterface.getDao(),registrosSistemaServiceInterface)) ;
		    	cab = registrosSistemaServiceInterface.reporteRegistros(lstOpcion, idperiodo, anio, codmes, codlibro, codplanparametro);
	            	
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  
		
			//return new ClaseResponse<RegistrosCabRest>(cab, HttpStatus.OK);
		return new ResponseEntity<RegistrosCabRest>(cab, HttpStatus.OK);
	}
	
	@GetMapping(value="/registro-compras/soles-acumulado-fecha/{codmes}/{anio}/{idperiodo}/{codplanparametro}/{codlibro}")
    public ResponseEntity<?> listRegistroDocSAD(@PathVariable Integer codmes,@PathVariable String anio,@PathVariable Integer idperiodo, @PathVariable String codplanparametro,@PathVariable String codlibro) throws ClaseException{
		Map<String, Object> response = new HashMap<>();
		RegistrosCabRest cab = new RegistrosCabRest();
		   
		try {
			    List<OpcionReporte> lstOpcion = new ArrayList<OpcionReporte>();
			    lstOpcion.add(new SolesAcumuladoFecha(registrosSistemaServiceInterface.getDao(),registrosSistemaServiceInterface)) ;
		    	cab = registrosSistemaServiceInterface.reporteRegistros(lstOpcion, idperiodo, anio, codmes, codlibro, codplanparametro);
	            	
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  
		   
			return new ResponseEntity<RegistrosCabRest>(cab, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/registro-ventas/soles-mensual-fecha/{codmes}/{anio}/{idperiodo}/{codplanparametro}/{codlibro}")
    public ResponseEntity<?> listRegistroVentasDocSMD(@PathVariable Integer codmes,@PathVariable String anio,@PathVariable Integer idperiodo, @PathVariable String codplanparametro,@PathVariable String codlibro) throws ClaseException{
		Map<String, Object> response = new HashMap<>();
		RegistrosCabRest cab = new RegistrosCabRest();
		   
		try {
			    List<OpcionReporte> lstOpcion = new ArrayList<OpcionReporte>();
			    lstOpcion.add(new SolesMensualFecha(registrosSistemaServiceInterface.getDao(),registrosSistemaServiceInterface)) ;
		    	cab = registrosSistemaServiceInterface.reporteRegistros(lstOpcion, idperiodo, anio, codmes, codlibro, codplanparametro);
	            	
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  
		
			return new ResponseEntity<RegistrosCabRest>(cab, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/registro-ventas/soles-acumulado-fecha/{codmes}/{anio}/{idperiodo}/{codplanparametro}/{codlibro}")
    public ResponseEntity<?> listRegistroVentasDocSAD(@PathVariable Integer codmes,@PathVariable String anio,@PathVariable Integer idperiodo, @PathVariable String codplanparametro,@PathVariable String codlibro) throws ClaseException{
		Map<String, Object> response = new HashMap<>();
		RegistrosCabRest cab = new RegistrosCabRest();
		   
		try {
			    List<OpcionReporte> lstOpcion = new ArrayList<OpcionReporte>();
			    lstOpcion.add(new SolesAcumuladoFecha(registrosSistemaServiceInterface.getDao(),registrosSistemaServiceInterface)) ;
		    	cab = registrosSistemaServiceInterface.reporteRegistros(lstOpcion, idperiodo, anio, codmes, codlibro, codplanparametro);
	            	
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  
		
			return new ResponseEntity<RegistrosCabRest>(cab, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value="/hoja-trabajo/registro-soles-mensual/{codmes}/{anio}/{idperiodo}/{idperiodoanio}")
    public ResponseEntity<?> hojaTrabajoRegistroMensualSoles(@PathVariable Integer codmes,@PathVariable String anio,@PathVariable Integer idperiodo, @PathVariable Integer idperiodoanio) throws ClaseException{
		Map<String, Object> response = new HashMap<>();
		HojaTrabajoRest hojaTrabajo = new HojaTrabajoRest();
		   
		try {
			    List<OpcionHojaTrabajo> lstOpcion = new ArrayList<OpcionHojaTrabajo>();
			   lstOpcion.add(new RegistroSolesMensual(hojaTrabajoService)) ;
			    
			    hojaTrabajo = hojaTrabajoService.reporteRegistros(lstOpcion, idperiodo, anio, codmes, idperiodoanio);
	            	
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  
		
			return new ResponseEntity<HojaTrabajoRest>(hojaTrabajo, HttpStatus.OK);
	}
	
	@GetMapping(value="/hoja-trabajo/registro-soles-acumulado/{codmes}/{anio}/{idperiodo}/{idperiodoanio}")
    public ResponseEntity<?> hojaTrabajoRegistroAcumuladoSoles(@PathVariable Integer codmes,@PathVariable String anio,@PathVariable Integer idperiodo, @PathVariable Integer idperiodoanio) throws ClaseException{
		Map<String, Object> response = new HashMap<>();
		HojaTrabajoRest hojaTrabajo = new HojaTrabajoRest();
		   
		try {
			    List<OpcionHojaTrabajo> lstOpcion = new ArrayList<OpcionHojaTrabajo>();
			   lstOpcion.add(new RegistroSolesAcumulado(hojaTrabajoService)) ;
			    
			    hojaTrabajo = hojaTrabajoService.reporteRegistros(lstOpcion, idperiodo, anio, codmes, idperiodoanio);
	            	
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  
		
			return new ResponseEntity<HojaTrabajoRest>(hojaTrabajo, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/libro-mayor/registro-soles-mensual/{codmes}/{anio}/{idperiodo}/{idperiodoanio}/{codcuenta}")
    public ResponseEntity<?> libromayorregistrosolesmensual(@PathVariable Integer codmes,@PathVariable String anio,@PathVariable Integer idperiodo, @PathVariable Integer idperiodoanio,@PathVariable String codcuenta) throws ClaseException{
		Map<String, Object> response = new HashMap<>();
		List<LibroMayorSisRest> listLibroMayor = new ArrayList<LibroMayorSisRest>();
		   
		try {
			   List<OpcionLibroMayorSis> lstOpcion = new ArrayList<OpcionLibroMayorSis>();
			   lstOpcion.add(new MayorSolesMensual(mayorSistemaService)) ;
			    
			   listLibroMayor = mayorSistemaService.reporteRegistros(lstOpcion, idperiodo, anio, codmes, idperiodoanio,codcuenta);
	            	
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  
		
			return new ResponseEntity<List<LibroMayorSisRest>>(listLibroMayor, HttpStatus.OK);
	}
	

	@GetMapping(value="/libro-mayor/registro-soles-acumulado/{codmes}/{anio}/{idperiodo}/{idperiodoanio}/{codcuenta}")
    public ResponseEntity<?> libromayorregistrosolesacumulado(@PathVariable Integer codmes,@PathVariable String anio,@PathVariable Integer idperiodo, @PathVariable Integer idperiodoanio,@PathVariable String codcuenta) throws ClaseException{
		Map<String, Object> response = new HashMap<>();
		List<LibroMayorSisRest> listLibroMayor = new ArrayList<LibroMayorSisRest>();
		   
		try {
			   List<OpcionLibroMayorSis> lstOpcion = new ArrayList<OpcionLibroMayorSis>();
			   lstOpcion.add(new MayorSolesAcumulado(mayorSistemaService)) ;
			    
			   listLibroMayor = mayorSistemaService.reporteRegistros(lstOpcion, idperiodo, anio, codmes, idperiodoanio,codcuenta);
	            	
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  
		
			return new ResponseEntity<List<LibroMayorSisRest>>(listLibroMayor, HttpStatus.OK);
	}
}
