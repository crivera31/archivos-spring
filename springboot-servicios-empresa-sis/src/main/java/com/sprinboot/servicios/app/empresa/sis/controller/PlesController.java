package com.sprinboot.servicios.app.empresa.sis.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.supercsv.prefs.CsvPreference;

import com.sprinboot.servicios.app.empresa.sis.dto.CancelacionDocDto;
import com.sprinboot.servicios.app.empresa.sis.dto.LEDiarioDto;
import com.sprinboot.servicios.app.empresa.sis.dto.LEDiarioPorOrigenDao;
import com.sprinboot.servicios.app.empresa.sis.dto.LEDiarioVoucherDto;
import com.sprinboot.servicios.app.empresa.sis.dto.LEDiarioVucherDetDto;
import com.sprinboot.servicios.app.empresa.sis.dto.MsgxDto;
import com.sprinboot.servicios.app.empresa.sis.dto.RegistroComprasDetDto;
import com.sprinboot.servicios.app.empresa.sis.dto.RegistroComprasDto;
import com.sprinboot.servicios.app.empresa.sis.dto.RegistroComprasSubDetDto;
import com.sprinboot.servicios.app.empresa.sis.dto.ValidarLibroSPDao;
import com.sprinboot.servicios.app.empresa.sis.funciones.FileService;
import com.sprinboot.servicios.app.empresa.sis.service.AsientoServiceInterface;
import com.sprinboot.servicios.app.empresa.sis.service.IPlesService;
import com.sprinboot.servicios.app.empresa.sis.service.RegistroLibrosServiceInterface;
import com.sprinboot.servicios.app.empresa.sis.service.ReporteLibroDiarioServiceInterface;
import com.sprinboot.servicios.empresa.commons.entity.TipoAllPersona;

@RestController
public class PlesController {

	@Autowired
	FileService fileService;

	@Autowired
	IPlesService plesService;
	
	@Autowired
	RegistroLibrosServiceInterface registroLibrosServiceInterface;
	
	@Autowired
	ReporteLibroDiarioServiceInterface reporteLIbroDiarioService ;
	
	@Autowired
	AsientoServiceInterface asientoService;

	@RequestMapping(value= "/ple",method = RequestMethod.GET)
	public ResponseEntity<?>  descargarples() {
		return new ResponseEntity<String>("Exito", HttpStatus.OK);
	}
	
	//aplicando libreria supercsv
	@RequestMapping(value = "downloadCSV",method = RequestMethod.GET)
    public ResponseEntity<?> downloadCSV(HttpServletResponse response)  {
 
		Map<String, Object> responses = new HashMap<>();
		 ICsvBeanWriter csvWriter ;
		try {

			response.setContentType("text/csv; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=plea.csv");
			response.setHeader("filename", "plea.csv");
	  
	     // uses the Super CSV API to generate CSV data from the model data
	         csvWriter = new CsvBeanWriter(response.getWriter(),
	        CsvPreference.STANDARD_PREFERENCE);

	        String[] header = { "Title", "Description", "Author", "Publisher",
	        "isbn", "PublishedDate", "Price" };

	        csvWriter.writeHeader(header);

	 
	       /* for (Book aBook : listBooks) {
	            csvWriter.write(aBook, header);
	        }*/
	       // csvWriter.write("asas", header);
	        
	        csvWriter.close();
	        
	         
		} catch (Exception e) {
			responses.put("error",e.getMessage());		
			return new ResponseEntity<Map<String, Object>>(responses, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ICsvBeanWriter>(csvWriter, HttpStatus.OK);

		
    }
	

	
	
	//codigo correcto
/*	@RequestMapping(value = "descargaPle/{filename}", produces = "text/csv; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public Resource getFileFromClasspath(@PathVariable String filename, HttpServletResponse response) {

		return fileService.getClassPathFile(filename, response, "text/csv");
	}
	*/
	
	//return codigo testeado 
	/*@RequestMapping(value = "descargaPle/{filename}", produces = "text/csv; charset=utf-8")
	public ResponseEntity<?> getFileFromClasspath(@PathVariable String filename, HttpServletResponse response)throws IOException  {
		 
		Map<String, Object> responses = new HashMap<>();
		 Resource resource = null ;
		try {
			resource = fileService.getClassPathFile(filename, response, "text/csv");
			//InputStream input = resource.getInputStream();
			File file = resource.getFile();
		    BufferedWriter bw = null;
            bw = new BufferedWriter(new FileWriter(file));
             bw.write("nooooooooooooo");
             bw.newLine();
            bw.write("segunda linea wu");
            bw.close();


		} catch (DataAccessException e) {
			responses.put("mensaje", "Error al actualizar en el servidor");
			responses.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responses, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//responses.put("mensaje", "Libro Electronico generado correctamente");
		//responses.put("resource", resource);
		//return new ResponseEntity<Map<String, Object>>(responses, HttpStatus.OK);
		return new ResponseEntity<Resource>(resource, HttpStatus.OK);

		
    }*/
	
	
	@RequestMapping(value = "descargaPle/{idperiodo}/{anio}/{codmes}/{codlibro}/{codCtaParametro}/{codClasificacion}", produces = "text/csv; charset=utf-8")
	public ResponseEntity<?> getFileFromClasspath( @PathVariable Integer idperiodo, @PathVariable String anio,@PathVariable Integer codmes,@PathVariable String codlibro ,@PathVariable String codCtaParametro ,@PathVariable String codClasificacion ,HttpServletResponse response)throws IOException  {
		
		System.out.println("COD CLASI ="+codClasificacion);
		
		String codDocumentos[] = {"01","02","03","04","05","06","07","08","10","11","12","13","14","15","16","17","18","19","21","22","23","24","25","26","27","28","29","30","32","34","35","36","37","42","43","44","45","46","48","49","50","51","52","53","54","55","56","87","88","89","96"};	
		RegistroComprasDto registroComprasDto= new RegistroComprasDto();   
		List<RegistroComprasDetDto> listRegistroComprasDetDto=new ArrayList<RegistroComprasDetDto>();
		Map<String, Object> responses = new HashMap<>();
		 Resource resource = null ;
	
			
			Integer error = 0;
			String idError = "";
			String numAsientoError="";
			String colError ="";
			resource = fileService.getClassPathFile("ple.txt", response, "text/csv");
			//InputStream input = resource.getInputStream();
			File file = resource.getFile();
		    BufferedWriter bw = null;
            bw = new BufferedWriter(new FileWriter(file));
            
            List<ValidarLibroSPDao> validacionList = new ArrayList<>();
 		   try {
 			   validacionList = asientoService.getValidacionSP();
			   if(validacionList.size()> 0) {
				   bw.write("Existen Repeticion de libros , se recomienda verificar los asientos , si es necesario borrar los datos de cada asiento y volver a ingresar el voucher, los asientos con repeticion de vouchers son: ");
				   //bw.newLine();
				   for (ValidarLibroSPDao v : validacionList) {
					   bw.write("**** ASIENTO = " + v.getNum_asiento()+ " | ORIGEN = "+v.getCod_origen()+" | MES = "+v.getCod_mes() + " | AÑO = "+v.getAnio()+ " ****");
					   //bw.newLine();
				   }
				   bw.close();
				   return new ResponseEntity<Resource>(resource, HttpStatus.INTERNAL_SERVER_ERROR);
			   }
			} catch (Exception e) {
				 bw.write("Error en el servidor al llamar SP VALIDAR LIBROS, revisar conexion a internet ");
				 bw.close();
    			     //ERROR 500
    			  return new ResponseEntity<Resource>(resource, HttpStatus.INTERNAL_SERVER_ERROR);
			}
            
            
           try {
       	    for (int i = 0; i < codDocumentos.length; i++) {
       				System.out.println("ENRO AL ARRAY "+i);
       				//String cad ="sds|545454|sdsd|sdsdsd|dssdsd|4545|545454|sdsd|sdsdsd|dssdsd|4545|pepe|rola|4545|545454|sdsd|sdsdsd|dssdsd|4545|pepe|rola|4545|545454|sdsd|sdsdsd|dssdsd|4545|pepe|rola4545|545454|sdsd|sdsdsd|dssdsd|4545|pepe|ASECINO";
       				
       				RegistroComprasDetDto rs= new RegistroComprasDetDto();
       				rs=null;
       				rs= registroLibrosServiceInterface.listarRegistroDeCompras(codDocumentos[i], idperiodo, codmes, anio, 2,codlibro,codCtaParametro);
       			    
       				if(rs!=null) {
       			    	for (RegistroComprasSubDetDto subdet : rs.getListcompra()) {
       			    		//System.out.println("SUBDET = "+subdet.getFechaEmision());
       			    		
       						   ///aqui
       			    		    String col = plesService.generatorFilaPle(rs, codmes, anio, subdet, codClasificacion);
       			    		    System.out.println("ERROR  == "+col);
       			    		    if (col.length()>2) {
       			    		    	bw.write(col);
       								bw.newLine();
       			    		    	// bw.write(col);
       			 			         //bw.newLine();

       							 }else {
       								 System.out.println("ERRORRR ES MENOR QUE DOS");
       							    numAsientoError = ""+subdet.getNumAsiento();
       							    colError =  ""+col;
       							    error=1;
       							    idError = ""+subdet.getId();
       							   break;
       							 }
       					   }	
       			    }
       			    if (error==1) {
       					break;
       				}
       			}

       			if (error==1) {
       				 bw.write("Error al generar fila (Columna "+ colError+ " )  en PLE del asiento = "+numAsientoError+" del libro "+codlibro );
       			     bw.close();
       			     //ERROR 500
       				return new ResponseEntity<Resource>(resource, HttpStatus.INTERNAL_SERVER_ERROR);

       		    	//return new ResponseEntity<Map<String, Object>>(responses, HttpStatus.INTERNAL_SERVER_ERROR);
       			}else {
       				System.out.println("LLEGO AL FIN OK ");
       		        bw.close();
       				return new ResponseEntity<Resource>(resource, HttpStatus.OK);

       			}
			} catch (Exception e) {
				 bw.write("Error en el Backend al Generar ple del libro  "+codlibro );
   			     bw.close();
   			     // ERROR 406
   				return new ResponseEntity<Resource>(resource, HttpStatus.NOT_ACCEPTABLE);

			} 
	
			
          // // bw.write("nooooooooooooo");
            //bw.newLine();
          // bw.write("segunda linea wu");
          
		
		//responses.put("mensaje", "Libro Electronico generado correctamente");
		//responses.put("resource", resource);
		//return new ResponseEntity<Map<String, Object>>(responses, HttpStatus.OK);

		
    }
	
	
	

	@RequestMapping(value = "descarga-leventa/{idperiodo}/{anio}/{codmes}/{codlibro}/{codCtaParametro}", produces = "text/csv; charset=utf-8")
	public ResponseEntity<?> getFileFromClasspathVentas( @PathVariable Integer idperiodo, @PathVariable String anio,@PathVariable Integer codmes,@PathVariable String codlibro ,@PathVariable String codCtaParametro ,HttpServletResponse responseVenta)throws IOException  {
		String codDocumentos[] = {"01","03","04","05","06","07","08","11","12","13","14","15","16","17","18","19","21","23","24","25","26","27","28","29","30","32","34","35","36","37","42","43","44","45","48","49","55","56","87","88"};	
		RegistroComprasDto registroComprasDto= new RegistroComprasDto();   
		List<RegistroComprasDetDto> listRegistroComprasDetDto=new ArrayList<RegistroComprasDetDto>();
		Map<String, Object> responses = new HashMap<>();
		 Resource resource = null ;
	
			
			Integer error = 0;
			String idError = "";
			String numAsientoError="";
			String colError ="";
			resource = fileService.getClassPathFile("ple2.txt", responseVenta, "text/csv");
			//InputStream input = resource.getInputStream();
			File file = resource.getFile();
		    BufferedWriter bw = null;
            bw = new BufferedWriter(new FileWriter(file));
            
           try {
       	    for (int i = 0; i < codDocumentos.length; i++) {
       				System.out.println("ENRO AL ARRAY "+i);
       				//String cad ="sds|545454|sdsd|sdsdsd|dssdsd|4545|545454|sdsd|sdsdsd|dssdsd|4545|pepe|rola|4545|545454|sdsd|sdsdsd|dssdsd|4545|pepe|rola|4545|545454|sdsd|sdsdsd|dssdsd|4545|pepe|rola4545|545454|sdsd|sdsdsd|dssdsd|4545|pepe|ASECINO";
       				
       				RegistroComprasDetDto rs= new RegistroComprasDetDto();
       				rs=null;
       				rs= registroLibrosServiceInterface.listarRegistroDeCompras(codDocumentos[i], idperiodo, codmes, anio, 2,codlibro,codCtaParametro);
       			    
       				if(rs!=null) {
       			    	for (RegistroComprasSubDetDto subdet : rs.getListcompra()) {
       			    		//System.out.println("SUBDET = "+subdet.getFechaEmision());
       			    		
       						   ///aqui
       			    		    String col = plesService.generatorFilaLEVentas(rs, codmes, anio, subdet);
       			    		    System.out.println("ERROR  == "+col);
       			    		    if (col.length()>2) {
       			    		    	bw.write(col);
       								bw.newLine();
       			    		    	// bw.write(col);
       			 			         //bw.newLine();

       							 }else {
       								 System.out.println("ERRORRR ES MENOR QUE DOS");
       							    numAsientoError = ""+subdet.getNumAsiento();
       							    colError =  ""+col;
       							    error=1;
       							    idError = ""+subdet.getId();
       							   break;
       							 }
       					   }	
       			    }
       			    if (error==1) {
       					break;
       				}
       			}

       			if (error==1) {
       				 bw.write("Error al generar fila (Columna "+ colError+ " )  en PLE del asiento = "+numAsientoError+" del libro "+codlibro );
       			     bw.close();
       			     //ERROR 500
       				return new ResponseEntity<Resource>(resource, HttpStatus.INTERNAL_SERVER_ERROR);

       		    	//return new ResponseEntity<Map<String, Object>>(responses, HttpStatus.INTERNAL_SERVER_ERROR);
       			}else {
       		        bw.close();
       				return new ResponseEntity<Resource>(resource, HttpStatus.OK);

       			}
			} catch (Exception e) {
				 bw.write("Error en el Backend al Generar ple del libro  "+codlibro );
   			     bw.close();
   			     // ERROR 406
   				return new ResponseEntity<Resource>(resource, HttpStatus.NOT_ACCEPTABLE);

			} 
	
			
          // // bw.write("nooooooooooooo");
            //bw.newLine();
          // bw.write("segunda linea wu");
          
		
		//responses.put("mensaje", "Libro Electronico generado correctamente");
		//responses.put("resource", resource);
		//return new ResponseEntity<Map<String, Object>>(responses, HttpStatus.OK);

		
    }
	
	@RequestMapping(value="descarga-libro-diario/{anio}/{codmes}/{codCtaParametroCompras}/{codCtaParametroVentas}", produces = "text/csv; charset=utf-8")
    public ResponseEntity<?> getLEDiario0501( @PathVariable String anio,@PathVariable Integer codmes,@PathVariable String codCtaParametroCompras ,@PathVariable String codCtaParametroVentas, HttpServletResponse response) throws IOException {
		Integer error = 0;
		String idError = "";
		String numAsientoError="";
		String colError ="";
		String desError ="";
		Resource resource = null ;
		Map<String, Object> responses = new HashMap<>();
		String codOrigenes[] = {"01","02","03","04","05"};	
		LEDiarioDto diarioDto= new LEDiarioDto();   
		List<LEDiarioPorOrigenDao> lstDiarioPorOrigenDao =new ArrayList<LEDiarioPorOrigenDao>();
		
		
		resource = fileService.getClassPathFile("ple.txt", response, "text/csv");
		//InputStream input = resource.getInputStream();
		File file = resource.getFile();
	    BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(file));
		try {
			for (int i = 0; i < codOrigenes.length; i++) {
				System.out.println("ENRO AL ARRAY "+i);
				LEDiarioPorOrigenDao diarioOrigen= new LEDiarioPorOrigenDao();
				diarioOrigen=null;
				diarioOrigen= reporteLIbroDiarioService.libroDiarioSunat(codOrigenes[i], codmes, anio);
			    if(diarioOrigen!=null) {
			        for (LEDiarioVoucherDto diarioVoucher : diarioOrigen.getListLeDiarioVoucherDto()) {
					    for (LEDiarioVucherDetDto diarioVoucherDet : diarioVoucher.getLstLeDiarioVucherDetDto()) {
					    	
					    	List<MsgxDto> lisMsgx = plesService.generatorFilaLEDiario(diarioVoucherDet, codmes, anio, diarioVoucher, diarioOrigen, codCtaParametroCompras, codCtaParametroVentas);
					    	if (lisMsgx.size()>1) {
								//error fijo
					             bw = new BufferedWriter(new FileWriter(file));
				  				 bw.write("********************************** Error al generar fila en PLE del asiento = "+diarioVoucher.getNumAsiento()+" del origen "+ diarioOrigen.getCodOrigen() + "  en el  libro Diario****************" );
				  				 bw.newLine();
					    		    for (MsgxDto msg : lisMsgx) {
					     				 bw.write(msg.getDescripcion());
					     				 bw.newLine();
									}
	   							    error=1;
	   							   break;
							}else {
								if (!lisMsgx.get(0).getIsValido()) {
							         bw = new BufferedWriter(new FileWriter(file));
									 bw.write("********************************** Error al generar fila en PLE del asiento = "+diarioVoucher.getNumAsiento()+" del origen "+ diarioOrigen.getCodOrigen() + "  en el  libro Diario****************" );
					  				 bw.newLine();
						    		    for (MsgxDto msg : lisMsgx) {
						    		   	 bw.write(msg.getDescripcion());
						    		   	 bw.newLine();
										}
		   							    error=1;
		   							   break;
								}else {
									//correcto
									bw.write(lisMsgx.get(0).getRetorno());
	   								bw.newLine();
								}
							}
						}
					    if (error==1) {
		   					break;
		   				}
					}
			    }
			    if (error==1) {
   					break;
   				}
			}
			if (error==1) {
  				// bw.write("Error al generar fila (Columna "+ colError+ " )  en PLE del asiento = "+numAsientoError+" del libro Diario" );
  			     bw.close();
  			     //ERROR 204
  				return new ResponseEntity<Resource>(resource, HttpStatus.INTERNAL_SERVER_ERROR);

  		    	//return new ResponseEntity<Map<String, Object>>(responses, HttpStatus.INTERNAL_SERVER_ERROR);
  			}else {
  		        bw.close();
  				return new ResponseEntity<Resource>(resource, HttpStatus.OK);

  			}
	            
			} catch (DataAccessException e) {
				bw.write("Error en el Backend al Generar ple del libro  " +e.getMessage() );
  			     bw.close();
  			     // ERROR 406
  				return new ResponseEntity<Resource>(resource, HttpStatus.NOT_ACCEPTABLE);

			}
		
	}
	
	
	// PROBANDO SP ---------------------
	
	
	@RequestMapping(value="pruebasp")
    public ResponseEntity<?> listRegistroDsddoc() {
		Map<String, Object> response = new HashMap<>();
		//List<CancelacionDocDto> listCancelacion =null;
		List<ValidarLibroSPDao> validacionList = null;
		   try {
				//listCancelacion = registroDocumentosServiceInterface.listarByPersona(codigo, plan, anio);
			   validacionList = asientoService.getValidacionSP();
	            	
			} catch (DataAccessException e) {
				response.put("mensaje","Error en el Servidor DataAccesException");
				response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		   response.put("mensaje","EXITO");
		   response.put("data", validacionList);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
}
