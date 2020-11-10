package com.sprinboot.servicios.app.empresa.constructores.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.sprinboot.servicios.app.empresa.constructores.client.DataServiceClient;
import com.sprinboot.servicios.app.empresa.constructores.dto.PersonaDto;
import com.sprinboot.servicios.app.empresa.constructores.funciones.Funciones;
import com.sprinboot.servicios.app.empresa.constructores.funciones.JasperSpring;
import com.sprinboot.servicios.app.empresa.constructores.jsons.ComprobanteRest;
import com.sprinboot.servicios.app.empresa.constructores.service.ComprobanteDetServiceInterface;
import com.sprinboot.servicios.app.empresa.constructores.service.ComprobanteServiceInterface;
import com.sprinboot.servicios.empresa.commons.entity.Asiento;
import com.sprinboot.servicios.empresa.commons.entity.Comprobante;

@RestController
@RequestMapping("comprobante")
public class ComprobanteController {
	   private static final Logger LOGGER =LoggerFactory.getLogger(JasperSpring.class);
	   
	@Autowired
	ComprobanteServiceInterface comprobanteService;
	
	@Autowired
	ComprobanteDetServiceInterface comprobanteDetService;
	
	@Autowired
    DataServiceClient dataServiceClient; 
	
	@Autowired
	JasperSpring jasperSpring;
	
	@Autowired
	Funciones funciones;
	
	@GetMapping(value="/sin-registrar/{anio}")
    public ResponseEntity<?> getComprobantesSinregistrar(@PathVariable String anio) {
		 List<Asiento> lstAsiento = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();
		try {
			lstAsiento = comprobanteService.comprobantesSinRegistrar(anio);
		        
		} catch (DataAccessException e) {
		     	
			response.put("mensaje","Erroren el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("lstAsiento",lstAsiento);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		

	}
	
	
	@GetMapping(value="/listar-comprobantes/{anio}")
    public ResponseEntity<?> getListarComprobantes(@PathVariable String anio) {
		 List<Comprobante> lstComprobante = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();
		try {
			lstComprobante = comprobanteService.listarComprobante(anio);
		        
		} catch (DataAccessException e) {
		     	
			response.put("mensaje","Erroren el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("lstComprobante",lstComprobante);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/ultima-fecha/{numcomprobante}/{anio}")
    public ResponseEntity<?> getListarComprobantes(@PathVariable Integer numcomprobante,@PathVariable String anio) {
		 List<Comprobante> lstComprobante = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();
		try {
			lstComprobante = comprobanteService.ultimaFecha(numcomprobante, anio);
		        
		} catch (DataAccessException e) {
		     	
			response.put("mensaje","Erroren el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (lstComprobante.size()>0) {
			response.put("ultimafecha",lstComprobante.get(lstComprobante.size()-1));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}else {
			response.put("ultimafecha",lstComprobante.get(lstComprobante.size()-1));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		
	}
	
	
	
	
	@GetMapping(value="/lista-asientos-por-comprobante/{numcomprobante}/{anio}")
	public ResponseEntity<?> listarAsientosPorNumComprobante(@PathVariable("numcomprobante") Integer numcomprobante,@PathVariable("anio") String anio){
		Map<String, Object> response = new HashMap<>();
		List<Asiento> lstAsientoEntity  = new ArrayList<>();
		BigDecimal suma = new BigDecimal("0.00");
		try {
			lstAsientoEntity = comprobanteService.listarAsientosPorNumComprobante(numcomprobante, anio);
			suma = comprobanteService.sumaAsientos(lstAsientoEntity, "101", "104");
		} catch (DataAccessException e) {
			response.put("mensaje","Erroren el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
             return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		response.put("lista",lstAsientoEntity );
		response.put("sumaTotal",suma );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value="/lista-asientos-por-comprobante/editar/{numcomprobante}/{anio}/{comprobanteid}")
	public ResponseEntity<?> listarAsientosPorNumComprobanteEditar(@PathVariable("numcomprobante") Integer numcomprobante,@PathVariable("anio") String anio,@PathVariable("comprobanteid") Integer comprobanteid){
		Map<String, Object> response = new HashMap<>();
		List<Asiento> lstAsientoEntity  = new ArrayList<>();
		BigDecimal suma = new BigDecimal("0.00");
		try {
			
			comprobanteService.reeditarComprobantesDet(numcomprobante,anio, comprobanteid);
			lstAsientoEntity = comprobanteService.listarAsientosPorNumComprobante(numcomprobante, anio);
			suma = comprobanteService.sumaAsientos(lstAsientoEntity, "101", "104");
		} catch (DataAccessException e) {
			response.put("mensaje","Erroren el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
             return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		response.put("lista",lstAsientoEntity );
		response.put("sumaTotal",suma );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value="/guardar")
    public ResponseEntity<?> agregarAsiento(@RequestBody Comprobante request) throws Exception {
		Comprobante comprobante= new Comprobante();		
		Map<String, Object> response = new HashMap<>();
		try {
			request.setTipo_comprobante("pago");
			//request.setSon(""); //incluir funcion de diego
			comprobante = comprobanteService.saveGuardarComprobante(request);
			 
		} catch (DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor, al recomprobar si asiento ya fue creado");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//response.put("comprobante",comprobante );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value="/descargar-pdf/{numcomprobante}/{anio}")
    public ResponseEntity<?> registroComprasPdf(@PathVariable Integer numcomprobante, @PathVariable String anio) {
		Map<String, Object> response = new HashMap<>();
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 Comprobante comprobante = new Comprobante();
		 LOGGER.info("ENTRE ");
		 comprobante = comprobanteService.getComprobante(anio.trim(), numcomprobante);
		 PersonaDto per = new PersonaDto();
		
		 
		 LOGGER.info("AAAAAAAAAA "+comprobante.getSerieNumero());
		 if (comprobante==null) {
			 response.put("mensaje","Fallo de conexion en el servidor");
	         return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		 per = comprobanteService.getDatosPersonales(comprobante.getRuc().trim());
		 
		 comprobante.setLstComprobanteDet(null);
		 parameters.put("constructora", "ORIANA CONTRATISTAS GENERALES S.R.L.");
		 parameters.put("direccionEmpresa", "CAL BRASIL NRO. 144 DPTO 3");
		 parameters.put("ejercicio", "EJERCICIO 2020");
		 parameters.put("rucEmpresa", "RUC N° 20445667618");
		 parameters.put("numComprobante", numcomprobante+ " - 2020");
		 parameters.put("cuentaBanco", comprobante.getCuentaBanco());
		 parameters.put("descuento", comprobante.getDescuento());
		 parameters.put("detalle", comprobante.getDetalle());
		 parameters.put("direccion", per.getDireccion());
		 parameters.put("documento", comprobante.getDocumento());
		 parameters.put("fechaComprobante", comprobante.getFechaComprobante());
		 parameters.put("fechaDoc", comprobante.getFechaDoc());
		 parameters.put("formaPago", comprobante.getFormaPago());
		 parameters.put("netoPagar", funciones.sumarRedondear( comprobante.getNetoPagar(), null, 2));
		 parameters.put("numCheque", comprobante.getNumCheque());
		 parameters.put("razonSocial", per.getRazonSocial());
		 parameters.put("ruc", comprobante.getRuc());
		 parameters.put("serieNumero", comprobante.getSerieNumero());
		 comprobante.setNetoPagar(comprobante.getNetoPagar()!=null? comprobante.getNetoPagar() : new BigDecimal("0.00"));
		 parameters.put("son",  funciones.readNumber( funciones.sumarRedondear( comprobante.getNetoPagar(), null, 2).toString() , ".", "" ).toUpperCase() + "/100 SOLES" );
		 parameters.put("total", funciones.sumarRedondear(comprobante.getTotal(), null, 2));
		 parameters.put("usoGasto", dataServiceClient.findCentroCostoById(comprobante.getUnidadNegocioId()));
		 
		 return jasperSpring.doReporte(new Object[] {comprobante},"comprobantes/comprobantePago",parameters);
			//return new ResponseEntity<RegistroComprasDto>(registroComprasDto, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/obtener/{numcomprobante}/{anio}")
	public ResponseEntity<?> getObtener(@PathVariable("numcomprobante") Integer numcomprobante,@PathVariable("anio") String anio){
		Map<String, Object> response = new HashMap<>();
		 ComprobanteRest comprobante = new ComprobanteRest();
		 
		try {
		    comprobante = comprobanteService.getComprobanteRest(anio, numcomprobante);	
		} catch (DataAccessException e) {
			response.put("mensaje","Error en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
             return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	
		return new ResponseEntity<ComprobanteRest>(comprobante, HttpStatus.OK);
	}

	
	@PostMapping(value="/editar")
    public ResponseEntity<?> editarComprobante(@RequestBody ComprobanteRest request) throws Exception {
		Comprobante comprobante= new Comprobante();		
		Map<String, Object> response = new HashMap<>();
		try {
			//request.setSon(""); //incluir funcion de diego
			comprobante = comprobanteService.editarComprobante(request);
			comprobante.setLstComprobanteDet(null);
			 
		} catch (DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//response.put("comprobante",comprobante );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	

}
