package com.springboot.servicios.app.empresa.sis.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.inject.matcher.Matchers;
import com.sprinboot.servicios.app.empresa.villasol.controller.ReportesController;
import com.sprinboot.servicios.app.empresa.villasol.dao.RegistroLibrosDao;
import com.sprinboot.servicios.app.empresa.villasol.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.villasol.jsons.ClaseResponse;
import com.sprinboot.servicios.app.empresa.villasol.jsons.RegistrosCabRest;
import com.sprinboot.servicios.app.empresa.villasol.jsons.RegistrosDetRest;
import com.sprinboot.servicios.app.empresa.villasol.service.RegistrosSistemaService;
import com.sprinboot.servicios.app.empresa.villasol.service.RegistrosSistemaServiceInterface;
import com.sprinboot.servicios.app.empresa.villasol.solid.OpcionReporte;
import com.sprinboot.servicios.app.empresa.villasol.solid.SolesMensualFecha;

public class ReportesControllerTest {

	private static final String STATUS_CODE_OK = "200 OK";
    private static final Integer CODMES = 1;
    private static final String ANIO ="2020";
    private static final String COD_LIBRO = "01";
    private static final Integer ID_PERIODO = 16;
    private static final String COD_PLAN_PARAMETRO = "40111";
	public static final RegistrosCabRest CAB =  new RegistrosCabRest();
	public static final List<RegistrosDetRest> LISTDET = new ArrayList<RegistrosDetRest>();
	public static final List<OpcionReporte> LISTOPCION = new ArrayList<OpcionReporte>();
	public static final Logger LOGGER = LoggerFactory.getLogger(ReportesControllerTest.class);
	
	@Mock
	RegistrosSistemaServiceInterface registrosSistemaServiceInterface;
	
	@Mock
	RegistroLibrosDao registroLibroDao;
	
	
	@InjectMocks
	ReportesController reportesController;
	
	 @Before
	 public void init() throws ClaseException {
		 MockitoAnnotations.initMocks(this);
		 CAB.setAdqNoGrav(new BigDecimal("0.00"));
		 CAB.setAdqSinDer(new BigDecimal("0.00"));
		 CAB.setBaseImpAdq(new BigDecimal("0.00"));
		 CAB.setBaseImpOpe(new BigDecimal("0.00"));
		 CAB.setExonerado(new BigDecimal("0.00"));
		 CAB.setIgvBaseAdqSinDer(new BigDecimal("0.00"));
		 CAB.setIgvBaseImpAdq(new BigDecimal("0.00"));
		 CAB.setIgvBaseImpOpe(new BigDecimal("0.00"));
		 CAB.setInafecto(new BigDecimal("0.00"));
		 CAB.setIsc(new BigDecimal("0.00"));
		 CAB.setOtrosTributos(new BigDecimal("0.00"));
		 CAB.setValorExportacion(new BigDecimal("0.00"));
		 CAB.setTotal(new BigDecimal("0.00"));
		 CAB.setLstRegistrosDetRest(LISTDET);
		 Mockito.when(registrosSistemaServiceInterface.getDao()).thenReturn(registroLibroDao);
		 LISTOPCION.add(new SolesMensualFecha(registroLibroDao,registrosSistemaServiceInterface)) ;
	//	 ClaseResponse<RegistrosCabRest> responseEntity = new ClaseResponse<RegistrosCabRest>(CAB, HttpStatus.OK);
		 Mockito.when(registrosSistemaServiceInterface.reporteRegistros(LISTOPCION, CODMES, ANIO, ID_PERIODO, COD_LIBRO, COD_PLAN_PARAMETRO)).thenReturn(CAB);
		// Mockito.when(reportesController.listRegistroDoc(CODMES, ANIO,ID_PERIODO,COD_PLAN_PARAMETRO,COD_LIBRO)).thenReturn(responseEntity);
	 }

	 	@Test
	 	public void listRegistroDocTest() throws ClaseException {
	 		final ResponseEntity<RegistrosCabRest> response = (ResponseEntity<RegistrosCabRest>) reportesController.listRegistroDoc(CODMES, ANIO,ID_PERIODO,COD_PLAN_PARAMETRO,COD_LIBRO);
	 	//	System.out.println("HTTP = "+String.valueOf(response.getCode()));
	 	//	assertEquals(String.valueOf(response.getStatusCode()) , STATUS_CODE_OK);
	 	//	LOGGER.info("RESt = "+response.getData());
	 		LOGGER.info("RESt CAB = "+CAB.getLstRegistrosDetRest());
		// assertEquals(response.getCode() , HttpStatus.OK);
		// assertEquals(response.getData() , CAB);

	 	}
}
