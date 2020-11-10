package com.sprinboot.servicios.app.admin.base.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.servicios.app.admin.base.dto.MsgxDto;
import com.sprinboot.servicios.app.admin.base.funciones.FileService;
import com.sprinboot.servicios.app.admin.base.funciones.Funciones;
import com.sprinboot.servicios.app.admin.base.models.services.IPlanCuentaServiceImpl;
import com.sprinboot.servicios.app.admin.base.models.services.IPlesService;
import com.sprinboot.servicios.app.otros.commons.models.entity.PlanCuenta;




@RestController
public class PleController {

	@Autowired
	private FileService fileService;

	@Autowired
	private IPlesService plesService;

	@Autowired
	private Funciones funciones;

	
	@Autowired
	private IPlanCuentaServiceImpl planCuentaService;
	
	
	@RequestMapping(value="descarga-libro-diario/plancontable/{anio}/{codmes}/{idperiodoanio}", produces = "text/csv; charset=utf-8")
    public ResponseEntity<?> getLEDiario0503( @PathVariable String anio,@PathVariable Integer codmes,@PathVariable Integer idperiodoanio, HttpServletResponse response) throws IOException {
		Integer error = 0;
		Resource resource = null ;		
		resource = fileService.getClassPathFile("ple.txt", response, "text/csv");
		//InputStream input = resource.getInputStream();
		File file = resource.getFile();
	    BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(file));
		try {
			
				List<PlanCuenta> listPlanCuentas= new ArrayList<PlanCuenta>();
				listPlanCuentas = planCuentaService.listarPlanContablePorEmpresa(anio,idperiodoanio);
				
				
			    if(listPlanCuentas.size()>0) {
			    	listPlanCuentas = funciones.getOrdenarPlanContable(listPlanCuentas);
			        for (PlanCuenta plan :listPlanCuentas) {
			        	List<MsgxDto> lisMsgx = plesService.generatorFilaLEDiarioPlanContable(plan, anio, codmes);
				    	if (lisMsgx.size()>1) {
							//error fijo
	                        if (error==0) {
	                            bw = new BufferedWriter(new FileWriter(file));
							}			    		 
			  				 bw.write("********************************** Error al generar fila en el LE libro Diario 0503 (ID="+plan.getId() +" )****************" );
			  				 bw.newLine();
				    		    for (MsgxDto msg : lisMsgx) {
				     				 bw.write(msg.getDescripcion());
				     				 bw.newLine();
								}
   							    error=1;
   							  // break;
						}else {
							if (!lisMsgx.get(0).getIsValido()) {
								 if (error==0) {
			                            bw = new BufferedWriter(new FileWriter(file));
									}	
				  				 bw.write("********************************** Error al generar filaen el LE libro Diario 0503 (ID="+plan.getId() +" )****************" );
				  				 bw.newLine();
					    		    for (MsgxDto msg : lisMsgx) {
					    		   	 bw.write(msg.getDescripcion());
					    		   	 bw.newLine();
									}
	   							    error=1;
	   							//   break;
							}else {
								if (error==0) {
									//correcto
									bw.write(lisMsgx.get(0).getRetorno());
	   								bw.newLine();
								}
								
							}
						}
					}
			    }else {
					//vacio
				}
			    
			
			if (error==1) {
  				// bw.write("Error al generar fila (Columna "+ colError+ " )  en PLE del asiento = "+numAsientoError+" del libro Diario" );
  			     bw.close();
  			     //ERROR 422
  				return new ResponseEntity<Resource>(resource, HttpStatus.UNPROCESSABLE_ENTITY);

  		    	//return new ResponseEntity<Map<String, Object>>(responses, HttpStatus.INTERNAL_SERVER_ERROR);
  			}else {
  		        bw.close();
  				return new ResponseEntity<Resource>(resource, HttpStatus.OK);

  			}
	            
			} catch (DataAccessException e) {
				bw.write("Error en el Backend al Generar ple del libro diario 0503  " +e.getMessage() );
  			     bw.close();
  			     // ERROR 406
  				return new ResponseEntity<Resource>(resource, HttpStatus.NOT_ACCEPTABLE);

			}
		
	}
	
	
	
	
	
}
