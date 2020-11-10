package com.sprinboot.servicios.app.empresa.constructores.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.empresa.constructores.dao.RegistroDocumentosDao;
import com.sprinboot.servicios.app.empresa.constructores.dao.RegistroLibrosDao;
import com.sprinboot.servicios.app.empresa.constructores.dto.LEDiarioPorOrigenDao;
import com.sprinboot.servicios.app.empresa.constructores.dto.LEDiarioVoucherDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.LEDiarioVucherDetDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.MsgxDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.RegistroComprasDetDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.RegistroComprasSubDetDto;
import com.sprinboot.servicios.app.empresa.constructores.funciones.Funciones;
import com.sprinboot.servicios.app.empresa.constructores.service.IPlesService;
import com.sprinboot.servicios.empresa.commons.entity.RegistroLibros;
@Service
public class PlesService implements IPlesService {

	@Autowired
	Funciones funciones;
	
	@Autowired
	RegistroLibrosDao registroLibrosDao;
	
	@Override
	public String generatorFilaPle(RegistroComprasDetDto rs,Integer mes ,String anioFront,RegistroComprasSubDetDto subdet,String codClasificacion) {
		System.out.println("ANO FRONT = " + anioFront+"|");   
		String anio = funciones.getSinEspacios(anioFront); 

		System.out.println("ANO  = " + anio+"|");   

		   int error =0 ;
		   List<String> fila = new ArrayList<String>();
		  
			   //Columna 1 Periodo , Obligatorio
			   try {
				   fila.add(funciones.getPeriodoPle(mes,anio));  
					
				} catch (Exception e) {
					error = 1;
				}
			   //Columna 2 ID del software , Obligatorio
			    try {
					   fila.add(""+subdet.getId());
				} catch (Exception e) {
					error = 2;
				}
			   //columna 3 CUO, A = asiento apertura, M = asiento mes, C = asiento cierre OBLIGATORIO
			   try {
				    fila.add(funciones.getCampo3Ple(subdet.getCodOrigen(), subdet.getNumAsiento(), mes));

				} catch (Exception e) {
					error = 3;
				}
			   //columna 4 Fecha de emision 
			   try {
				   fila.add(subdet.getFechaEmision());

				} catch (Exception e) {
					// TODO: handle exception
				}
			   //columna 5 Fecha de vencimiento , si el codigo de documento es el 14 no debe ser null
			   if(rs.getCodDocumento().compareTo("14")==0 ) {
				 if (subdet.getFechaVencimiento()!=null) {
					 fila.add(subdet.getFechaVencimiento());
			     }else {
			        error=1; 
				 } 
			   }else {  
				 fila.add(" ");
			   }
			   //columna 6 Codigo de documento 
			   if (rs.getCodDocumento()!=null) {
				   fila.add(rs.getCodDocumento());
			    }else {
					error = 6;
				}
			   //columna 7 Serie 
			   
			   if(subdet.getSerie().compareTo(" ")==0) {
				   fila.add(subdet.getSerie()); 
			   }else {
				   if (rs.getCodDocumento().compareTo("01")==0) {
					    if (subdet.getSerie().length()==4) {
							   fila.add(subdet.getSerie()); 

						}else {
							error = 7;
						}
					}else {
						   fila.add(subdet.getSerie()); 

					}
				  
			   }
			  
			   //columna 8 Año emision de la DUA o DSI
			   if (rs.getCodDocumento().compareTo("50")==0 || rs.getCodDocumento().compareTo("52")==0) {
			   fila.add(anio);
			   }else {
				fila.add("0");
			   }
			   //columna 9 Numero de comprobante
			   if (subdet.getComprobantePago()!=null) {
				   fila.add(subdet.getComprobantePago());
				}else {
					error = 9;
				}			 
			   //columna 10
			   fila.add("");
			   //columna 11 , 12  y 13Codigo del documento del cliente o proveddor RUC o DNI y razonsocial
			   if (funciones.getValidarCampo11Ple(rs.getCodDocumento())==1 ) {
				   if (subdet.getCodTipoDoc()==null) {
					 error = 11;
				   }else {
					   if (subdet.getCodRuc()==null) {
						 error = 12;
				    	}else {
				    		 if (subdet.getRazonSocial()==null) {
									error = 13;
								   }else {
										 fila.add(subdet.getCodTipoDoc());
										 fila.add(subdet.getCodRuc());
			                             fila.add(subdet.getRazonSocial());
								   }
						}
				  }
			   }else {
				 fila.add(subdet.getCodTipoDoc()!=null ? subdet.getCodTipoDoc() : "");
				 fila.add(subdet.getCodRuc() !=null ? subdet.getCodRuc() : "");
				 fila.add(subdet.getRazonSocial()!=null ? subdet.getRazonSocial() : "");
			   }
			   //columna 14 
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getBaseImpOpe(), 2));
				} catch (Exception e) {
					error = 14;
				}
			   // columna 15
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getIgvBaseImpOpe(), 2));

				} catch (Exception e) {
					error = 15;
				}
			   //columna 16
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getBaseImpAdq(), 2));
				} catch (Exception e) {
					error = 16;
				}
			       
			   //columna 17
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getIgvBaseImpAdq(), 2));

				} catch (Exception e) {
					error = 17;
				}
			   //columna 18
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getAdqSinDer(), 2));

				} catch (Exception e) {
					error = 18;
				}
		       //columna 19
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getIgvBaseAdqSinDer(), 2));
				} catch (Exception e) {
				    error = 19;
				}
			   //columna 20
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getAdqNoGrav(), 2));

				} catch (Exception e) {
					error = 20;
				}
		       //columna 21
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getIsc(), 2));

				} catch (Exception e) {
					error = 21;
				}
			   //columna 22
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getOtrosTributos(), 2));

				} catch (Exception e) {
					error = 22;
				}
			   //columna 23
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getTotal(), 2));

				} catch (Exception e) {
					error = 23;
				}
			   
			   //columna 24
			   try {
				   fila.add(funciones.getFormatoMonedaPle(subdet.getMoneda()));

				} catch (Exception e) {
					error=24;
				}
			   //columna 25
			   subdet.setTipoCambio(funciones.getFormatoNumber(new String(subdet.getTipoCambio()),3));
			  try {
				  if (funciones.getValidarTipoCambioPle(subdet.getTipoCambio())==1) {
					   fila.add(subdet.getTipoCambio());
				   }else {
					 error = 25;
				   }
				} catch (Exception e) {
					 error = 25;
				} 
			   //columna 26,27,28,29,30
			  try {
				  if (funciones.getValidarSiEsObligatorioCampo26(rs.getCodDocumento())==1) {
					   System.out.println("SI ES OBLIGAROIO CAMPO 26");
					   //Es obligatorio
					   if (subdet.getFechaRef()!=null && subdet.getFechaRef().compareTo("")!=0) {
					       if (funciones.getValidarFechaConElPeriodo(subdet.getFechaRef(),anio,mes)==1) {
					    	   fila.add(subdet.getFechaRef()); 
					    	   if (subdet.getCodDocumentoRef()!=null && subdet.getCodDocumentoRef().compareTo("")!=0) {
								    fila.add(subdet.getCodDocumentoRef());
								    if (subdet.getNumeroRef()!=null && subdet.getNumeroRef().compareTo("")!=0) {
										fila.add(subdet.getNumeroRef());
									    //columna 29
										fila.add(" ");
										if (subdet.getComprobanteRef()!=null && subdet.getComprobanteRef().compareTo("")!=0) {
											fila.add(subdet.getComprobanteRef());
										}else {
											error = 30;
										}
									}else {
										error = 28;
									}
							   }else {
								 error = 27;
							   }
					       }else {
							error = 26;
						   }
					   }else {
						 error = 26;
					   }
				   }else {
					   //No es obligatorio
					   System.out.println("NO ES OBLIGAROIO CAMPO 26");
					   //columna 26
					   if (subdet.getFechaRef()!=null && subdet.getFechaRef().compareTo("")!=0) {
						   try {
							   if (funciones.getValidarFechaConElPeriodo(subdet.getFechaRef(),anio,mes)==1) {
								   fila.add(subdet.getFechaRef()); 

							   }else {
								   error = 26;
							   }
						   } catch (Exception e) {
							    error = 26;
						  }
						  
					   }else {
						   fila.add("01/01/0001"); 
					   }
					   //columna 27
					   if (subdet.getCodDocumentoRef()!=null && subdet.getCodDocumentoRef().compareTo("")!=0) {
						    fila.add(subdet.getCodDocumentoRef());
					   }else {
						 fila.add("00");
					   }
					   //columna 28
					   if (subdet.getNumeroRef()!=null && subdet.getNumeroRef().compareTo("")!=0) {
							fila.add(subdet.getNumeroRef());
					   }else {
							fila.add("-");
					   }
					   //columna 29
					   fila.add(" ");
					   //columna 30
					   if (subdet.getComprobanteRef()!=null) {
							fila.add(subdet.getComprobanteRef());
					   }else {
						   fila.add("-");
					   }
				   };
				} catch (Exception e) {
					error = 26;
				}
			  
			   
			   //columna 31
			  
			   if (subdet.getdFechaRef()!=null && subdet.getdFechaRef().compareTo("")!=0) {
				   try {
					   //if (funciones.getValidarFechaConElPeriodo(subdet.getdFechaRef(),anio,mes)==1) {
						   fila.add(subdet.getdFechaRef());
					 //  }else {
					//	error = 31;
					 //  }
					} catch (Exception e) {
						error = 31;
					}
				  
			   }else {
					fila.add("01/01/0001");
			   }
			   
			   //columna 32			  
			   if (subdet.getdNumeroRef()!=null && subdet.getdNumeroRef().compareTo("")!=0) {
					fila.add(subdet.getdNumeroRef());

			   }else {
					fila.add("0");
			   }
			   //columna 33			  
				fila.add("");
			   //columna 34
				if (codClasificacion.compareTo("0")==0) {
					// no es obligatorio la empresa
					fila.add(" ");
				}else {
					//si es obligatorio
					if (codClasificacion.compareTo("1")==0) {
						fila.add(codClasificacion);
					}else {
						error=34;
					}
				}
				
				//columna 35
				fila.add("");
				//columna 36
				fila.add("");
				//columna 37
				fila.add("");
				//columna 38
				fila.add("");
				//columna 39
				fila.add("");
				//columna 40
				fila.add("");
				//columna 41
				BigDecimal suma = subdet.getBaseImpOpe().add(subdet.getIgvBaseImpOpe()).add(subdet.getBaseImpAdq()).add(subdet.getIgvBaseImpAdq());
				Integer valor41= -1;
				try {
					 valor41 = funciones.getValorParaCampo41(subdet.getFechaEmision(), mes, anio, suma);

				} catch (Exception e) {
					error = 41;
				}
				
				if (valor41==-1) {
					error = 41;
				}else {
					fila.add(""+valor41);
				}
				
				
				
		   
         if (error>0) {
			return ""+error;
		 }else {

				String columna="";
				try {
					
					for (String col : fila) {
						 columna = columna + col + "|";
					}
				} catch (Exception e) {
					error = 42;
				}
			return columna;
		}
		  
	
	}
	
	

	@Override
	public String generatorFilaLEVentas(RegistroComprasDetDto rs,Integer mes ,String anioFront,RegistroComprasSubDetDto subdet) {
		String anio = funciones.getSinEspacios(anioFront); 

		   int error =0 ;
		   List<String> fila = new ArrayList<String>();
		   Integer campo34 = 1;
			  //Columna 1 Periodo , Obligatorio
			   try {
				   fila.add(funciones.getPeriodoPle(mes,anio));  
					
				} catch (Exception e) {
					error = 1;
				}
			     //Columna 2 ID del software , Obligatorio
			    try {
					   fila.add(""+subdet.getId());
				} catch (Exception e) {
					error = 2;
				}
			   //columna 3 CUO, A = asiento apertura, M = asiento mes, C = asiento cierre OBLIGATORIO
			   try {
				    fila.add(funciones.getCampo3Ple(subdet.getCodOrigen(), subdet.getNumAsiento(), mes));

				} catch (Exception e) {
					error = 3;
				}
			      //columna 4 Fecha de emision  si el campo 34 es 2 no es obligatorio
			   try {
				   fila.add(subdet.getFechaEmision());

				} catch (Exception e) {
					// TODO: handle exception
				}
			   //columna 5 Fecha de vencimiento , si el codigo de documento es el 14 y es campo34 es diferente de 2 es obligatorio
			   if(rs.getCodDocumento()=="14" && campo34.compareTo(new Integer(2))==0) {
				 if (subdet.getFechaVencimiento()!=null) {
					 fila.add(subdet.getFechaVencimiento());
			     }else {
			        error=1; 
				 } 
			   }else {  
				 fila.add(" ");
			   }
			   //columna 6 Codigo de documento 
			   if (rs.getCodDocumento()!=null) {
				   fila.add(rs.getCodDocumento());
			    }else {
					error = 6;
				}
			   //columna 7 Serie 
			   if(subdet.getSerie().length()==4 || subdet.getSerie().compareTo(" ")==0) {
				   fila.add(subdet.getSerie()); 
			   }else {
				   error = 7;
			   }
			  
			   //columna 8 Numero de comprobante
			   if (subdet.getComprobantePago()!=null) {
				   fila.add(subdet.getComprobantePago());
				}else {
					error = 8;
				}
			   
			   //columna 9
			   fila.add("");
			    //columna 10 , 11  y 12Codigo del documento del cliente o proveddor RUC o DNI y razonsocial
			   if (funciones.getValidarCampo10VentasPle(rs.getCodDocumento(),subdet.getTotal(),subdet.getValorExportacion())==1 ) {
				   if (subdet.getCodTipoDoc()==null) {
					 error = 10;
				   }else {
					   if (subdet.getCodRuc()==null) {
						 error = 11;
				    	}else {
				    		 if (subdet.getRazonSocial()==null) {
									error = 12;
								   }else {
										 fila.add(subdet.getCodTipoDoc());
										 fila.add(subdet.getCodRuc());
			                             fila.add(subdet.getRazonSocial());
								   }
						}
				  }
			   }else {
				 fila.add(subdet.getCodTipoDoc()!=null ? subdet.getCodTipoDoc() : "");
				 fila.add(subdet.getCodRuc() !=null ? subdet.getCodRuc() : "");
				 fila.add(subdet.getRazonSocial()!=null ? subdet.getRazonSocial() : "");
			   }
			   //columna 13 
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getValorExportacion(), 2));
				} catch (Exception e) {
					error = 13;
				}
			   //columja 14
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getBaseImpAdq(), 2));

				} catch (Exception e) {
					error = 14;
				}
			   
			   // columna 15 DESCUENTO DE LA BASE IMPONIBLE
			   fila.add("0.00");
			  
			  //columna 16  IPM IGV
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getIgvBaseImpAdq(), 2));
				} catch (Exception e) {
					error = 16;
				}
			       
			   //columna 17 DESCUENTO DEL IGV IPM
			   fila.add("0.00");

			      //columna 18
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getExonerado(), 2));

				} catch (Exception e) {
					error = 18;
				}
			    //columna 19
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getInafecto(), 2));
				} catch (Exception e) {
				    error = 19;
				}
			   //columna 20
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getIsc(), 2));

				} catch (Exception e) {
					error = 20;
				}
			    //columna 21 BASE IMPONIBLE DE LA OPERACION GRAVADA CON EL IMPUESTO A LAS VENTAS DEL ARROZ PILADO
			   fila.add("0.00");
			   
			  //columna 22 IMPUESTO A LAS VENTAS DEL ARROZ PILADO
			     fila.add("0.00");
			  //columna 23
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getOtrosTributos(), 2));

				} catch (Exception e) {
					error = 23;
				}
			   
			   
			     //columna 24
			   try {
				   fila.add(funciones.getFormatoNumber(subdet.getTotal(), 2));

				} catch (Exception e) {
					error = 24;
				}
			  
			   //columna 25
		       try {
				   fila.add(funciones.getFormatoMonedaPle(subdet.getMoneda()));

				} catch (Exception e) {
					error=24;
				}
		
		       //columna 26
		      subdet.setTipoCambio(funciones.getFormatoNumber(new String(subdet.getTipoCambio()),3));
			  try {
				  if (funciones.getValidarTipoCambioPle(subdet.getTipoCambio())==1) {
					   fila.add(subdet.getTipoCambio());
				   }else {
					 error = 25;
				   }
				} catch (Exception e) {
					 error = 25;
				} 
			     //columna 27,28,29,30
			  try {
				  if (funciones.getValidarSiEsObligatorioCampo27Ventas(rs.getCodDocumento(),campo34)==1) {
					   System.out.println("SI ES OBLIGAROIO CAMPO 27,28,29,30");
					   //Es obligatorio
					   if (subdet.getFechaRef()!=null && subdet.getFechaRef().compareTo("")!=0) {
					       if (funciones.getValidarFechaConElPeriodo(subdet.getFechaRef(),anio,mes)==1) {
					    	   fila.add(subdet.getFechaRef()); 
					    	   if (subdet.getCodDocumentoRef()!=null && subdet.getCodDocumentoRef().compareTo("")!=0) {
								    fila.add(subdet.getCodDocumentoRef());
								    if (subdet.getNumeroRef()!=null && subdet.getNumeroRef().compareTo("")!=0) {
										fila.add(subdet.getNumeroRef());
									    //columna 29
										//fila.add(" ");
										if (subdet.getComprobanteRef()!=null && subdet.getComprobanteRef().compareTo("")!=0) {
											fila.add(subdet.getComprobanteRef());
										}else {
											error = 30;
										}
									}else {
										error = 29;
									}
							   }else {
								 error = 28;
							   }
					       }else {
							error = 27;
						   }
					   }else {
						 error = 27;
					   }
				   }else {
					   //No es obligatorio
					   System.out.println("NO ES OBLIGAROIO CAMPO 27,28,29,30");
					   //columna 27
					   if (subdet.getFechaRef()!=null && subdet.getFechaRef().compareTo("")!=0) {
						   try {
							   if (funciones.getValidarFechaConElPeriodo(subdet.getFechaRef(),anio,mes)==1) {
								   fila.add(subdet.getFechaRef()); 

							   }else {
								   error = 27;
							   }
						   } catch (Exception e) {
							    error = 27;
						  }
						  
					   }else {
						   fila.add("01/01/0001"); 
					   }
					   //columna 28
					   if (subdet.getCodDocumentoRef()!=null && subdet.getCodDocumentoRef().compareTo("")!=0) {
						    fila.add(subdet.getCodDocumentoRef());
					   }else {
						 fila.add("00");
					   }
					   //columna 29
					   if (subdet.getNumeroRef()!=null && subdet.getNumeroRef().compareTo("")!=0) {
							fila.add(subdet.getNumeroRef());
					   }else {
							fila.add("-");
					   }
					   //columna 30
					   if (subdet.getComprobanteRef()!=null) {
							fila.add(subdet.getComprobanteRef());
					   }else {
						   fila.add("-");
					   }
				   };
				} catch (Exception e) {
					error = 27;
				}
			  
			   
			   //columna 31
			  
			  	fila.add("");
			  //columna 32	
			  	fila.add("");

			  //columna 33	
			  	fila.add("");

			   		 
				//columna 34
				BigDecimal suma = subdet.getBaseImpAdq().add(subdet.getIgvBaseImpAdq());
				Integer valor41= -1;
				try {
					 valor41 = funciones.getValorParaCampo34Ventas(subdet.getFechaEmision(), mes, anio, suma);

				} catch (Exception e) {
					error = 41;
				}
				
				if (valor41==-1) {
					error = 41;
				}else {
					fila.add(""+valor41);
				}
				
				
				
		   
         if (error>0) {
			return ""+error;
		 }else {

				String columna="";
				try {
					
					for (String col : fila) {
						 columna = columna + col + "|";
					}
				} catch (Exception e) {
					error = 42;
				}
			return columna;
		}
		  
	
	}



	@Override
	public List<MsgxDto>  generatorFilaLEDiario(LEDiarioVucherDetDto voucher, Integer mes, String anioFront,LEDiarioVoucherDto asiento,LEDiarioPorOrigenDao origen,String codPlanParametroCompras, String codPlanParametroVentas) {
		String anio = funciones.getSinEspacios(anioFront); 
		int error =0 ;
		List<String> fila = new ArrayList<String>();
		List<MsgxDto> listMsgx = new ArrayList<MsgxDto>();
		MsgxDto msgx = new MsgxDto();
		 //Columna 1 Periodo , Obligatorio
			   try {
				   fila.add(funciones.getPeriodoPle(mes,anio));  
					
				} catch (Exception e) {
					error = 1;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error + " al generar el periodo (MES,AÑO) - LE DIARIO 0501");
					listMsgx.add(msgx);
					
				}
			   
			   //Columna 2 ID del software VOucher , Obligatorio
			    try {
					   fila.add("LD"+voucher.getId());
				} catch (Exception e) {
					error = 2;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error + " al CUO, NO EXISTE EL ID DEL VOUCHER   -LE DIARIO 0501");
					listMsgx.add(msgx);
				}	   
			    
		      //columna 3 CUO, A = asiento apertura, M = asiento mes, C = asiento cierre OBLIGATORIO
			   try {
				    fila.add(funciones.getCampo3Ple(origen.getCodOrigen(), asiento.getNumAsiento(), mes));

				} catch (Exception e) {
					error = 3;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error + " FALLA LO FUNCION PARA GENERAR EL CORRELATIVO, REVISAR SI EXISTE EL CODIGO ORIGEN, NUMERO DE ASIENTO -LE DIARIO 0501");
					listMsgx.add(msgx);
				}
			   
			     //columna 4  si el campo 34 es 2 no es obligatorio
			   try {
				   fila.add(funciones.getTrim(funciones.getCuentaParaPle(voucher.getCodPlan())));

				} catch (Exception e) {
					error = 4;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error + " NO EXISTE EL CODIGO DEL PLAN EN EL ASIENTO "+ asiento.getNumAsiento()+"  -LE DIARIO 0501 - VOU "+voucher.getId());
					listMsgx.add(msgx);
				}
			   //columna 5 
			     fila.add("");
			   //columna 6  Codigo Unidad de Negocio CENTRO DE COSTO
			     if (voucher.getCodUnidadNegocio()!=null) {
					fila.add(voucher.getCodUnidadNegocio());
				}else {
					fila.add("");
				}
			    //columna 7 MONEDA
		       try {
				   fila.add(funciones.getFormatoMonedaPle(voucher.getAbreNomMoneda()));
				} catch (Exception e) {
					error=7;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error + " al generar FORMATO DE LA MONEDA, LO MAS PROBABLE QUE NO TENGA SELECCIONADO UNA MONEDA EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
					listMsgx.add(msgx);
				}
		       //columna8 Codigo del documento del cliente o proveddor RUC o DNI 
		         fila.add(voucher.getCodTipoDoc()!=null ? voucher.getCodTipoDoc() : " ");
		       // columna 9 numero 
		         try {
					 fila.add(voucher.getCodRuc() !=null ? funciones.getTrim(voucher.getCodRuc()) : " ");
				} catch (Exception e) {
			     	error = 9 ;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error + " al generar FORMATO RUC DNI , EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
					listMsgx.add(msgx);
				}
		        
				 //columna 10 11 12

		        if (voucher.getNumeroComprobante()!=null) {
					//Obligatorio
		        	    //10
		        	 	if (voucher.getCodDocumento()!=null) {
						   fila.add(voucher.getCodDocumento());
						   
						   
						 //11
						    if(voucher.getCodDocumento().compareTo("01")==0 ) {
						    	if (voucher.getSerie()!=null && voucher.getSerie().length()==4 ) {
				        	 		
					        	 	
					 				   fila.add(voucher.getSerie()); 

					 			    }else {
						 				   error = 11;
					 			    	msgx.setRetorno(""+error);
										msgx.setIsValido(false);
										msgx.setDescripcion("Error en el campo "+error + ", LONGITUD INCORRECTA DE LA SERIE EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
										listMsgx.add(msgx);
					 				}
						    }else {
						    	
				        	 		
					        	 	
					 				   fila.add(voucher.getSerie()); 

					 			    
							}
			        	 	
			        	 	
					    }else {
							   error = 10;

					    	msgx.setRetorno(""+error);
							msgx.setIsValido(false);
							msgx.setDescripcion("Error en el campo "+error + ", NO EXISTE EL CODIGO DE DOCUMENTO EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
							listMsgx.add(msgx);
						}
		        	 	
		        	 	//12
		        	 	if (voucher.getNumeroComprobante()!=null) {
			 				   fila.add(voucher.getNumeroComprobante()); 
		 			    }else {
			 				   error = 12;
		 			    	msgx.setRetorno(""+error);
							msgx.setIsValido(false);
							msgx.setDescripcion("Error en el campo "+error + ", NO EXISTE EL NUMERO DE COMPROBANTE EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
							listMsgx.add(msgx);
		 				}
		        	 	
				}else {
					//no obligatorio
					 //10
	        	 	if (voucher.getCodDocumento()!=null) {
	        	 		fila.add(voucher.getCodDocumento());
				    }else {
				    	fila.add("00");
					}
	        	 	//11
	        	 	if (voucher.getSerie()!=null) {
	 				   fila.add(voucher.getSerie()); 

	 			    }else {
	 			       fila.add(" ");
	 				}
	        	 	//12
	        	 	if (voucher.getNumeroComprobante()!=null) {
		 				  fila.add(voucher.getNumeroComprobante()); 
	 			    }else {
	 			        fila.add("0");
	 				}
				} 
			  //columna 13 FEcha contable asiento
		        if (asiento.getFechaAsiento()!=null) {
		        	 try {
		        		 if (funciones.getValidarFechaConElPeriodo(asiento.getFechaAsiento(),anio,mes)==1) {
					        	//es valido
					        	 fila.add(asiento.getFechaAsiento()); 
					        	
					        }else {
								error = 13;
					        	msgx.setRetorno(""+error);
								msgx.setIsValido(false);
								msgx.setDescripcion("Error en el campo "+error + ", LA FECHA ASIENTO ES MAYOR AL PERIODO SEÑALADO , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
								listMsgx.add(msgx);
							}
					} catch (Exception e) {
						error = 13;
			        	msgx.setRetorno(""+error);
						msgx.setIsValido(false);
						msgx.setDescripcion("Error Exception en el campo "+error + ", LA FECHA ASIENTO ES MAYOR AL PERIODO SEÑALADO , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
						listMsgx.add(msgx);
					}
		        	  
				}else {
					fila.add("01/01/1970");
				}
		      //columna 14 FVENCI
		        if (voucher.getFechaVencimiento()!=null) {
					fila.add(voucher.getFechaVencimiento());
				}else {
					fila.add("01/01/1970");
				}
		  	  //columna 15 FEcha emision
		        if (voucher.getFechaEmision()!=null) {
		        	try {
		        		if (funciones.getValidarFechaConElPeriodo(voucher.getFechaEmision(),anio,mes)==1) {
				        	//es valido
				        	 fila.add(voucher.getFechaEmision()); 
				        	
				        }else {
							error = 15;
				        	msgx.setRetorno(""+error);
							msgx.setIsValido(false);
							msgx.setDescripcion("Error en el campo "+error + ", LA FECHA DE EMISION ES MAYOR AL PERIODO SEÑALADO , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
							listMsgx.add(msgx);
						}
					} catch (Exception e) {
						error = 15;
			        	msgx.setRetorno(""+error);
						msgx.setIsValido(false);
						msgx.setDescripcion("Error Exception en el campo "+error + ", LA FECHA DE EMISION ES MAYOR AL PERIODO SEÑALADO , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
						listMsgx.add(msgx);
					}
		        	  
				}else {
					fila.add("01/01/1970");
				}
		        
		        //columna 16 glosario maximo hasta 200
		        if (voucher.getGlosario()!=null) {
		        	try {
		        		  fila.add(funciones.analizarLongitudCadena(200, voucher.getGlosario()));
					} catch (Exception e) {
						error = 16;
						msgx.setRetorno(""+error);
						msgx.setIsValido(false);
						msgx.setDescripcion("Error Exception en el campo "+error + ", HUBO UN ERROR EN EL GLOSARIO , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
						listMsgx.add(msgx);
					}
		           
				}else {
					/*error = 16;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error + ", HUBO UN ERROR EN EL GLOSARIO , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
					listMsgx.add(msgx);*/
					//HACER BANDERA PARA CUANDO EL CONTADOR SE OLVIDO DE LLENAR EL GLOSARIO
					fila.add(" "); //OJO CAMPO OBLIGATORIO
				}
		        
		        //columna 17 glosario referencial 
		        fila.add("");
		        
		        
		        //columna 18 debe
		        if (voucher.getDebe()!=null) {
		        	try {
		        		fila.add(funciones.getFormatoNumber(voucher.getDebe(),2));
					} catch (Exception e) {
						error = 18;
						msgx.setRetorno(""+error);
						msgx.setIsValido(false);
						msgx.setDescripcion("Error Exception en el campo "+error + ", AL GENERAR FUNCION FORMATO NUMERO DEL DEBE  , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
						listMsgx.add(msgx);
					}
					
				}else {
					error = 18;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error + ", AL GENERAR FUNCION FORMATO NUMERO DEL DEBE  , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
					listMsgx.add(msgx);
				
				}
		        
		        //columna 19
		        if (voucher.getHaber()!=null) {
		        	try {
		        		fila.add(funciones.getFormatoNumber(voucher.getHaber(),2));
					} catch (Exception e) {
						error = 19;
						msgx.setRetorno(""+error);
						msgx.setIsValido(false);
						msgx.setDescripcion("Error Exception en el campo "+error + ", AL GENERAR FUNCION FORMATO NUMERO DEL HABER  , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
						listMsgx.add(msgx);
					}
				
				}else {
					error = 19;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error + ", AL GENERAR FUNCION FORMATO NUMERO DEL HABER  , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
					listMsgx.add(msgx);
				
				}
		        
		        // columna 20
		     try {
		    	    if ((funciones.getTrim(voucher.getCodPlan()).compareTo(funciones.getTrim(codPlanParametroCompras))==0
		    	    		|| funciones.getTrim(voucher.getCodPlan()).compareTo(funciones.getTrim(codPlanParametroVentas))==0) 
		    	    		&& ( origen.getCodOrigen().trim().compareTo("01")==0 || origen.getCodOrigen().trim().compareTo("02")==0 )) {
					    //Verificar si tiene asignado SIN LIBRO 
			        	if (getCampo20DiarioTieneLibro00(voucher.getCodDocumento(), mes, anio, codPlanParametroCompras, codPlanParametroVentas, voucher.getId()) ) {
							fila.add("");
						}else {
							if (voucher.getCodDocumento()!=null) {
						    	try {
						        	String campo20 = getCampo20Diario(voucher.getCodDocumento(), mes, anio, codPlanParametroCompras, codPlanParametroVentas, voucher.getId());
						        	if (campo20.compareTo("3")==0 ) {
						        		error=20;
						        		msgx.setRetorno(""+error);
										msgx.setIsValido(false);
										msgx.setDescripcion("Error en el campo "+error + ", EL VOUCHER TIENE MAS DE UN REGISTRO EN LOS LIBROS , ERROR DE CONEXION , HAY QUE VERIFICAR LA BD , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
										listMsgx.add(msgx);
										
									}else {
										if (campo20.compareTo("1")==0) {
											error = 20;
											msgx.setRetorno(""+error);
											msgx.setIsValido(false);
											msgx.setDescripcion("Error en el campo "+error + ", EL VOUCHER CON CODIGO DE PLAN IGV SEÑALADO EN EL PARAMETRO DE CUENTAS, NO TIENE UN REGISTRO DE LIBRO , HAY QUE VERIFICAR LA BD , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
											listMsgx.add(msgx);
										}else {
											if (campo20.compareTo("2")==0) {
												error = 20;
												msgx.setRetorno(""+error);
												msgx.setIsValido(false);
												msgx.setDescripcion("Error en el campo "+error + ", EL VOUCHER CON CODIGO DE PLAN IGV SEÑALADO EN EL PARAMETRO DE CUENTAS "+voucher.getCodPlan()+"  SE ENCONTRO EN EL LIBRO DE VENTAS Y COMPRAS , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
												listMsgx.add(msgx);
											}else {
												fila.add(campo20);
											}
											
										}
									}
								} catch (Exception e) {
									error = 20;
								}
							} else {
								error = 10;
							}
						}
			        	
			        
		
			        }else {
						fila.add("");
					}
			} catch (Exception e) {
				error=20;
        		msgx.setRetorno(""+error);
				msgx.setIsValido(false);
				msgx.setDescripcion("Error Exception en el campo "+error + ", EL VOUCHER TIENE MAS DE UN REGISTRO EN LOS LIBROS , ERROR DE CONEXION , HAY QUE VERIFICAR LA BD , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
				listMsgx.add(msgx);
			}
		    
		        
		        
		        //columna 21
		        if (voucher.getFechaEmision()==null) {
					voucher.setFechaEmision("01/01/1970");
				}
		       // int campo21 = funciones.getValidarCampo21LEdiario(voucher.getFechaEmision(), mes, anio);
		         int campo21 = 1;
		        if (campo21==-1) {
		        	error = 21;
					msgx.setRetorno(""+error);
					msgx.setIsValido(false);
					msgx.setDescripcion("Error en el campo "+error + " VERIFICAR FECHA DE EMISION QUE SEA MENOR O IGUAL AL PERIODO  , ERROR EN EL ASIENTO "+asiento.getNumAsiento() + " ORIGEN "+origen.getCodOrigen() +"  -LE DIARIO 0501 - VOU "+voucher.getId());
					listMsgx.add(msgx);
				}else {
					fila.add(""+campo21);
				}
		
			  
				
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

	
	public String getCampo20Diario(String documento,Integer mes, String anio, String codPlanParametroCompras,String codPlanParametroVentas, Integer voucherId) {
		 RegistroLibros registroLibro = null;
	        List<String> codigosLibros = new ArrayList<String>();
	        int falla = 0;
	        int encontrado = 0;
	        codigosLibros.add("01"); codigosLibros.add("02");
	        String campo20="";
	        for (String codigo : codigosLibros) {
	        	   List<RegistroLibros> listRegistroLibro = new ArrayList<RegistroLibros>();
	        	if (codigo.compareTo("01")==0) {
			        listRegistroLibro = registroLibrosDao.buscarFacturaEnLibros(documento, mes, anio,codigo, codPlanParametroCompras, voucherId);
				}else {
			        listRegistroLibro = registroLibrosDao.buscarFacturaEnLibros(documento, mes, anio, codigo , codPlanParametroVentas, voucherId);
				}
		        if (listRegistroLibro.size()>1) {
					falla=1;
		        	break;
				}else {
					if (listRegistroLibro.size()==1) {
						campo20= funciones.getCodigoLibroPLE(listRegistroLibro.get(0).getLibro().getCodLibro()) + "&"  +funciones.getPeriodoPle(mes, anio) + "&" + listRegistroLibro.get(0).getId()  + "&" + funciones.getCampo3Ple(listRegistroLibro.get(0).getVoucher().getAsiento().getCodOrigen(), listRegistroLibro.get(0).getVoucher().getAsiento().getNumAsiento(), mes); 
						encontrado++;
					}
				}
			}
	        
	        if (falla>0) {
	        	//hay un fallo xk hay dos relibros en un solo voucher
	    	    return "3";   

			}else {
				if (encontrado==1) {
					return campo20;
				}else {
					if (encontrado==0) {
						// no se encontro en ningun libro
						return "1"; 
					}else {
						// se encontro en los dos libros
						return "2";
					}
				}
		
			}
	}
	
	
	public boolean getCampo20DiarioTieneLibro00(String documento,Integer mes, String anio, String codPlanParametroCompras,String codPlanParametroVentas, Integer voucherId) {

	        List<RegistroLibros> listRegistroLibro = new ArrayList<RegistroLibros>();
	        listRegistroLibro = registroLibrosDao.buscarFacturaEnLibros(documento, mes, anio,"00", codPlanParametroCompras, voucherId);
	        if (listRegistroLibro.size()>0) {
				 return true;
			}else {
		        listRegistroLibro = registroLibrosDao.buscarFacturaEnLibros(documento, mes, anio,"03", codPlanParametroCompras, voucherId);
		        	if (listRegistroLibro.size()>0) {
						return true;
					}else {
				        listRegistroLibro = registroLibrosDao.buscarFacturaEnLibros(documento, mes, anio,"04", codPlanParametroCompras, voucherId);
				        	if (listRegistroLibro.size()>0) {
				        		return true;
							}else {
								return false;
							}
					}

			}
	       
	}

}