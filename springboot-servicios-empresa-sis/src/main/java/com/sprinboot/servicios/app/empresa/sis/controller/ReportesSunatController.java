package com.sprinboot.servicios.app.empresa.sis.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.servicios.app.empresa.sis.client.DataServiceClient;
import com.sprinboot.servicios.app.empresa.sis.dto.CancelacionDocDto;
import com.sprinboot.servicios.app.empresa.sis.dto.LEDMCuentaDto;
import com.sprinboot.servicios.app.empresa.sis.dto.LEDiarioDto;
import com.sprinboot.servicios.app.empresa.sis.dto.LEDiarioPorOrigenDao;
import com.sprinboot.servicios.app.empresa.sis.dto.MsgxDto;
import com.sprinboot.servicios.app.empresa.sis.dto.PlanContableDto;
import com.sprinboot.servicios.app.empresa.sis.dto.RegistroComprasDetDto;
import com.sprinboot.servicios.app.empresa.sis.dto.RegistroComprasDto;
import com.sprinboot.servicios.app.empresa.sis.dto.ValidarLibroSPDao;
import com.sprinboot.servicios.app.empresa.sis.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.sis.funciones.JasperSpring;
import com.sprinboot.servicios.app.empresa.sis.service.AsientoServiceInterface;
import com.sprinboot.servicios.app.empresa.sis.service.ILibroMayorService;
import com.sprinboot.servicios.app.empresa.sis.service.RegistroLibrosServiceInterface;
import com.sprinboot.servicios.app.empresa.sis.service.ReporteLIbroDiarioService;
import com.sprinboot.servicios.app.empresa.sis.service.ReporteLibroDiarioServiceInterface;

import org.springframework.core.io.Resource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

@RestController
@RequestMapping("reportes-sunat")
public class ReportesSunatController {

	@Autowired
	RegistroLibrosServiceInterface registroLibrosServiceInterface;
	

	@Autowired
	ReporteLibroDiarioServiceInterface reporteLIbroDiarioService ;
	
	@Autowired
    DataServiceClient dataServiceClient; 
	
	@Autowired
	ILibroMayorService libroMayorService ;
	
	@Autowired
	AsientoServiceInterface asientoService;
	
	@Autowired
	JasperSpring jasperSpring;
	

	@GetMapping(value="/registro-auxiliar-compras/{idperiodo}/{anio}/{codmes}/{codlibro}/{codCtaParametro}/{codClasificacion}/{codunidadnegocio}")
    public ResponseEntity<?> listRegistroAuxiliaresCompras(@PathVariable Integer idperiodo, @PathVariable String anio,@PathVariable Integer codmes,@PathVariable String codlibro,@PathVariable String codCtaParametro,@PathVariable String codClasificacion,@PathVariable String codunidadnegocio) {
		Map<String, Object> response = new HashMap<>();
		String codDocumentos[] = {"01","02","03","04","05","06","07","08","10","11","12","13","14","15","16","17","18","19","21","22","23","24","25","26","27","28","29","30","32","34","35","36","37","42","43","44","45","46","48","49","50","51","52","53","54","55","56","87","88","89","96"};	

		RegistroComprasDto registroComprasDto= new RegistroComprasDto();   
		List<RegistroComprasDetDto> listRegistroComprasDetDto=new ArrayList<RegistroComprasDetDto>();
		
		List<ValidarLibroSPDao> validacionList = new ArrayList<>();
		   try {
			   validacionList = asientoService.getValidacionSP();
			   if(validacionList.size()> 0) {
				   String mensaje = "Existen Repeticion de libros , se recomienda verificar los asientos , si es necesario borrar los datos de cada asiento y volver a ingresar el voucher, los asientos con repeticion de vouchers son: ";
				   
				   for (ValidarLibroSPDao v : validacionList) {
					   mensaje = mensaje + "**** ASIENTO = " + v.getNum_asiento()+ " | ORIGEN = "+v.getCod_origen()+" | MES = "+v.getCod_mes() + " | AÑO = "+v.getAnio()+ " ****";
				   }
				   response.put("mensaje",mensaje);
				   return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			   }
			} catch (Exception e) {
			    	 response.put("mensaje","Error en el servidor al llamar SP VALIDAR LIBROS, revisar conexion a internet");
					response.put("error",e.getMessage());		
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 			     //ERROR 500
 			
			}
         
		
		System.out.println("coDO "+codDocumentos.length);
		try {
			for (int i = 0; i < codDocumentos.length; i++) {
				System.out.println("ENRO AL ARRAY "+i);
				RegistroComprasDetDto rs= new RegistroComprasDetDto();
				rs=null;																	//03  codigo libro auxiliar comrpas y 0001 codigo RENACER LORETO
				rs= registroLibrosServiceInterface.listarRegistroAuxiliares(codDocumentos[i], idperiodo, codmes, anio, 2,codlibro,codCtaParametro,"03",codunidadnegocio);
			    if(rs!=null) {
					listRegistroComprasDetDto.add(rs);
			    }
			}
				System.out.println("ACABO EL FOR EN EL CONTROLER");
	            registroComprasDto.setListRegistroCompraDet(listRegistroComprasDetDto);	
	            
	            try {
	            	registroComprasDto = registroLibrosServiceInterface.calcularTotales(registroComprasDto, 2);
					
				} catch (DataAccessException ed) {
					response.put("mensaje","Error en el Servidor DataAccesException en Calculando totales");
					response.put("error",ed.getMessage().concat(": ").concat(ed.getMostSpecificCause().getMessage()));		
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
	            
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  /* if (listCancelacion==null) {
			   response.put("mensaje","El cod ".concat(codigo.toString().concat(" no tiene registros ")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}*/ 
		
			return new ResponseEntity<RegistroComprasDto>(registroComprasDto, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/registro-auxiliar-ventas/{idperiodo}/{anio}/{codmes}/{codlibro}/{codCtaParametro}/{codClasificacion}/{codunidadnegocio}")
    public ResponseEntity<?> listRegistroAuxiliarVentas(@PathVariable Integer idperiodo, @PathVariable String anio,@PathVariable Integer codmes,@PathVariable String codlibro,@PathVariable String codCtaParametro,@PathVariable String codClasificacion,@PathVariable String codunidadnegocio) {
		Map<String, Object> response = new HashMap<>();
		String codDocumentos[] = {"01","03","04","05","06","07","08","11","12","13","14","15","16","17","18","19","21","23","24","25","26","27","28","29","30","32","34","35","36","37","42","43","44","45","48","49","55","56","87","88"};	

		RegistroComprasDto registroComprasDto= new RegistroComprasDto();   
		List<RegistroComprasDetDto> listRegistroComprasDetDto=new ArrayList<RegistroComprasDetDto>();
		
		
		List<ValidarLibroSPDao> validacionList = new ArrayList<>();
		   try {
			   validacionList = asientoService.getValidacionSP();
			   if(validacionList.size()> 0) {
				   String mensaje = "Existen Repeticion de libros , se recomienda verificar los asientos , si es necesario borrar los datos de cada asiento y volver a ingresar el voucher, los asientos con repeticion de vouchers son: ";
				   
				   for (ValidarLibroSPDao v : validacionList) {
					   mensaje = mensaje + "**** ASIENTO = " + v.getNum_asiento()+ " | ORIGEN = "+v.getCod_origen()+" | MES = "+v.getCod_mes() + " | AÑO = "+v.getAnio()+ " ****";
				   }
				   response.put("mensaje",mensaje);
				   return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			   }
			} catch (Exception e) {
			    	 response.put("mensaje","Error en el servidor al llamar SP VALIDAR LIBROS, revisar conexion a internet");
					response.put("error",e.getMessage());		
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			     //ERROR 500
			
			}
      
		
		
		try {
			for (int i = 0; i < codDocumentos.length; i++) {
				System.out.println("ENRO AL ARRAY "+i);
				RegistroComprasDetDto rs= new RegistroComprasDetDto();
				rs=null;
				rs= registroLibrosServiceInterface.listarRegistroAuxiliares(codDocumentos[i], idperiodo, codmes, anio, 2,codlibro,codCtaParametro,"04",codunidadnegocio);
			    if(rs!=null) {
					listRegistroComprasDetDto.add(rs);
			    }
			}
				System.out.println("ACABO EL FOR EN EL CONTROLER");
	            registroComprasDto.setListRegistroCompraDet(listRegistroComprasDetDto);	
	            
	            try {
	            	registroComprasDto = registroLibrosServiceInterface.calcularTotales(registroComprasDto, 2);
					
				} catch (DataAccessException ed) {
					response.put("mensaje","Error en el Servidor DataAccesException en Calculando totales");
					response.put("error",ed.getMessage().concat(": ").concat(ed.getMostSpecificCause().getMessage()));		
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
	            
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  /* if (listCancelacion==null) {
			   response.put("mensaje","El cod ".concat(codigo.toString().concat(" no tiene registros ")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}*/ 
		
			return new ResponseEntity<RegistroComprasDto>(registroComprasDto, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/registro-compras/{idperiodo}/{anio}/{codmes}/{codlibro}/{codCtaParametro}/{codClasificacion}")
    public ResponseEntity<?> listRegistroDoc(@PathVariable Integer idperiodo, @PathVariable String anio,@PathVariable Integer codmes,@PathVariable String codlibro,@PathVariable String codCtaParametro,@PathVariable String codClasificacion) {
		Map<String, Object> response = new HashMap<>();
		String codDocumentos[] = {"01","02","03","04","05","06","07","08","10","11","12","13","14","15","16","17","18","19","21","22","23","24","25","26","27","28","29","30","32","34","35","36","37","42","43","44","45","46","48","49","50","51","52","53","54","55","56","87","88","89","96"};	

		RegistroComprasDto registroComprasDto= new RegistroComprasDto();   
		List<RegistroComprasDetDto> listRegistroComprasDetDto=new ArrayList<RegistroComprasDetDto>();
		
		List<ValidarLibroSPDao> validacionList = new ArrayList<>();
		   try {
			   validacionList = asientoService.getValidacionSP();
			   if(validacionList.size()> 0) {
				   String mensaje = "Existen Repeticion de libros , se recomienda verificar los asientos , si es necesario borrar los datos de cada asiento y volver a ingresar el voucher, los asientos con repeticion de vouchers son: ";
				   
				   for (ValidarLibroSPDao v : validacionList) {
					   mensaje = mensaje + "**** ASIENTO = " + v.getNum_asiento()+ " | ORIGEN = "+v.getCod_origen()+" | MES = "+v.getCod_mes() + " | AÑO = "+v.getAnio()+ " ****";
				   }
				   response.put("mensaje",mensaje);
				   return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			   }
			} catch (Exception e) {
			    	 response.put("mensaje","Error en el servidor al llamar SP VALIDAR LIBROS, revisar conexion a internet");
					response.put("error",e.getMessage());		
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 			     //ERROR 500
 			
			}
         
		
		System.out.println("coDO "+codDocumentos.length);
		try {
			for (int i = 0; i < codDocumentos.length; i++) {
				System.out.println("ENRO AL ARRAY "+i);
				RegistroComprasDetDto rs= new RegistroComprasDetDto();
				rs=null;
				rs= registroLibrosServiceInterface.listarRegistroDeCompras(codDocumentos[i], idperiodo, codmes, anio, 2,codlibro,codCtaParametro);
			    if(rs!=null) {
					listRegistroComprasDetDto.add(rs);
			    }
			}
				System.out.println("ACABO EL FOR EN EL CONTROLER");
	            registroComprasDto.setListRegistroCompraDet(listRegistroComprasDetDto);	
	            
	            try {
	            	registroComprasDto = registroLibrosServiceInterface.calcularTotales(registroComprasDto, 2);
					
				} catch (DataAccessException ed) {
					response.put("mensaje","Error en el Servidor DataAccesException en Calculando totales");
					response.put("error",ed.getMessage().concat(": ").concat(ed.getMostSpecificCause().getMessage()));		
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
	            
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  /* if (listCancelacion==null) {
			   response.put("mensaje","El cod ".concat(codigo.toString().concat(" no tiene registros ")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}*/ 
		
			return new ResponseEntity<RegistroComprasDto>(registroComprasDto, HttpStatus.OK);
	}
	

	@GetMapping(value="/registro-compras/pdf/{idperiodo}/{anio}/{codmes}/{codlibro}/{codCtaParametro}/{codClasificacion}/{nommes}")
    public ResponseEntity<?> registroComprasPdf(@PathVariable Integer idperiodo, @PathVariable String anio,@PathVariable Integer codmes,@PathVariable String codlibro,@PathVariable String codCtaParametro,@PathVariable String codClasificacion,@PathVariable String nommes) {
		Map<String, Object> response = new HashMap<>();
		String codDocumentos[] = {"01","02","03","04","05","06","07","08","10","11","12","13","14","15","16","17","18","19","21","22","23","24","25","26","27","28","29","30","32","34","35","36","37","42","43","44","45","46","48","49","50","51","52","53","54","55","56","87","88","89","96"};	

		RegistroComprasDto registroComprasDto= new RegistroComprasDto();   
		List<RegistroComprasDetDto> listRegistroComprasDetDto=new ArrayList<RegistroComprasDetDto>();
		
		List<ValidarLibroSPDao> validacionList = new ArrayList<>();
		   try {
			   validacionList = asientoService.getValidacionSP();
			   if(validacionList.size()> 0) {
				   String mensaje = "Existen Repeticion de libros , se recomienda verificar los asientos , si es necesario borrar los datos de cada asiento y volver a ingresar el voucher, los asientos con repeticion de vouchers son: ";
				   
				   for (ValidarLibroSPDao v : validacionList) {
					   mensaje = mensaje + "**** ASIENTO = " + v.getNum_asiento()+ " | ORIGEN = "+v.getCod_origen()+" | MES = "+v.getCod_mes() + " | AÑO = "+v.getAnio()+ " ****";
				   }
				   response.put("mensaje",mensaje);
				   return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			   }
			} catch (Exception e) {
			    	 response.put("mensaje","Error en el servidor al llamar SP VALIDAR LIBROS, revisar conexion a internet");
					response.put("error",e.getMessage());		
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 			     //ERROR 500
 			
			}
         
		
		System.out.println("coDO "+codDocumentos.length);
		try {
			for (int i = 0; i < codDocumentos.length; i++) {
				System.out.println("ENRO AL ARRAY "+i);
				RegistroComprasDetDto rs= new RegistroComprasDetDto();
				rs=null;
				rs= registroLibrosServiceInterface.listarRegistroDeCompras(codDocumentos[i], idperiodo, codmes, anio, 2,codlibro,codCtaParametro);
			    if(rs!=null) {
					listRegistroComprasDetDto.add(rs);
			    }
			}
				System.out.println("ACABO EL FOR EN EL CONTROLER");
	            registroComprasDto.setListRegistroCompraDet(listRegistroComprasDetDto);	
	            
	            try {
	            	registroComprasDto = registroLibrosServiceInterface.calcularTotales(registroComprasDto, 2);
					
				} catch (DataAccessException ed) {
					response.put("mensaje","Error en el Servidor DataAccesException en Calculando totales");
					response.put("error",ed.getMessage().concat(": ").concat(ed.getMostSpecificCause().getMessage()));		
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
	            
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  /* if (listCancelacion==null) {
			   response.put("mensaje","El cod ".concat(codigo.toString().concat(" no tiene registros ")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}*/ 
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("mes", "REGISTRO DE COMPRAS DEL MES DE "+ nommes);
		 parameters.put("constructora", "ORIANA CONTRATISTAS GENERALES S.R.L.");
		 parameters.put("direccion", "CAL BRASIL NRO. 144 DPTO 3");
		 parameters.put("ejercicio", "EJERCICIO 2020");
		 parameters.put("ruc", "20445667618");
		 return jasperSpring.doReporte(new Object[] {registroComprasDto},"sunat/librocompras",parameters);
			//return new ResponseEntity<RegistroComprasDto>(registroComprasDto, HttpStatus.OK);
	}
	
	

	
	
	@GetMapping(value="/registro-ventas/{idperiodo}/{anio}/{codmes}/{codlibro}/{codCtaParametro}/{codClasificacion}")
    public ResponseEntity<?> listRegistroDocVentas(@PathVariable Integer idperiodo, @PathVariable String anio,@PathVariable Integer codmes,@PathVariable String codlibro,@PathVariable String codCtaParametro,@PathVariable String codClasificacion) {
		Map<String, Object> response = new HashMap<>();
		String codDocumentos[] = {"01","03","04","05","06","07","08","11","12","13","14","15","16","17","18","19","21","23","24","25","26","27","28","29","30","32","34","35","36","37","42","43","44","45","48","49","55","56","87","88"};	

		RegistroComprasDto registroComprasDto= new RegistroComprasDto();   
		List<RegistroComprasDetDto> listRegistroComprasDetDto=new ArrayList<RegistroComprasDetDto>();
		
		
		List<ValidarLibroSPDao> validacionList = new ArrayList<>();
		   try {
			   validacionList = asientoService.getValidacionSP();
			   if(validacionList.size()> 0) {
				   String mensaje = "Existen Repeticion de libros , se recomienda verificar los asientos , si es necesario borrar los datos de cada asiento y volver a ingresar el voucher, los asientos con repeticion de vouchers son: ";
				   
				   for (ValidarLibroSPDao v : validacionList) {
					   mensaje = mensaje + "**** ASIENTO = " + v.getNum_asiento()+ " | ORIGEN = "+v.getCod_origen()+" | MES = "+v.getCod_mes() + " | AÑO = "+v.getAnio()+ " ****";
				   }
				   response.put("mensaje",mensaje);
				   return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			   }
			} catch (Exception e) {
			    	 response.put("mensaje","Error en el servidor al llamar SP VALIDAR LIBROS, revisar conexion a internet");
					response.put("error",e.getMessage());		
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			     //ERROR 500
			
			}
      
		
		
		try {
			for (int i = 0; i < codDocumentos.length; i++) {
				System.out.println("ENRO AL ARRAY "+i);
				RegistroComprasDetDto rs= new RegistroComprasDetDto();
				rs=null;
				rs= registroLibrosServiceInterface.listarRegistroDeCompras(codDocumentos[i], idperiodo, codmes, anio, 2,codlibro,codCtaParametro);
			    if(rs!=null) {
					listRegistroComprasDetDto.add(rs);
			    }
			}
				System.out.println("ACABO EL FOR EN EL CONTROLER");
	            registroComprasDto.setListRegistroCompraDet(listRegistroComprasDetDto);	
	            
	            try {
	            	registroComprasDto = registroLibrosServiceInterface.calcularTotales(registroComprasDto, 2);
					
				} catch (DataAccessException ed) {
					response.put("mensaje","Error en el Servidor DataAccesException en Calculando totales");
					response.put("error",ed.getMessage().concat(": ").concat(ed.getMostSpecificCause().getMessage()));		
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
	            
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  /* if (listCancelacion==null) {
			   response.put("mensaje","El cod ".concat(codigo.toString().concat(" no tiene registros ")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}*/ 
		
			return new ResponseEntity<RegistroComprasDto>(registroComprasDto, HttpStatus.OK);
	}
	
	

	@GetMapping(value="/libro-diario/{anio}/{codmes}")
    public ResponseEntity<?> listRegistroLibroDiario( @PathVariable String anio,@PathVariable Integer codmes) {
		Map<String, Object> response = new HashMap<>();
		String codOrigenes[] = {"01","02","03","04","05"};	
		LEDiarioDto diarioDto= new LEDiarioDto();   
		List<LEDiarioPorOrigenDao> lstDiarioPorOrigenDao =new ArrayList<LEDiarioPorOrigenDao>();
		try {
			for (int i = 0; i < codOrigenes.length; i++) {
				System.out.println("ENRO AL ARRAY "+i);
				LEDiarioPorOrigenDao diarioOrigen= new LEDiarioPorOrigenDao();
				diarioOrigen=null;
				diarioOrigen= reporteLIbroDiarioService.libroDiarioSunat(codOrigenes[i], codmes, anio);
			    if(diarioOrigen!=null) {
			    	lstDiarioPorOrigenDao.add(diarioOrigen);
			    }
			}
				System.out.println("ACABO EL FOR EN EL CONTROLER");
				diarioDto.setListDiarioPorOrigenDao(lstDiarioPorOrigenDao);
	            
	            try {
	            // 	registroComprasDto = registroLibrosServiceInterface.calcularTotales(registroComprasDto, 2);
                     diarioDto = reporteLIbroDiarioService.getCalcularMontosTotalesSunat(diarioDto, 2);
				} catch (DataAccessException ed) {
					response.put("mensaje","Error en el Servidor DataAccesException en Calculando totales");
					response.put("error",ed.getMessage().concat(": ").concat(ed.getMostSpecificCause().getMessage()));		
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
	            
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  /* if (listCancelacion==null) {
			   response.put("mensaje","El cod ".concat(codigo.toString().concat(" no tiene registros ")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}*/ 
		
			return new ResponseEntity<LEDiarioDto>(diarioDto, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/libro-mayor/{anio}/{codmes}/{idperiodoanio}")
    public ResponseEntity<?> listRegistroLibroMayor( @PathVariable String anio,@PathVariable Integer codmes,@PathVariable Integer idperiodoanio) {
		Map<String, Object> response = new HashMap<>();
	 
		List<LEDMCuentaDto> listCuentas =new ArrayList<>();
		
		List<PlanContableDto> listPlanContableDto =new ArrayList<>();
		try {
			listPlanContableDto = dataServiceClient.listarPlanContableOrdenado(anio, idperiodoanio);
		} catch (DataAccessException ex) {
			response.put("mensaje","Error en el Servidor DataAccesException al tratar de traer el plan contable ");
			response.put("error",ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			    
			  if (listPlanContableDto.size()>1) {
				for (PlanContableDto p : listPlanContableDto) {
					LEDMCuentaDto cuentaDto = new LEDMCuentaDto();
					cuentaDto=  libroMayorService.generarDetalleLibroMayor(p.getCodCuenta(), codmes, anio, idperiodoanio);
					if (cuentaDto!=null) {
						listCuentas.add(cuentaDto);
					}
				}
			  } 
	     
	            
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		  /* if (listCancelacion==null) {
			   response.put("mensaje","El cod ".concat(codigo.toString().concat(" no tiene registros ")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}*/ 
		
			return new ResponseEntity<List<LEDMCuentaDto>>(listCuentas, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value="/prueba5")
    public ResponseEntity<?>  prueba5()  {
		
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("jasperva", "oli");
		 
		 return jasperSpring.doReporte(listas(),"sunat/prueba",parameters);
	}
	
	
	
	
	@GetMapping(value="/prueba")
    public String prueba() throws FileNotFoundException, JRException {

		 
		
		 File file = ResourceUtils.getFile("classpath:data/prueba.jrxml");
		 JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		 
		 
		 JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listas());
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("jasperva", "oli");
		 JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters,dataSource);
		 JasperExportManager.exportReportToPdfFile(jasperPrint,"/home/aelvis/Escritorio/prueba.pdf");
		 return "path exito en el d ";
	}
	
	@GetMapping(value="/prueba2")
    public ResponseEntity<?>  prueba2() throws FileNotFoundException, JRException {

		 byte[] bytes = null;
		
		 File file = ResourceUtils.getFile("classpath:data/prueba.jrxml");
		 JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		 
		 
		 JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listas());
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("jasperva", "oli");
		 JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters,dataSource);
		 bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		 //InputStream m = new ByteArrayInputStream(bytes);
		 System.out.println("CREO BYTES");
		// Resource resource = null;
		 String name= "jasper" ;
		 HttpHeaders cabecera = new HttpHeaders();
		 cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\""+name+".pdf\"");
		 cabecera.add(HttpHeaders.CONTENT_TYPE, "application/pdf; charset=UTF-8"); 
		 return new ResponseEntity<byte[]>(bytes,cabecera, HttpStatus.OK);
	}
	
	
	

	
	@Autowired
	ApplicationContext context;
	
	@GetMapping(value="/prueba3")
	@ResponseBody
    public void prueba3(HttpServletResponse response) throws JRException, IOException {

	
		
		  //File file = ResourceUtils.getFile("classpath:data/prueba.jrxml");
		  Resource resource = context.getResource("classpath:data/prueba.jrxml");
		// JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		  InputStream inputStream = resource.getInputStream();
		  JasperReport jasper = JasperCompileManager.compileReport(inputStream);
		  
		 
		 JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listas());
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("jasperva", "oli");
		 JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters,dataSource);
		 
		 response.setContentType("application/pdf; charset=UTF-8");
		 String name= "jasper" ;
		 response.setHeader("Content-Disposition", "inline; filename=\""+name+".pdf\"");
		 JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		 //InputStream m = new ByteArrayInputStream(bytes);
		 System.out.println("CREO RESPONSE");
		
		
		 //return new ResponseEntity<byte[]>(bytes,cabecera, HttpStatus.OK);
	}
	
	
	public List<MsgxDto> listas() {
		MsgxDto d = new MsgxDto();
		d.setDescripcion("retorn");
		d.setRetorno("re");
		d.setIsValido(true);
		List<MsgxDto> lst = new ArrayList<MsgxDto>();
		lst.add(d);
		lst.add(d);
		return lst;
	}
	
	
	}
