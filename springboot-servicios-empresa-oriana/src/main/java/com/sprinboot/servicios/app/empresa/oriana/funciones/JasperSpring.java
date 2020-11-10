package com.sprinboot.servicios.app.empresa.oriana.funciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.sprinboot.servicios.app.empresa.oriana.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.oriana.service.HojaTrabajoService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

@Component
public class JasperSpring {
   private static final Logger LOGGER =LoggerFactory.getLogger(JasperSpring.class);

/* Funcion generar reporte en archivo Blob
 * @Param (Object) Es el objecto del cual se genera el reporte, puedes ser Lista,Clase , array , set 
 * @Param (String) pathArchivoJRXML es la subruta con el nombre del archivo ejm: Sunat/registrocompras.jrxml todo esto siempre debe estar dentro de la carpeta reportes
 * @Param (Map) Parametros son los parametros que usa el reporte a generar     
 * return archivo Blob  .pdf
 * */	
  public ResponseEntity<?> doReporte(Object dsObject,String pathArchivoJrxml,Map parametros)  {
	  try {
		JRDataSource dataSource =null;
		//if(dsObject==null)
	//		dataSource = null;
		/*if(dsObject instanceof JRDataSource)
			dataSource = (JRDataSource) dsObject;
		if(dsObject instanceof Collection)
			dataSource = new JRBeanCollectionDataSource((Collection) dsObject);
		if (dsObject instanceof ResultSet)
			dataSource = new JRResultSetDataSource((ResultSet) dsObject);
		if(dsObject.getClass().isArray())
			dataSource = new JRBeanArrayDataSource((Object[]) dsObject);*/
		if(dsObject instanceof JRDataSource) {
			LOGGER.info("Es jrdatasorce");
			dataSource = (JRDataSource) dsObject;
			}else {
				if(dsObject instanceof Collection) {
					LOGGER.info("Es collection");
					dataSource = new JRBeanCollectionDataSource((Collection) dsObject);}
				else {
					if (dsObject instanceof ResultSet) {
						LOGGER.info("Es resultset");
						dataSource = new JRResultSetDataSource((ResultSet) dsObject);}
					else {
						if(dsObject.getClass().isArray()) {
							LOGGER.info("Es isarray");
							dataSource = new JRBeanArrayDataSource((Object[]) dsObject);}
						else {
							if(dsObject==null) {
								LOGGER.info("es null");
								dataSource = null;
							}else {
								LOGGER.info("NO ES NINGUNO");
							}
									
						}
					}
				}
			}
		
		
		
		 File file = ResourceUtils.getFile("classpath:reportes/"+pathArchivoJrxml+".jrxml");
		 JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		 
		 JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parametros,dataSource);

		 byte[] bytes = null;
		 bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		 
		 String name= "jasper" ;
		 HttpHeaders cabecera = new HttpHeaders();
		 cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\""+name+".pdf\"");
		 cabecera.add(HttpHeaders.CONTENT_TYPE, "application/pdf; charset=UTF-8"); 
		 return new ResponseEntity<byte[]>(bytes,cabecera, HttpStatus.OK);
		 
	}  catch (FileNotFoundException f) {
			Map<String, Object> response = new HashMap<>();
		
			LOGGER.info("ERROR FILE NOT FOUND, NO SE ENCONTRO EL ARCHIVO JRXML EN EL PROYECTO");
			response.put("mensaje","Error en el Servidor No encontro el path");
			response.put("error",f.getMessage());		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}  catch (JRException e) {
			Map<String, Object> response = new HashMap<>();
			LOGGER.info("ERROR JREXCEPTION NO SE PUDO GENERAR EL JASPER ");
			response.put("mensaje","Error en el Servidor Generar Reporte");
			response.put("error",e.getMessage());		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
  }
  
}
