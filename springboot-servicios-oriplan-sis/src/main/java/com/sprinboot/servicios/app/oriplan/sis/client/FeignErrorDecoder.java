package com.sprinboot.servicios.app.oriplan.sis.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		   switch (response.status()) {
				case 400:
					
					break;
				case 404:
					     if(methodKey.contains("findCentroCostoById")){
					    	 return new ResponseStatusException(HttpStatus.valueOf(response.status()),"No se encuentra el anexo Unidad Negocio");
					     }
					     if(methodKey.contains("listarPlanContableOrdenado")){
				    	 return new ResponseStatusException(HttpStatus.valueOf(response.status()),"No se encuentra el anexo Plan Contable");
				         }
					     if(methodKey.contains("listarPlanContableDetalladoOrdenado")){
					    	 return new ResponseStatusException(HttpStatus.valueOf(response.status()),"No se encuentra el anexo Plan Contable");
					     }
					     /*if(methodKey.contains("buscarPlanCuentaForPles")){
					    	 return new ResponseStatusException(HttpStatus.valueOf(response.status()),"No se encuentra el anexo Cuenta");
					     }*/
					     
					break;
				default:
					return new Exception(response.reason());
				}
	 return null;
	}
}
