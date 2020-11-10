package com.sprinboot.servicios.app.seguridad.sis.client;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*import com.sprinboot.servicios.app.helpdesk.sis.dto.CuentaDetalladaDto;
import com.sprinboot.servicios.app.helpdesk.sis.dto.PlanContableDto;*/

import feign.FeignException;
import feign.hystrix.FallbackFactory;

@FeignClient(name="administracion-general",fallbackFactory = DatafallbackFactory.class)
public interface DataServiceClient {

	/*
	 * @GetMapping("/otro/find-centro-costo-byid/{idunidadnegocio}") public String
	 * findCentroCostoById(@PathVariable Integer idunidadnegocio);
	 * 
	 * @GetMapping("/plan-cuenta/plan-contable-ordenado/{anio}/{idperiodoanio}")
	 * public List<PlanContableDto> listarPlanContableOrdenado(@PathVariable String
	 * anio,@PathVariable Integer idperiodoanio);
	 * 
	 * @GetMapping(
	 * "/plan-cuenta//filtro-planes/detallado-por-empresa/{anio}/{idperiodoanio}/{nivelcuenta}")
	 * public List<CuentaDetalladaDto>
	 * listarPlanContableDetalladoOrdenado(@PathVariable String anio,@PathVariable
	 * Integer idperiodoanio,@PathVariable String nivelcuenta);
	 */
/*
	@GetMapping("/plan-cuenta/ples/filtrar-cuenta/{plan}/empresa/{idperiodoanio}")
	public ResponseEntity<Map<String, Object>> buscarPlanPorEmpresaForPles(@PathVariable String plan,@PathVariable Integer idperiodoanio);
	
	@GetMapping("/plan-cuenta/ples/plan-cuenta/{id}")
	public ResponseEntity<Map<String, Object>> buscarPlanCuentaForPles(@PathVariable Integer id);*/
}

@Component
class DatafallbackFactory implements FallbackFactory<DataServiceClient>{

	@Override
	public DataServiceClient create(Throwable cause) {
		return new DataServiceClientFallback(cause);
	}

class DataServiceClientFallback implements DataServiceClient{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final Throwable cause;
	
	public DataServiceClientFallback(Throwable cause) {
		this.cause = cause;
	}

	
	/*
	 * @Override public String findCentroCostoById(Integer idunidadnegocio) {
	 * if(cause instanceof FeignException && ((FeignException) cause).status() ==
	 * 404) { logger.
	 * error("Se produjo un error al tratar de Llamar a Plan de Cuenta con Id:" +
	 * idunidadnegocio + ". Error Mensaje: " + cause.getLocalizedMessage()); }else {
	 * logger.error("Otro error: " + cause.getLocalizedMessage()); } return null; }
	 */
	
	/*
	 * @Override public List<PlanContableDto> listarPlanContableOrdenado(String
	 * anio,Integer idperiodoanio) { if(cause instanceof FeignException &&
	 * ((FeignException) cause).status() == 404) { logger.
	 * error("Se produjo un error al tratar de Llamar a Plan contable con año :" +
	 * anio + " en año_periodo "+idperiodoanio + " . Error Mensaje: " +
	 * cause.getLocalizedMessage());
	 * 
	 * }else { logger.error("Otro error: " + cause.getLocalizedMessage()); } return
	 * null; }
	 */
	
	
	/*
	 * @Override public List<CuentaDetalladaDto>
	 * listarPlanContableDetalladoOrdenado(String anio, Integer idperiodoanio,String
	 * nivelcuenta){ if(cause instanceof FeignException && ((FeignException)
	 * cause).status() == 404) { logger.
	 * error("Se produjo un error al tratar de Llamar a Plan contable detallado con año :"
	 * + anio + " en año_periodo "+idperiodoanio + " . Error Mensaje: " +
	 * cause.getLocalizedMessage());
	 * 
	 * }else { logger.error("Otro error: " + cause.getLocalizedMessage()); } return
	 * null; }
	 */
	
	
	/*@Override
	public ResponseEntity<Map<String, Object>> buscarPlanPorEmpresaForPles(String plan,Integer idperiodoanio) {
		if(cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error("Se produjo un error al tratar de Llamar a Plan de Cuenta con codigo :" + plan + " en año_periodo "+idperiodoanio + " . Error Mensaje: " + cause.getLocalizedMessage());

		}else {
			logger.error("Otro error: " + cause.getLocalizedMessage());
		}
		return null;
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> buscarPlanCuentaForPles(Integer id) {
		if(cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error("404 - Se produjo un error al tratar de Llamar a Plan de Cuenta con Id:" + id + ". Error Mensaje: " + cause.getLocalizedMessage());
		}else {
			if(cause instanceof FeignException && ((FeignException) cause).status() == 406) {
				logger.error("406 - Se produjo un error al tratar de Llamar a Plan de Cuenta con Id:" + id + ". Error Mensaje: " + cause.getLocalizedMessage());
			}else {
				logger.error("Otro error: " + cause.getLocalizedMessage());
			}
		}
		return null;
	}
	*/
}
	
}
