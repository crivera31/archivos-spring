package com.sprinboot.servicios.app.empresa.sis.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.empresa.sis.dao.RegistroDocumentosDao;
import com.sprinboot.servicios.app.empresa.sis.dto.CancelacionDocDto;
import com.sprinboot.servicios.app.empresa.sis.funciones.Funciones;
import com.sprinboot.servicios.app.empresa.sis.service.RegistroDocumentosServiceInterface;
import com.sprinboot.servicios.empresa.commons.entity.RegistroDocumentos;

@Service
public class RegistroDocumentosService implements RegistroDocumentosServiceInterface {
	
	@Autowired
	RegistroDocumentosDao registroDocumentosDao;
	
	@Autowired
	Funciones funciones;
	
	
	@Override
	@Transactional
	public RegistroDocumentos save (RegistroDocumentos registroDocumentos) {
		return registroDocumentosDao.save(registroDocumentos);
	}; 


	@Override
	@Transactional
	public RegistroDocumentos saveManual (RegistroDocumentos registroDocumentos) {
		return registroDocumentosDao.save(registroDocumentos);
	}; 
	
	
	
	
	public List<CancelacionDocDto> filtrandoSeriesNoRepetidas(List<CancelacionDocDto> lista) {
    	List<CancelacionDocDto> nuevalista= new ArrayList<>();
        for (CancelacionDocDto entidad : lista) {
            System.out.println("PASO 1 "+entidad.getIdRegDoc());
            if(entidad.getIdVoucher()!=-99){
                System.out.println("PASO 2 "+entidad.getIdRegDoc());
                nuevalista.add(entidad);
                for (CancelacionDocDto x : lista ) {
                     if( (funciones.getSinEspacios(entidad.getNumero()).compareToIgnoreCase(funciones.getSinEspacios(x.getNumero()))==0) && 
                             (entidad.getDoc().compareToIgnoreCase(x.getDoc())==0) && 
                             (entidad.getAbrevMoneda().compareTo(x.getAbrevMoneda())==0)
                    		 ){
                         System.out.println("ES IGUAL ");
                             //listreplica =(List)
                             lista.stream()
                             .filter(c -> c.getIdVoucher().compareTo(x.getIdVoucher())==0)
                             .map( c -> { c.setIdVoucher(-99); return c; } ).collect(Collectors.toList());
                           /* for (RegistroDocumentos c : lista) {
								if (c.getEstadoRegDoc().compareTo(x.getEstadoRegDoc())==0) {
									c.setEstadoRegDoc(-99);
								}
							}*/
                             System.out.println("LISTA STREAM ");
                             for (CancelacionDocDto xs : lista) {
                             System.out.println(""+xs.getIdVoucher());
                             } 
                     }
                }
            }
        }
        return nuevalista;
	}
	
	public void cancelarDocumentos(CancelacionDocDto filtro,List<CancelacionDocDto> listareplica) {
		listareplica.forEach(a ->  System.out.println("REPLICA ID = "+a.getIdRegDoc() + " | SERIE = "+a.getNumero()));
		 for (CancelacionDocDto r : listareplica) {
			 if (funciones.getSinEspacios(filtro.getNumero()).compareTo(funciones.getSinEspacios(r.getNumero()))==0 && 
           		  filtro.getDoc().compareTo(r.getDoc())==0 &&
           		 filtro.getAbrevMoneda().compareTo(r.getAbrevMoneda())==0) {
				  System.out.println("GUARDARÀ");
				  RegistroDocumentos registroDoc = registroDocumentosDao.buscarReg(r.getIdRegDoc());
				  registroDoc.setCancelado(1);
				   registroDocumentosDao.save(registroDoc);
             }
       }
	}
	
	@Override
	@Transactional
	public Boolean calculoPrevio (String codigo,String codPlan,String anio) {
		List<CancelacionDocDto> listCancelacion =null;
		listCancelacion = listarByPersona(codigo,codPlan,anio);
		
	try {
		List<CancelacionDocDto> nuevalista= new ArrayList<>();
		List<CancelacionDocDto> listreplica= new ArrayList<>();
		listCancelacion.forEach(a-> listreplica.add(a));
		
        if (listCancelacion!=null) {
        	nuevalista = filtrandoSeriesNoRepetidas(listCancelacion);
       
        	 System.out.println("SAPEE");
             nuevalista.forEach(e -> System.out.println(""+e.getNumero()));
            
            for (CancelacionDocDto filtro : nuevalista) {
                  BigDecimal suma = new BigDecimal("0.00");
                  for (CancelacionDocDto r : listreplica) {
                      if (funciones.getSinEspacios(filtro.getNumero()).compareTo(funciones.getSinEspacios(r.getNumero()))==0 && 
                    		  filtro.getDoc().compareTo(r.getDoc())==0 &&
                    		  filtro.getAbrevMoneda().compareTo(r.getAbrevMoneda())==0) {
                          suma = funciones.sumarRedondear(suma, r.getMonto(), 2);
                      }
                }
                if(suma.compareTo(new BigDecimal("0.00"))==0 || suma.compareTo(new BigDecimal("0"))==0 ) {
                    System.out.println("FUNCION COLOCAR CANCELAR DOCUMENTOS");
                    cancelarDocumentos(filtro, listreplica);
                } 
                System.out.println("SUMA = "+suma);
            }
		}
		
	} catch (Exception e2) {
		// TODO: handle exception
		return false;
	} 
		
     return true; 
	}; 
	
	
	
	
	@Override
	@Transactional
	public List<CancelacionDocDto> listarByPersona (String codigo,String codPlan,String anio) {
		List<CancelacionDocDto> listCanDocDto = new ArrayList<>();
		List<RegistroDocumentos> listRegDoc = new ArrayList<>();
		listRegDoc = registroDocumentosDao.listarByPersona(codigo, codPlan, anio);
        if (listRegDoc!=null) {
        	for (RegistroDocumentos registroDocumentos : listRegDoc) {
  			  CancelacionDocDto dto = new CancelacionDocDto();
  			  dto.setMes(registroDocumentos.getVoucher().getAsiento().getCodMes());
  			  dto.setVenc(registroDocumentos.getVoucher().getFechaVencimiento());
  			  dto.setDoc(registroDocumentos.getVoucher().getCodDocumento());
  			  dto.setNumero(registroDocumentos.getVoucher().getSerieNumero());
  			  dto.setMonto_inicial(registroDocumentos.getMontoTotalInicial());
  			  dto.setMonto(registroDocumentos.getMontoFaltante());
  			  dto.setAbrevMoneda(registroDocumentos.getVoucher().getAbreNomMoneda());
  			  dto.setFechaDoc(registroDocumentos.getVoucher().getFechaEmision());
  			  dto.setIdRegDoc(registroDocumentos.getId());
  			  dto.setCodOrigen(registroDocumentos.getVoucher().getAsiento().getCodOrigen());
  			  dto.setIdVoucher(registroDocumentos.getVoucher().getId());
  			  dto.setDuplicado(registroDocumentos.getDuplicado());
  			  listCanDocDto.add(dto);
  		 }
		}
		
     return listCanDocDto; 
	}; 
	
	
	@Override
	@Transactional
	public CancelacionDocDto  cancelacionDtoPorId (Integer id) {
		RegistroDocumentos registroDocumentos = new RegistroDocumentos();
		registroDocumentos = registroDocumentosDao.buscarReg(id);
		CancelacionDocDto dto = new CancelacionDocDto();
        if (registroDocumentos!=null) {
  			  
  			  dto.setMes(registroDocumentos.getVoucher().getAsiento().getCodMes());
  			  dto.setVenc(registroDocumentos.getVoucher().getFechaVencimiento());
  			  dto.setDoc(registroDocumentos.getVoucher().getCodDocumento());
  			  dto.setNumero(registroDocumentos.getVoucher().getSerieNumero());
  			  dto.setMonto_inicial(registroDocumentos.getMontoTotalInicial());
  			  dto.setMonto(registroDocumentos.getMontoFaltante());
  			  dto.setAbrevMoneda(registroDocumentos.getVoucher().getAbreNomMoneda());
  			  dto.setFechaDoc(registroDocumentos.getVoucher().getFechaEmision());
  			  dto.setIdRegDoc(registroDocumentos.getId());
  			  dto.setCodOrigen(registroDocumentos.getVoucher().getAsiento().getCodOrigen());
  			  dto.setIdVoucher(registroDocumentos.getVoucher().getId());
  			  dto.setDuplicado(registroDocumentos.getDuplicado());
  		 
		}
		
     return dto; 
	}; 
	
	
	
	
	@Override
	@Transactional
	public List<CancelacionDocDto> obtenerCancelacion (CancelacionDocDto dto) {
		List<RegistroDocumentos> lstRegistroDocumentos = null;
		List<CancelacionDocDto> listCanDocDto = new ArrayList<>();

        if (dto!=null) {
        	System.out.println("DTO NO ES NULL");
             lstRegistroDocumentos =  registroDocumentosDao.listarRegistroDocumentos(dto.getDoc(), dto.getNumero(), dto.getAbrevMoneda());
             for (RegistroDocumentos registroDocumentos : lstRegistroDocumentos) {
     			  CancelacionDocDto dto2 = new CancelacionDocDto();
     			dto2.setMes(registroDocumentos.getVoucher().getAsiento().getCodMes());
     			dto2.setVenc(registroDocumentos.getVoucher().getFechaVencimiento());
     			dto2.setDoc(registroDocumentos.getVoucher().getCodDocumento());
     			dto2.setNumero(registroDocumentos.getVoucher().getSerieNumero());
     			dto2.setMonto_inicial(registroDocumentos.getMontoTotalInicial());
     			dto2.setMonto(registroDocumentos.getMontoFaltante());
     			dto2.setAbrevMoneda(registroDocumentos.getVoucher().getAbreNomMoneda());
     			dto2.setFechaDoc(registroDocumentos.getVoucher().getFechaEmision());
     			dto2.setIdRegDoc(registroDocumentos.getId());
     			dto2.setCodOrigen(registroDocumentos.getVoucher().getAsiento().getCodOrigen());
     			dto2.setIdVoucher(registroDocumentos.getVoucher().getId());
     			dto2.setDuplicado(registroDocumentos.getDuplicado());
     			listCanDocDto.add(dto2);
     		 }
             return listCanDocDto;
        }else {
			return listCanDocDto;
		}
		
	};
	
	@Override
	@Transactional
	public Boolean guardarCancelacion (List<CancelacionDocDto> listdto) {

        if (listdto!=null) {
             for (CancelacionDocDto cancelacion : listdto) {
            	 RegistroDocumentos regdoc = registroDocumentosDao.buscarReg(cancelacion.getIdRegDoc());
            		 regdoc.setCancelado(1);
            		 registroDocumentosDao.save(regdoc);
            	 
     		 }
             return true;
        }else {
			return false;
		}
		
	};
	
	@Override
	@Transactional
	public Boolean guardarCancelacionRemove (List<CancelacionDocDto> listdto) {

        if (listdto!=null) {
             for (CancelacionDocDto cancelacion : listdto) {
            	 RegistroDocumentos regdoc = registroDocumentosDao.buscarRegAll(cancelacion.getIdRegDoc());
            		 regdoc.setCancelado(0);
            		 registroDocumentosDao.save(regdoc);
            	 
     		 }
             return true;
        }else {
			return false;
		}
		
	};
	
	@Override
	@Transactional
	public List<CancelacionDocDto> obtenerCancelacionFull (CancelacionDocDto dto) {
		List<RegistroDocumentos> lstRegistroDocumentos = null;
		List<CancelacionDocDto> listCanDocDto = new ArrayList<>();

        if (dto!=null) {
        	System.out.println("DTO NO ES NULL");
             lstRegistroDocumentos =  registroDocumentosDao.listarRegistroDocumentosFull(dto.getDoc(), dto.getNumero(), dto.getAbrevMoneda());
             for (RegistroDocumentos registroDocumentos : lstRegistroDocumentos) {
     			  CancelacionDocDto dto2 = new CancelacionDocDto();
     			dto2.setMes(registroDocumentos.getVoucher().getAsiento().getCodMes());
     			dto2.setVenc(registroDocumentos.getVoucher().getFechaVencimiento());
     			dto2.setDoc(registroDocumentos.getVoucher().getCodDocumento());
     			dto2.setNumero(registroDocumentos.getVoucher().getSerieNumero());
     			dto2.setMonto_inicial(registroDocumentos.getMontoTotalInicial());
     			dto2.setMonto(registroDocumentos.getMontoFaltante());
     			dto2.setAbrevMoneda(registroDocumentos.getVoucher().getAbreNomMoneda());
     			dto2.setFechaDoc(registroDocumentos.getVoucher().getFechaEmision());
     			dto2.setIdRegDoc(registroDocumentos.getId());
     			dto2.setCodOrigen(registroDocumentos.getVoucher().getAsiento().getCodOrigen());
     			dto2.setIdVoucher(registroDocumentos.getVoucher().getId());
     			dto2.setDuplicado(registroDocumentos.getDuplicado());
     			listCanDocDto.add(dto2);
     		 }
             return listCanDocDto;
        }else {
			return listCanDocDto;
		}
		
	};
	
	/*
	@Override
	@Transactional
	public List<RegistroDocumentos> guardarCancelacion (CancelacionDocDto dto) {
		List<RegistroDocumentos> lstRegistroDocumentos = null;
        if (dto!=null) {
        	System.out.println("DTO NO ES NULL");
         lstRegistroDocumentos =  registroDocumentosDao.listarRegistroDocumentos(dto.getVenc(), dto.getDoc(), dto.getNumero(), dto.getAbrevMoneda(), dto.getFechaDoc());
		
         Float resultado = (float) 0 ;
         if(lstRegistroDocumentos.size()>0) {
        	 System.out.println("REGISTRO DE DOCUMENTOS ES MAYOR QUE 0");
        	 for (RegistroDocumentos entidad : lstRegistroDocumentos) {
  		       resultado += entidad.getMontoFaltante();
  		       System.out.println("resultado");
  		     }
  	        if(resultado==0) {
  	        	 for (RegistroDocumentos registroDocumentos : lstRegistroDocumentos) {
  	        		   registroDocumentos.setCancelado(1);
  				       registroDocumentosDao.save(registroDocumentos);
  				} 
  	        	 return lstRegistroDocumentos;
  	        }else {
  	        	return lstRegistroDocumentos; 
  	        }
          
         }else {
        	 return lstRegistroDocumentos; 
         }
        
        }else {
			return lstRegistroDocumentos;
		}
		
	};
	
	*/
	
	
	
	
	
/*	@Override
	@Transactional
	public RegistroDocumentos guardarCancelacion (CancelacionDocDto dto) {
	       RegistroDocumentos registroDocumentos = null;
        if (dto!=null) {
        	
        	registroDocumentos =  registroDocumentosDao.buscarReg(dto.getIdRegDoc());
             if(registroDocumentos!=null){
            	 if(dto.getDuplicado()!=null) {
            		 registroDocumentos.setDuplicado(dto.getDuplicado());
            	 }else {
            		 registroDocumentos.setMontoFaltante(dto.getMonto());
                     
                	 if(dto.getMonto()==0) {
                		 registroDocumentos.setCancelado(1);
                	 }
            	 }
            	
            	 return registroDocumentos;
             }else {
            	 return null;
             }
		}else {
			return null;
		}
		
	}; */
	
	
	
}
