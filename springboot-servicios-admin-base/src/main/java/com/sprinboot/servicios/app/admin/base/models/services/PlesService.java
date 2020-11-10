package com.sprinboot.servicios.app.admin.base.models.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.admin.base.dto.MsgxDto;
import com.sprinboot.servicios.app.admin.base.funciones.Funciones;
import com.sprinboot.servicios.app.otros.commons.models.entity.PlanCuenta;


@Service
public class PlesService implements IPlesService {

	@Autowired
	Funciones funciones;
	
	//@Autowired
	//RegistroLibrosDao registroLibrosDao;

	



	@Override
	public List<MsgxDto>  generatorFilaLEDiarioPlanContable(PlanCuenta plan,String anioFront, Integer mes) {
		String anio = funciones.getSinEspacios(anioFront); 
		
		int error =0 ;
		List<String> fila = new ArrayList<String>();
		List<MsgxDto> listMsgx = new ArrayList<MsgxDto>();
		MsgxDto msgx = new MsgxDto();
		 //Columna 1 Periodo , Obligatorio
			try {
				   fila.add(funciones.getPeriodoFomatoAAAAMMDD(mes,anio));  
					
				} catch (Exception e) {
					error = 1;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error + " al generar el periodo (MES,AÑO) - LE DIARIO 0503");
					listMsgx.add(msgx);
					
				}
			   
		      //columna 2 CUENTA 
			   if (plan.getCodCuenta()!=null) {
				  
				   if (plan.getCodCuenta().trim().compareTo("")!=0) {
					     if(plan.getCodCuenta().trim().length()<25) {
					    	 try {
					    		 fila.add(funciones.getCuentaParaPle(plan.getCodCuenta().trim()));
							} catch (Exception e) {
								 error = 2;
									msgx.setRetorno(""+error);
									msgx.setIsValido(false);
									msgx.setDescripcion("Error en el campo "+error +", "+ plan.getNombreCuenta() +   " su longitud es mayor a 24, revisar el plan contable - LE DIARIO 0503");
									listMsgx.add(msgx);
							}
						    
						   }else {
							//error cadena es longitud mayor que 24 maximo permitido por sunat
							   error = 2;
								msgx.setRetorno(""+error);
								msgx.setIsValido(false);
								msgx.setDescripcion("Error en el campo "+error +", "+ plan.getNombreCuenta() +   " su longitud es mayor a 24, revisar el plan contable - LE DIARIO 0503");
								listMsgx.add(msgx);
							   
						  }
				   }else {
					//error es vacio
						error = 2;
						msgx.setRetorno(""+error);
						msgx.setIsValido(false);
						msgx.setDescripcion("Error en el campo "+error +", "+ plan.getNombreCuenta() +   " su codigo esta en blanco, revisar el plan contable - LE DIARIO 0503");
						listMsgx.add(msgx);
				   }
			   }else {
				// ERROR
					error = 2;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error +", "+ plan.getNombreCuenta() +   " no tiene codigo , revisar el plan contable - LE DIARIO 0503");
					listMsgx.add(msgx);
			   }
			   
			   
			 //columna 3 nombre CUENTA 
			   if (plan.getNombreCuenta()!=null) {
				   if (plan.getNombreCuenta().trim().compareTo("")!=0) {
					      fila.add(funciones.analizarLongitudCadena(100, plan.getNombreCuenta().trim()));
					     
				   }else {
					//error es vacio
						error = 3;
						msgx.setRetorno(""+error);
						msgx.setIsValido(false);
						msgx.setDescripcion("Error en el campo "+error +", "+ plan.getCodCuenta() +   " su nombre esta en blanco, revisar el plan contable - LE DIARIO 0503");
						listMsgx.add(msgx);
				   }
			   }else {
				// ERROR
					error = 3;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error +", "+ plan.getCodCuenta() +   " no tiene nombre , revisar el plan contable - LE DIARIO 0503");
					listMsgx.add(msgx);
			   }
			   
			   //columna 4 
			   fila.add("01");
			   
			   //columna 5
			   fila.add("-");
			   
			   //columna 6 
			   fila.add("");
			   
			   //columna 7 
			   fila.add("");
			   
			   //columna 8 siempre va ser uno porque se informa en el periodo actual si hay rectifiacion es manual
			   fila.add("1");
			   		  
				
	         if (error>0) {
	 			return listMsgx;
	 		 }else {

	 				String columna="";
	 				try {
	 					
	 					for (String col : fila) {
	 						 columna = columna + col + "|";
	 					}
	 					msgx.setRetorno(columna);
						msgx.setIsValido(true);
						msgx.setDescripcion(columna);
						listMsgx.add(msgx);
	 					return listMsgx;
	 				} catch (Exception e) {
	 					error = 22;
	 					msgx.setRetorno(""+error);
						msgx.setIsValido(false);
						msgx.setDescripcion("TODOS LOS CAMPOS VALIDOS, SE EFECTUO UN ERROR AL FINAL, ERROR DE CONEXION");
						listMsgx.add(msgx);
	 					return listMsgx;
	 				}
	 		}
		   
		
	}

	
	

}
